package aiss.api.comparators;

import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloEscala implements Comparator<Vuelo> {

	@Override
	public int compare(Vuelo o1, Vuelo o2) {
		return o1.getEscala().compareTo(o2.getEscala());
	}

}
