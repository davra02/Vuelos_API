package aiss.api.resources;

import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloPrecio implements Comparator<Vuelo> {

	@Override
	public int compare(Vuelo o1, Vuelo o2) {
		
		return o1.getPrecio().compareTo(o2.getPrecio());
	}

}
