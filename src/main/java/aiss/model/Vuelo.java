package aiss.model;

public class Vuelo {
	
	private String id;
	private String compañia;
	private String horaLlegada;
	private String horaSalida;
	private String precio;
	private String clase;
	private String escala;
	private Avion avion;
	
	public Vuelo() {
	}
	
	public Vuelo(String id, String compañia, String numAsiento, String horaLlegada, String horaSalida,
			String precio) {
		super();
		this.id = id;
		this.compañia = compañia;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.precio = precio;
	}

	public Vuelo(String compañia, String numAsiento, String horaLlegada, String horaSalida,
			String precio) {
		super();
		this.compañia = compañia;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.precio = precio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getEscala() {
		return escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}
}
