package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
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

import aiss.model.Viaje;
import aiss.model.Vuelo;
import aiss.model.repository.MapViajeRepository;
import aiss.model.repository.ViajeRepository;

@Path("/songs")
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
	public Collection<Viaje> getAll(@QueryParam("isEmpty") Boolean isEmpty, @QueryParam("name") String name)
	{
		List<Viaje> result = new ArrayList<>();
		for(Viaje v: repository.getAllViajes()) {
			//Filtros
			if(name == null || v.getId().equals(name)){
				if(isEmpty == null ||
						(isEmpty && (v.getVuelos()== null || v.getVuelos().size()==0)) ||
						(!isEmpty && (v.getVuelos() != null || v.getVuelos().size() > 0)) ) {
					result.add(v);
				}
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
			throw new NotFoundException("El viaje con id="+ id +" no se encontró.");			
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
			throw new BadRequestException("Ningún campo del viaje puede ser nulo o "
					+ "una cadena vacía");
		
		if (viaje.getVuelos()!=null)
			throw new BadRequestException("The songs property is not editable.");

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
					viaje.getId() +" no se encontró");			
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
					id +" no se encontró");
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
					viajeId +" no se encontró");
		
		if (vuelo == null)
			throw new NotFoundException("El vuelo con id="+ 
					viajeId +" no se encontró");		
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
					viajeId +" no se encontró");
		if (vuelo == null)
			throw new NotFoundException("El vuelo con id="+ 
					viajeId +" no se encontró");
		repository.removeVuelo(viajeId, vueloId);		
		
		return Response.noContent().build();
	}
}
