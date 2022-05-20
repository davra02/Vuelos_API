package aiss.api.comparators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import aiss.model.Viaje;

public class ComparatorViajeFecha implements Comparator<Viaje> {

	@Override
	public int compare(Viaje o1, Viaje o2) {
		LocalDate fechaSalida1 = LocalDate.parse(o1.getFecha(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate fechaSalida2 = LocalDate.parse(o2.getFecha(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return fechaSalida1.compareTo(fechaSalida2);
	}



}
