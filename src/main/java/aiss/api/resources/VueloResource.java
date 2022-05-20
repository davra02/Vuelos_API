package aiss.api.resources;

import java.net.URI; 

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.comparators.*;
import aiss.model.Vuelo;
import aiss.model.repository.MapViajeRepository;
import aiss.model.repository.ViajeRepository;

@Path("/vuelos")
public class VueloResource {

	public static VueloResource _instance=null;
	ViajeRepository repository;
	
	private VueloResource(){
		repository=MapViajeRepository.getInstance();
	}
	
	public static VueloResource getInstance()
	{
		if(_instance==null)
				_instance=new VueloResource();
		return _instance;
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<Vuelo> getAll(@QueryParam("compañia") String compañia, @QueryParam("order") String order, @QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset, @QueryParam("precioMax") String precioMax,  @QueryParam("tieneBusiness") String business)	{
		List<Vuelo> result = new ArrayList<>();
		for(Vuelo v: repository.getAllVuelos()) {
			if(compañia == null || v.getCompañia().equals(compañia)) {
				if(precioMax == null || Integer.parseInt(v.getPrecio())<= Integer.parseInt(precioMax)) {
					if(business == null || v.getAvion().getServicios().contains(business)) {
						result.add(v);
					}
				}
				}
			}	
		if (order != null) {
			if(order.equals("compañia")) {
				Collections.sort(result, new ComparatorVueloCompañia());
			} else if(order.equals("-compañia")) {
				Collections.sort(result, new ComparatorVueloCompañia().reversed());
			} else if(order.equals("escala")) {
				Collections.sort(result, new ComparatorVueloEscala());
			} else if(order.equals("-escala")) {
				Collections.sort(result, new ComparatorVueloEscala().reversed());
			} else if(order.equals("horaLlegada")) {
				Collections.sort(result, new ComparatorVueloHoraLlegada());
			} else if(order.equals("-horaLlegada")) {
				Collections.sort(result, new ComparatorVueloHoraLlegada().reversed());
			}else if(order.equals("horaSalida")) {
				Collections.sort(result, new ComparatorVueloHoraSalida());
			} else if(order.equals("-horaSalida")) {
				Collections.sort(result, new ComparatorVueloHoraSalida().reversed());
			}else if(order.equals("precio")) {
				Collections.sort(result, new ComparatorVueloPrecio());
			} else if(order.equals("-precio")) {
				Collections.sort(result, new ComparatorVueloPrecio().reversed());
			}else {
				throw new BadRequestException(
						"El parámetro debería ser uno de los siguientes: compañia, -compañia, escala, -escala, horaLlegada, -horaLlegada, horaSalida, -horaSalida, numAsiento, -numAsiento, precio, -precio");
			}
		}
		
		if(offset != null) {
			if(limit != null) {
				result = result.subList(offset, limit);
			}else {
				result = result.subList(offset, result.size());
			}
		}else {
			if(limit != null) {
				result = result.subList(0, limit);
			}
		}
		return result;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Vuelo get(@PathParam("id") String id)
	{
		Vuelo vuelo = repository.getVuelo(id);
		
		if (vuelo == null) {
			throw new NotFoundException("El vuelo con id "+ id +" no se encontró.");			
		}
		
		return vuelo;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVuelo(@Context UriInfo uriInfo, Vuelo vuelo) {
		if ((vuelo.getCompañia() == null || "".equals(vuelo.getCompañia()))
			|| (vuelo.getHoraLlegada() == null || "".equals(vuelo.getHoraLlegada()))
			|| (vuelo.getHoraSalida() == null || "".equals(vuelo.getHoraSalida()))
			|| (vuelo.getPrecio() == null || "".equals(vuelo.getPrecio()))) {
			throw new BadRequestException("Ningún campo del vuelo puede ser nulo o "
					+ "una cadena vacía"); 
		}
		repository.addVuelo(vuelo);	
		
		// Builds the response. Returns the vuelos the has just been added.
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(vuelo.getId());
		 	ResponseBuilder resp = Response.created(uri);
			resp.entity(vuelo);			
			return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Vuelo vuelo) {
		Vuelo oldVuelo = repository.getVuelo(vuelo.getId());
		
		if(oldVuelo == null) {
			throw new NotFoundException("El vuelo con el id "+vuelo.getId()+" no se encontró");
		}
		if(vuelo.getCompañia()!= null) {
			oldVuelo.setCompañia(vuelo.getCompañia());	
		}
		
		if(vuelo.getHoraLlegada()!= null) {
			oldVuelo.setHoraLlegada(vuelo.getHoraLlegada());			
		}
		
		if(vuelo.getHoraSalida()!=null) {
			oldVuelo.setHoraSalida(vuelo.getHoraSalida());	
		}
		
		if(vuelo.getPrecio()!=null) {
			oldVuelo.setPrecio(vuelo.getPrecio());	
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeVuelo(@PathParam("id") String vueloId) {
		Vuelo toberemoved = repository.getVuelo(vueloId);
		if(toberemoved == null) {
			throw new NotFoundException("El vuelo con el id "+vueloId+" no se encontró");
		}else {
			repository.deleteVuelo(vueloId);
		}
		
		return Response.noContent().build();
	}
		
}
