package aiss.api.comparators;

import java.util.Comparator;

import aiss.model.Avion;

public class ComparatorAvionModelo implements Comparator<Avion>{
	
	public int compare(Avion o1, Avion o2) {
		return o1.getModelo().compareTo(o2.getModelo());
	}

}
