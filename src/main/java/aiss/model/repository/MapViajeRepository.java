package aiss.model.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aiss.model.Avion;
import aiss.model.Viaje;
import aiss.model.Vuelo;



public class MapViajeRepository implements ViajeRepository{

	Map<String, Viaje> viajeMap;
	Map<String, Vuelo> vueloMap;
	Map<String, Avion> avionMap;
	private static MapViajeRepository instance=null;
	private int index=0;
	
	
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
		avionMap = new HashMap<String,Avion>();
		
		//Aviones
		Avion avion1 = new Avion();
		avion1.setCapacidad("220");
		avion1.setModelo("Airbus320");
		List<String> listaServicios1 = Arrays.asList("Alimentacion","Business","Entretenimiento");
		avion1.setServicios(listaServicios1);
		addAvion(avion1);

		Avion avion2 = new Avion();
		avion2.setCapacidad("215");
		avion2.setModelo("Boeing737");
		List<String> listaServicios2 = Arrays.asList("Alimentacion");
		avion2.setServicios(listaServicios2);
		addAvion(avion2);

		Avion avion3 = new Avion();
		avion3.setCapacidad("550");
		avion3.setModelo("Boeing777");
		List<String> listaServicios3 = Arrays.asList("Alimentacion","Business");
		avion3.setServicios(listaServicios3);
		addAvion(avion3);

		Avion avion4 = new Avion();
		avion4.setCapacidad("156");
		avion4.setModelo("Airbus319");
		List<String> listaServicios4 = Arrays.asList("Entretenimiento");
		avion4.setServicios(listaServicios4);
		addAvion(avion4);

		//Viaje 1
		Viaje viaje1 = new Viaje();
		viaje1.setOrigen("Madrid");
		viaje1.setDestino("Roma");
		viaje1.setFecha("01-09-2022");
		addViaje(viaje1);

		Vuelo vuelo1 = new Vuelo();
		vuelo1.setCompañia("RyanAir");
		vuelo1.setEscala("true");
		vuelo1.setHoraLlegada("4:00");
		vuelo1.setHoraSalida("14:00");
		vuelo1.setPrecio("199");
		vuelo1.setAvion(avion1);
		addVuelo(vuelo1);
			
		Vuelo vuelo2 = new Vuelo();
		vuelo2.setCompañia("Fly Emirates");
		vuelo2.setEscala("false");
		vuelo2.setHoraLlegada("22:00");
		vuelo2.setHoraSalida("11:00");
		vuelo2.setPrecio("249");
		vuelo2.setAvion(avion2);
		addVuelo(vuelo2);
				
		Vuelo vuelo3 = new Vuelo();
		vuelo3.setCompañia("Iberia");
		vuelo3.setEscala("true");
		vuelo3.setHoraLlegada("23:00");
		vuelo3.setHoraSalida("11:30");
		vuelo3.setPrecio("225");
		vuelo3.setAvion(avion3);
		addVuelo(vuelo3);


		//Viaje 2		
		Viaje viaje2 = new Viaje();
		viaje2.setOrigen("Barcelona");
		viaje2.setDestino("Tokio");
		viaje2.setFecha("11-08-2022");
		addViaje(viaje2);
				
		Vuelo vuelo4 = new Vuelo();
		vuelo4.setCompañia("RyanAir");
		vuelo4.setEscala("flase");
		vuelo4.setHoraLlegada("12:00");
		vuelo4.setHoraSalida("10:00");
		vuelo4.setPrecio("79");
		vuelo4.setAvion(avion4);
		addVuelo(vuelo4);
						
		Vuelo vuelo5 = new Vuelo();
		vuelo5.setCompañia("Vueling");
		vuelo5.setEscala("false");
		vuelo5.setHoraLlegada("20:00");
		vuelo5.setHoraSalida("18:00");
		vuelo5.setPrecio("75");
		vuelo5.setAvion(avion1);
		addVuelo(vuelo5);

		//Viaje 3
		Viaje viaje3 = new Viaje();
		viaje3.setOrigen("Paris");
		viaje3.setDestino("Praga");
		viaje3.setFecha("20-09-2022");
		addViaje(viaje3);

		Vuelo vuelo6 = new Vuelo();
		vuelo6.setCompañia("Vueling");
		vuelo6.setEscala("false");
		vuelo6.setHoraLlegada("12:00");
		vuelo6.setHoraSalida("14:00");
		vuelo6.setPrecio("55");
		vuelo6.setAvion(avion2);
		addVuelo(vuelo6);

		Vuelo vuelo7 = new Vuelo();
		vuelo7.setCompañia("EasyJet");
		vuelo7.setEscala("false");
		vuelo7.setHoraLlegada("10:00");
		vuelo7.setHoraSalida("12:00");
		vuelo7.setPrecio("88");
		vuelo7.setAvion(avion3);
		addVuelo(vuelo7);

		Vuelo vuelo8 = new Vuelo();
		vuelo8.setCompañia("RyanAir");
		vuelo8.setEscala("false");
		vuelo8.setHoraLlegada("13:00");
		vuelo8.setHoraSalida("15:30");
		vuelo8.setPrecio("67");
		vuelo8.setAvion(avion4);
		addVuelo(vuelo8);

		Vuelo vuelo9 = new Vuelo();
		vuelo9.setCompañia("Iberia");
		vuelo9.setEscala("false");
		vuelo9.setHoraLlegada("09:00");
		vuelo9.setHoraSalida("11:30");
		vuelo9.setPrecio("77");
		vuelo9.setAvion(avion1);
		addVuelo(vuelo9);

		//Viaje 4
		Viaje viaje4 = new Viaje();
		viaje4.setOrigen("Londres");
		viaje4.setDestino("Paris");
		viaje4.setFecha("21-10-2022");
		addViaje(viaje4);

		Vuelo vuelo10 = new Vuelo();
		vuelo10.setCompañia("Vueling");
		vuelo10.setEscala("false");
		vuelo10.setHoraLlegada("12:30");
		vuelo10.setHoraSalida("14:00");
		vuelo10.setPrecio("30");
		vuelo10.setAvion(avion2);
		addVuelo(vuelo10);

		Vuelo vuelo11 = new Vuelo();
		vuelo11.setCompañia("EasyJet");
		vuelo11.setEscala("false");
		vuelo11.setHoraLlegada("10:00");
		vuelo11.setHoraSalida("11:30");
		vuelo11.setPrecio("48");
		vuelo11.setAvion(avion3);
		addVuelo(vuelo11);

		Vuelo vuelo12 = new Vuelo();
		vuelo12.setCompañia("RyanAir");
		vuelo12.setEscala("false");
		vuelo12.setHoraLlegada("13:30");
		vuelo12.setHoraSalida("15:00");
		vuelo12.setPrecio("39");
		vuelo12.setAvion(avion4);
		addVuelo(vuelo12);

		Vuelo vuelo13 = new Vuelo();
		vuelo13.setCompañia("Iberia");
		vuelo13.setEscala("false");
		vuelo13.setHoraLlegada("09:00");
		vuelo13.setHoraSalida("10:30");
		vuelo13.setPrecio("43");
		vuelo13.setAvion(avion1);
		addVuelo(vuelo13);

		//Viaje 5
		Viaje viaje5 = new Viaje();
		viaje5.setOrigen("Nueva York");
		viaje5.setDestino("Sevilla");
		viaje5.setFecha("22-11-2022");
		addViaje(viaje5);

		Vuelo vuelo14 = new Vuelo();
		vuelo14.setCompañia("Vueling");
		vuelo14.setEscala("true");
		vuelo14.setHoraLlegada("10:30");
		vuelo14.setHoraSalida("19:30");
		vuelo14.setPrecio("255");
		vuelo14.setAvion(avion2);
		addVuelo(vuelo14);

		Vuelo vuelo15 = new Vuelo();
		vuelo15.setCompañia("EasyJet");
		vuelo15.setEscala("true");
		vuelo15.setHoraLlegada("11:00");
		vuelo15.setHoraSalida("20:30");
		vuelo15.setPrecio("348");
		vuelo15.setAvion(avion3);
		addVuelo(vuelo15);

		Vuelo vuelo16 = new Vuelo();
		vuelo16.setCompañia("RyanAir");
		vuelo16.setEscala("true");
		vuelo16.setHoraLlegada("07:30");
		vuelo16.setHoraSalida("17:00");
		vuelo16.setPrecio("288");
		vuelo16.setAvion(avion4);
		addVuelo(vuelo16);

		Vuelo vuelo17 = new Vuelo();
		vuelo17.setCompañia("Iberia");
		vuelo17.setEscala("true");
		vuelo17.setHoraLlegada("09:00");
		vuelo17.setHoraSalida("18:30");
		vuelo17.setPrecio("343");
		vuelo17.setAvion(avion1);
		addVuelo(vuelo17);

		//Viaje 6
		Viaje viaje6 = new Viaje();
		viaje6.setOrigen("Viena");
		viaje6.setDestino("Moscu");
		viaje6.setFecha("23-12-2022");
		addViaje(viaje6);

		Vuelo vuelo18 = new Vuelo();
		vuelo18.setCompañia("Vueling");
		vuelo18.setEscala("false");
		vuelo18.setHoraLlegada("10:30");
		vuelo18.setHoraSalida("14:30");
		vuelo18.setPrecio("155");
		vuelo18.setAvion(avion2);
		addVuelo(vuelo14);

		Vuelo vuelo19 = new Vuelo();
		vuelo19.setCompañia("EasyJet");
		vuelo19.setEscala("false");
		vuelo19.setHoraLlegada("08:00");
		vuelo19.setHoraSalida("12:30");
		vuelo19.setPrecio("176");
		vuelo19.setAvion(avion3);
		addVuelo(vuelo19);

		Vuelo vuelo20 = new Vuelo();
		vuelo20.setCompañia("RyanAir");
		vuelo20.setEscala("true");
		vuelo20.setHoraLlegada("07:30");
		vuelo20.setHoraSalida("11:00");
		vuelo20.setPrecio("99");
		vuelo20.setAvion(avion4);
		addVuelo(vuelo20);

		addVuelo(viaje1.getId(), vuelo1.getId());
		addVuelo(viaje1.getId(), vuelo2.getId());
		addVuelo(viaje1.getId(), vuelo3.getId());
			
		addVuelo(viaje2.getId(), vuelo4.getId());
		addVuelo(viaje2.getId(), vuelo5.getId());

		addVuelo(viaje3.getId(), vuelo6.getId());
		addVuelo(viaje3.getId(), vuelo7.getId());
		addVuelo(viaje3.getId(), vuelo8.getId());
		addVuelo(viaje3.getId(), vuelo9.getId());

		addVuelo(viaje4.getId(), vuelo10.getId());
		addVuelo(viaje4.getId(), vuelo11.getId());
		addVuelo(viaje4.getId(), vuelo12.getId());
		addVuelo(viaje4.getId(), vuelo13.getId());

		addVuelo(viaje5.getId(), vuelo14.getId());
		addVuelo(viaje5.getId(), vuelo15.getId());
		addVuelo(viaje5.getId(), vuelo16.getId());
		addVuelo(viaje5.getId(), vuelo17.getId());

		addVuelo(viaje6.getId(), vuelo18.getId());
		addVuelo(viaje6.getId(), vuelo19.getId());
		addVuelo(viaje6.getId(), vuelo20.getId());
	}
	
	@Override
	public void addViaje(Viaje v) {
		String id = "viaje" + index++;	
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
		String id = "vuelo" + index++;
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
		vuelo.setPrecio(v.getPrecio());

	}

	@Override
	public void deleteVuelo(String vueloId) {
		vueloMap.remove(vueloId);
	}

	@Override
	public Collection<Avion> getAllAviones() {
		return avionMap.values();
	}

	@Override
	public Avion getAvion(String id) {
		return avionMap.get(id);
	}

	@Override
	public void addAvion(Avion avion) {
		String id = "avion "+ index++;
		avion.setId(id);
		avionMap.put(id, avion);
	}

	@Override
	public void deleteAvion(String avionId) {
		avionMap.remove(avionId);
	}
	
	@Override
	public void updateAvion(Avion a) {
		Avion avion = avionMap.get(a.getId());
		avion.setCapacidad(a.getCapacidad());
		avion.setModelo(a.getModelo());
		avion.setServicios(a.getServicios());
	}
}
