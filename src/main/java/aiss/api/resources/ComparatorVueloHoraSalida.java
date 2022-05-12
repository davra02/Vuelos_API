package aiss.api.resources;

import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloHoraSalida implements Comparator<Vuelo> {

	@Override
	public int compare(Vuelo o1, Vuelo o2) {
		return o1.getHoraSalida().compareTo(o2.getHoraSalida());
	}

}