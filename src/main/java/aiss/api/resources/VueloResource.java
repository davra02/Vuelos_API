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

import aiss.model.Vuelo;
import aiss.model.repository.MapViajeRepository;
import aiss.model.repository.ViajeRepository;

public class VueloResource {
	
	//CAMBIAR IDS POR UN HASH CODE, AÑADIENDO UNA PROPIEDAD MAS EN EL TIPO VUELO, LO HAREMOS CON UNA CLASE DE JAVA 
	//QUE CREE CODIGOS ALEATORIOS PARA TENER UN IDENTIFICADOR DE VUELO
	
	public static VueloResource _instance=null;
	ViajeRepository repository;
	
	private VueloResource(){
		repository = MapViajeRepository.getInstance();
	}
	
	public static VueloResource getInstance()
	{
		if(_instance==null)
			_instance=new VueloResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Vuelo> getAll(@QueryParam("q") String q, @QueryParam("order") String order, @QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset)
	{
		List<Vuelo> result = new ArrayList<>();
		for(Vuelo vuelo: repository.getAllVuelos()) {
			if(q == null || vuelo.getId().contains(q) || vuelo.getCompañia().contains(q) ||
					vuelo.getEscala().contains(q)) {
				result.add(vuelo);
			}
		}
		
		if(order!= null) {
			if(order.equals("album")) {
				Collections.sort(result,new ComparatorSongAlbum());
			}else if(order.equals("-album")) {
				Collections.sort(result,new ComparatorSongAlbumReversed());
			}else if(order.equals("artist")) {
				Collections.sort(result,new ComparatorSongArtist());
			}else if(order.equals("-artist")) {
				Collections.sort(result,new ComparatorSongArtistReversed());
			}else if(order.equals("year")) {
				Collections.sort(result,new ComparatorSongYear());
			}else if(order.equals("-year")) {
				Collections.sort(result,new ComparatorSongYearReversed());
			}else {
				throw new BadRequestException("The order parameter should be name or -name");
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
	public Vuelo get(@PathParam("id") String vueloId)
	{
		
		Vuelo v = repository.getVuelo(vueloId);
		if(v == null) {
			throw new NotFoundException("The song with id="+vueloId+" doesn't exist");
		}
		
		
		return v;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Vuelo vuelo) {
		if(vuelo.getId()== null || "".equals(vuelo.getId())) {  // CAMBIAR POR UN HASHCODE
			throw new BadRequestException("The song name is not valid");
		}
		repository.addVuelo(vuelo);
		
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
			throw new NotFoundException("The song with de id="+vuelo.getId()+" doesn't exist");
		}
		if(vuelo.getId()!= null) {
			oldVuelo.setId(vuelo.getId());	
		}
		
		if(vuelo.getCompañia()!= null) {
			oldVuelo.setCompañia(vuelo.getCompañia());	
		}
		
		if(vuelo.getEscala()!= null) {
			oldVuelo.setEscala(vuelo.getEscala());			
		}
		
		if(vuelo.getHoraLlegada()!=null) {
			oldVuelo.setHoraLlegada(vuelo.getHoraLlegada());	
		}
		
		if(vuelo.getHoraSalida() != null) {
			oldVuelo.setHoraSalida(vuelo.getHoraSalida());
		}
		
		if(vuelo.getNumAsiento() != null) {
			oldVuelo.setNumAsiento(vuelo.getNumAsiento());
		}
		
		if(vuelo.getPrecio() != null) {
			oldVuelo.setPrecio(vuelo.getPrecio());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String vueloId) {
		Vuelo toberemoved = repository.getVuelo(vueloId);
		if(toberemoved == null) {
			throw new NotFoundException("The song with de id="+vueloId+" doesn't exist");
		}else {
			repository.deleteVuelo(vueloId);
		}
		
		return Response.noContent().build();
	}

}
