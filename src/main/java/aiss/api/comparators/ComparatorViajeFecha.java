package aiss.api.comparators;

import java.util.Comparator;

import aiss.model.Viaje;

public class ComparatorViajeFecha implements Comparator<Viaje> {

	@Override
	public int compare(Viaje o1, Viaje o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}



}
