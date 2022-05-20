package aiss.api.comparators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloHoraLlegada implements Comparator<Vuelo> {

	
	public int compare(Vuelo o1, Vuelo o2) {
		
		LocalTime horaLlegada1 = LocalTime.parse(o1.getHoraLlegada(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalTime horaLlegada2 = LocalTime.parse(o2.getHoraLlegada(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return horaLlegada1.compareTo(horaLlegada2);
	}

}
