package aiss.api;

import java.util.HashSet;

import java.util.Set;
import javax.ws.rs.core.Application;

import aiss.api.resources.ViajeResource;
import aiss.api.resources.VueloResource;

public class ViajeApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public ViajeApplication() {
		singletons.add(ViajeResource.getInstance());
		singletons.add(VueloResource.getInstance());
	}
	
	public Set<Class<?>> getClasses(){
		return classes;
	}
	public Set<Object> getSingletons(){
		return singletons;
	}
}
