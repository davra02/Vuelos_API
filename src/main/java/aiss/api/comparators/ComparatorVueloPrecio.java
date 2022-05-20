package aiss.api.comparators;

import java.util.Comparator;

import aiss.model.Vuelo;

public class ComparatorVueloPrecio implements Comparator<Vuelo> {

	@Override
	public int compare(Vuelo o1, Vuelo o2) {
		
		Integer precio1 = Integer.parseInt(o1.getPrecio());
		Integer precio2 = Integer.parseInt(o2.getPrecio());
		return precio1.compareTo(precio2);
	}

}
