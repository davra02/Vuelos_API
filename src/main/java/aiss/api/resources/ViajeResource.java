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
import aiss.model.Viaje;
import aiss.model.Vuelo;
import aiss.model.repository.MapViajeRepository;
import aiss.model.repository.ViajeRepository;

@Path("/viajes")
public class ViajeResource {

	public static ViajeResource _instance=null;
	ViajeRepository repository;
	
	private ViajeResource(){
		repository=MapViajeRepository.getInstance();
	}
	
	public static ViajeResource getInstance()
	{
		if(_instance==null)
				_instance=new ViajeResource();
		return _instance;
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<Viaje> getAll(@QueryParam("isEmpty") Boolean isEmpty, @QueryParam("id") String id,  @QueryParam("order") String order,
			@QueryParam("origen") String origen, @QueryParam("destino") String destino, @QueryParam("fecha") String fecha,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
	{
		List<Viaje> result = new ArrayList<>();
		for(Viaje v: repository.getAllViajes()) {
			//Filtros
			if(id == null || v.getId().equals(id)){
				if(origen == null || v.getOrigen().equals(origen)) {
					if(destino == null || v.getDestino().equals(destino)) {
						if(fecha == null || v.getFecha().equals(fecha)) {
							if(isEmpty == null ||
									(isEmpty && (v.getVuelos()== null || v.getVuelos().size()==0)) ||
									(!isEmpty && (v.getVuelos() != null || v.getVuelos().size() > 0)) ) {
								result.add(v);
							}
						}
					}
				}	
			}
			if (order != null) {
				if(order.equals("fecha")) {
					Collections.sort(result, new ComparatorViajeFecha());
				} else if (order.equals("-fecha")) {
					Collections.sort(result, new ComparatorViajeFecha().reversed());
				} else {
				
				throw new BadRequestException(
						"The order parameter should be one of these: fecha, -fecha");
				}
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
	public Viaje get(@PathParam("id") String id)
	{
		Viaje viaje = repository.getViaje(id);
		
		if (viaje == null) {
			throw new NotFoundException("El viaje con id="+ id +" no se encontr??.");			
		}
		
		return viaje;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addViaje(@Context UriInfo uriInfo, Viaje viaje) {
		if ((viaje.getDestino() == null || "".equals(viaje.getDestino()))
			|| (viaje.getOrigen() == null || "".equals(viaje.getOrigen())
			|| (viaje.getFecha() == null || "".equals(viaje.getFecha()))))
			throw new BadRequestException("Ning??n campo del viaje puede ser nulo o "
					+ "una cadena vac??a");
		
		if (viaje.getVuelos()!=null)
			throw new BadRequestException("La propiedad vuelos no es editable.");

		repository.addViaje(viaje);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(viaje.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(viaje);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateViaje(Viaje viaje) {
		Viaje oldViaje = repository.getViaje(viaje.getId());
		if (oldViaje == null) {
			throw new NotFoundException("El viaje con id="+ 
					viaje.getId() +" no se encontr??");			
		}
		
		if (viaje.getVuelos()!=null)
			throw new BadRequestException("La propiedad de los vuelos no es editable");
		
		// Update origen
		if (viaje.getOrigen()!=null)
			oldViaje.setOrigen(viaje.getOrigen());
		
		// Update destino
		if (viaje.getDestino()!=null)
			oldViaje.setDestino(viaje.getDestino());
		
		//Update fecha
		if (viaje.getFecha()!=null)
			oldViaje.setFecha(viaje.getFecha());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeViaje(@PathParam("id") String id) {
		Viaje toberemoved=repository.getViaje(id);
		if (toberemoved == null)
			throw new NotFoundException("El viaje con id="+ 
					id +" no se encontr??");
		else
			repository.deleteViaje(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{viajeId}/{vueloId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addVuelo(@Context UriInfo uriInfo,@PathParam("viajeId") String viajeId, @PathParam("vueloId") String vueloId)
	{				
		
		Viaje viaje = repository.getViaje(viajeId);
		Vuelo vuelo = repository.getVuelo(vueloId);
		
		if (viaje==null)
			throw new NotFoundException("El viaje con id="+ 
					viajeId +" no se encontr??");
		
		if (vuelo == null)
			throw new NotFoundException("El vuelo con id="+ 
					viajeId +" no se encontr??");		
		if (viaje.getVuelo(vueloId)!=null)
			throw new BadRequestException("El vuelo ya se encuentra en el viaje.");
			
		repository.addVuelo(viajeId, vueloId);		

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(viajeId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(viaje);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{viajeId}/{vueloId}")
	public Response removeVuelo(@PathParam("viajeId") String viajeId, @PathParam("vueloId") String vueloId) {
		Viaje viaje = repository.getViaje(viajeId);
		Vuelo vuelo = repository.getVuelo(vueloId);		
		if (viaje==null)
			throw new NotFoundException("El viaje con id="+ 
					viajeId +" no se encontr??");
		if (vuelo == null)
			throw new NotFoundException("El vuelo con id="+ 
					vueloId +" no se encontr??");
		repository.removeVuelo(viajeId, vueloId);		
		
		return Response.noContent().build();
	}
}

