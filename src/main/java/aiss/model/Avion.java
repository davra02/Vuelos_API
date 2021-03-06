package aiss.model;

import java.util.List;

public class Avion {
	
	private String id;
	private String capacidad;
	private String modelo;
	private List<String> servicios;
	
	
	public Avion() {
		
	}
	
	public Avion(String id, String capacidad, String modelo, List<String> servicios) {
		super();
		this.id = id;
		this.capacidad = capacidad;
		this.modelo = modelo;
		this.servicios = servicios;
	}
	
	public Avion(String capacidad, String modelo, List<String> servicios) {
		super();
		this.capacidad = capacidad;
		this.modelo = modelo;
		this.servicios = servicios;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public List<String> getServicios() {
		return servicios;
	}
	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}
}
