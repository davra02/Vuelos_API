package aiss.api.resources;

import javax.ws.rs.Path;

import aiss.model.repository.ViajeRepository;

@Path("/songs")
public class ViajeResource {

	public static ViajeResource _instance=null;
	ViajeRepository repository;
	
	private ViajeResource(){
		repository=MapPlaylistRepository.getInstance();
	}
	
	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Song> getAll(@QueryParam("q") String q, @QueryParam("order") String order, @QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset)
	{
		List<Song> result = new ArrayList<>();
		for(Song song: repository.getAllSongs()) {
			if(q == null || song.getTitle().contains(q) || song.getAlbum().contains(q) ||
					song.getArtist().contains(q)) {
				result.add(song);
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
	public Song get(@PathParam("id") String songId)
	{
		
		Song s = repository.getSong(songId);
		if(s == null) {
			throw new NotFoundException("The song with id="+songId+" doesn't exist");
		}
		
		
		return s;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Song song) {
		if(song.getTitle()== null || "".equals(song.getTitle())) {
			throw new BadRequestException("The song name is not valid");
		}
		repository.addSong(song);
		
		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(song.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(song);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Song song) {
		Song oldSong = repository.getSong(song.getId());
		
		if(oldSong == null) {
			throw new NotFoundException("The song with de id="+song.getId()+" doesn't exist");
		}
		if(song.getTitle()!= null) {
			oldSong.setTitle(song.getTitle());	
		}
		
		if(song.getAlbum()!= null) {
			oldSong.setAlbum(song.getAlbum());	
		}
		
		if(song.getArtist()!= null) {
			oldSong.setArtist(song.getArtist());			
		}
		
		if(song.getYear()!=null) {
			oldSong.setYear(song.getYear());	
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) {
		Song toberemoved = repository.getSong(songId);
		if(toberemoved == null) {
			throw new NotFoundException("The song with de id="+songId+" doesn't exist");
		}else {
			repository.deleteSong(songId);
		}
		
		return Response.noContent().build();
	}
	
}

