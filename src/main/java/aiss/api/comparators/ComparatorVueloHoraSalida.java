package aiss.api.comparators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloHoraSalida implements Comparator<Vuelo> {

public int compare(Vuelo o1, Vuelo o2) {
		
		LocalTime horaSalida1 = LocalTime.parse(o1.getHoraSalida(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalTime horaSalida2 = LocalTime.parse(o2.getHoraSalida(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return horaSalida1.compareTo(horaSalida2);
	}

}
