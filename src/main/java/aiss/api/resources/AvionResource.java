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
import aiss.model.Avion;
import aiss.model.repository.MapViajeRepository;
import aiss.model.repository.ViajeRepository;

@Path("/aviones")
public class AvionResource {

	public static AvionResource _instance=null;
	ViajeRepository repository;
	
	private AvionResource(){
		repository=MapViajeRepository.getInstance();
	}
	
	public static AvionResource getInstance()
	{
		if(_instance==null)
				_instance=new AvionResource();
		return _instance;
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<Avion> getAll(@QueryParam("q") String q, @QueryParam("order") String order, @QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset)	{
		List<Avion> result = new ArrayList<>();
		for(Avion a: repository.getAllAviones()) {
			if(q == null || a.getCapacidad().equals(q) || a.getModelo().equals(q) || a.getServicios().equals(q) ){
					result.add(a);
				}
			}	
		if (order != null) {
			if(order.equals("capacidad")) {
				Collections.sort(result, new ComparatorAvionCapacidad());
			}else if(order.equals("-capacidad")) {
				Collections.sort(result, new ComparatorAvionCapacidad().reversed());
			}else if(order.equals("modelo")) {
				Collections.sort(result, new ComparatorAvionModelo());
			}else if(order.equals("-modelo")) {
				Collections.sort(result, new ComparatorAvionModelo().reversed());
			}
			else {
				throw new BadRequestException(
						"El parámetro debería ser uno de los siguientes: capacidad, -capacidad, modelo, -modelo");
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
	public Avion get(@PathParam("id") String id)
	{
		Avion avion = repository.getAvion(id);
		
		if (avion == null) {
			throw new NotFoundException("El avion con id="+ id +" no se encontró.");			
		}
		
		return avion;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAvion(@Context UriInfo uriInfo, Avion avion) {
		if ((avion.getCapacidad() == null || "".equals(avion.getCapacidad()))
			|| (avion.getModelo() == null || "".equals(avion.getModelo()))
			|| (avion.getModelo() == null || "".equals(avion.getModelo()))
			|| (avion.getServicios() == null || "".equals(avion.getServicios()))) {
			throw new BadRequestException("Ningún campo del avion puede ser nulo o "
					+ "una cadena vacía"); 
		}
		repository.addAvion(avion);	
		
		// Builds the response. Returns the vuelos the has just been added.
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(avion.getId());
		 	ResponseBuilder resp = Response.created(uri);
			resp.entity(avion);			
			return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateAvion(Avion avion) {
		Avion oldAvion = repository.getAvion(avion.getId());
		
		if(oldAvion == null) {
			throw new NotFoundException("El avion con el id ="+avion.getId()+" no se encontró");
		}
		if(avion.getCapacidad()!= null) {
			oldAvion.setCapacidad(avion.getCapacidad());	
		}
		
		if(avion.getModelo()!= null) {
			oldAvion.setModelo(avion.getModelo());	
		}
		
		if(avion.getServicios()!= null) {
			oldAvion.setServicios(avion.getServicios());			
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeAvion(@PathParam("id") String avionId) {
		Avion toberemoved = repository.getAvion(avionId);
		if(toberemoved == null) {
			throw new NotFoundException("La canción con el id="+avionId+" no se encontró");
		}else {
			repository.deleteAvion(avionId);
		}
		
		return Response.noContent().build();
	}
		
}
