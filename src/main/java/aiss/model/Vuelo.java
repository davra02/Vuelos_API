package aiss.model;

public class Vuelo {
	
	private String id;
	private String escala;
	private String compañia;
	private String numAsiento;
	private String horaLlegada;
	private String horaSalida;
	private String precio;
	
	public Vuelo() {
	}
	
	public Vuelo(String id, String escala, String compañia, String numAsiento, String horaLlegada, String horaSalida,
			String precio) {
		super();
		this.id = id;
		this.escala = escala;
		this.compañia = compañia;
		this.numAsiento = numAsiento;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.precio = precio;
	}

	public Vuelo(String escala, String compañia, String numAsiento, String horaLlegada, String horaSalida,
			String precio) {
		super();
		this.escala = escala;
		this.compañia = compañia;
		this.numAsiento = numAsiento;
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

	public String getEscala() {
		return escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public String getNumAsiento() {
		return numAsiento;
	}

	public void setNumAsiento(String numAsiento) {
		this.numAsiento = numAsiento;
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
}
