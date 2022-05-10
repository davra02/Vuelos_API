package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Viaje;
import aiss.model.Vuelo;



public class MapViajeRepository implements ViajeRepository{

	Map<String, Viaje> viajeMap;
	Map<String, Vuelo> vueloMap;
	private static MapViajeRepository instance=null;
	private int index=0;			// Index to create playlists and songs' identifiers.
	
	
	public static MapViajeRepository getInstance() {
		
		if (instance==null) {
			instance = new MapViajeRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		viajeMap = new HashMap<String,Viaje>();
		vueloMap = new HashMap<String,Vuelo>();
		
		// Create songs
		Vuelo vuelo1 = new Vuelo();
		vuelo1.setCompañia("RyanAir");
		vuelo1.setEscala("true");
		vuelo1.setHoraLlegada("4:00");
		vuelo1.setHoraSalida("14:00");
		vuelo1.setNumAsiento("16");
		vuelo1.setPrecio("199");
		addVuelo(vuelo1);
		
		Vuelo vuelo2 = new Vuelo();
		vuelo2.setCompañia("Fly Emirates");
		vuelo2.setEscala("false");
		vuelo2.setHoraLlegada("22:00");
		vuelo2.setHoraSalida("11:00");
		vuelo2.setNumAsiento("100");
		vuelo2.setPrecio("249");
		addVuelo(vuelo2);
		
		
		Vuelo vuelo3 = new Vuelo();
		vuelo3.setCompañia("Iberia");
		vuelo3.setEscala("true");
		vuelo3.setHoraLlegada("23:00");
		vuelo3.setHoraSalida("11:30");
		vuelo3.setNumAsiento("67");
		vuelo3.setPrecio("225");
		addVuelo(vuelo3);
		
		
		Vuelo vuelo4 = new Vuelo();
		vuelo4.setCompañia("RyanAir");
		vuelo4.setEscala("flase");
		vuelo4.setHoraLlegada("12:00");
		vuelo4.setHoraSalida("10:00");
		vuelo4.setNumAsiento("100");
		vuelo4.setPrecio("79");
		addVuelo(vuelo4);
		
		
		Vuelo vuelo5 = new Vuelo();
		vuelo5.setCompañia("Vueling");
		vuelo5.setEscala("false");
		vuelo5.setHoraLlegada("20:00");
		vuelo5.setHoraSalida("18:00");
		vuelo5.setNumAsiento("114");
		vuelo5.setPrecio("75");
		addVuelo(vuelo5);
		
		// Create playlists
		Viaje viaje1 = new Viaje();
		viaje1.setOrigen("Madrid");
		viaje1.setDestino("Roma");
		viaje1.setFecha("01-09-2022");
		addViaje(viaje1);
		
		Viaje viaje2 = new Viaje();
		viaje2.setOrigen("Barcelona");
		viaje2.setDestino("Tokio");
		viaje2.setFecha("11-08-2022");
		addViaje(viaje2);
		
		// Add songs to playlists
		addVuelo(viaje1.getId(), vuelo1.getId());
		addVuelo(viaje1.getId(), vuelo2.getId());
		addVuelo(viaje1.getId(), vuelo3.getId());

		
		addVuelo(viaje2.getId(), vuelo4.getId());
		addVuelo(viaje2.getId(), vuelo5.getId());

	}
	
	// Playlist related operations
	@Override
	public void addViaje(Viaje v) {
		String id = "v" + index++;	
		v.setId(id);
		viajeMap.put(id,v);
	}
	
	@Override
	public Collection<Viaje> getAllViajes() {
			return viajeMap.values();
	}

	@Override
	public Viaje getViaje(String id) {
		return viajeMap.get(id);
	}
	
	@Override
	public void updateViaje(Viaje v) {
		viajeMap.put(v.getId(),v);
	}

	@Override
	public void deleteViaje(String id) {	
		viajeMap.remove(id);
	}
	

	@Override
	public void addVuelo(String viajeId, String vueloId) {
		Viaje viaje = getViaje(viajeId);
		viaje.addVuelo(vueloMap.get(vueloId));
	}

	@Override
	public Collection<Vuelo> getAll(String viajeId) {
		return getViaje(viajeId).getVuelos();
	}

	@Override
	public void removeVuelo(String viajeId, String vueloId) {
		getViaje(viajeId).deleteVuelo(vueloId);
	}

	
	// Vuelo related operations
	
	@Override
	public void addVuelo(Vuelo v) {
		String id = "v" + index++;
		v.setId(id);
		vueloMap.put(id, v);
	}
	
	@Override
	public Collection<Vuelo> getAllVuelos() {
			return vueloMap.values();
	}

	@Override
	public Vuelo getVuelo(String vueloId) {
		return vueloMap.get(vueloId);
	}

	@Override
	public void updateVuelo(Vuelo v) {
		Vuelo vuelo = vueloMap.get(v.getId());
		vuelo.setCompañia(v.getCompañia());
		vuelo.setEscala(v.getEscala());
		vuelo.setHoraLlegada(v.getHoraLlegada());
		vuelo.setHoraSalida(v.getHoraSalida());
		vuelo.setNumAsiento(v.getNumAsiento());
		vuelo.setPrecio(v.getPrecio());

	}

	@Override
	public void deleteVuelo(String vueloId) {
		vueloMap.remove(vueloId);
	}
	
}
