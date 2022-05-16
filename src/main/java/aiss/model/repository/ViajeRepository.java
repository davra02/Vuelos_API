package aiss.model.repository;

import java.util.Collection;

import aiss.model.Avion;
import aiss.model.Viaje;
import aiss.model.Vuelo;

public interface ViajeRepository {

	void addViaje(Viaje v);
	Collection<Viaje> getAllViajes();
	Viaje getViaje(String id);
	void updateViaje(Viaje v);
	void deleteViaje(String id);
	void addVuelo(String viajeId, String vueloId);
	Collection<Vuelo> getAll(String viajeId);
	void removeVuelo(String viajeId, String vueloId);
	void addVuelo(Vuelo v);
	Collection<Vuelo> getAllVuelos();
	Vuelo getVuelo(String vueloId);
	void updateVuelo(Vuelo v);
	void deleteVuelo(String vueloId);
	Collection<Avion> getAllAviones();
	Avion getAvion(String id);
	void addAvion(Avion avion);
	void deleteAvion(String avionId);

}
