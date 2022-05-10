package aiss.model;

import java.util.*;

public class Viaje {
	private String id;
	private String origen;
	private String destino;
	private String fecha;
	private List<Vuelo> vuelos;
	
	public Viaje(){
	}
	
	protected void setVuelos(List<Vuelo> v) {
		vuelos = v;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public Vuelo getVuelo(String id) {
		if(vuelos==null)
			return null;
		Vuelo vuelo = null;
		for(Vuelo v: vuelos)
			if(v.getId().equals(id)) {
				vuelo=v;
				break;
			}
		return vuelo;
	}
	
	public void addVuelo(Vuelo v) {
		if(vuelos == null)
			vuelos = new ArrayList<Vuelo>();
		vuelos.add(v);
	}
	
	public void deleteVuelo(Vuelo v) {
		vuelos.remove(v);
	}
	
	public void deleteVuelo(String id) {
		Vuelo v = getVuelo(id);
		if (v!=null)
			vuelos.remove(v);
	}
}
