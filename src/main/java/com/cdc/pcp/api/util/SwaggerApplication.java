package com.cdc.pcp.api.util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.cdc.pcp.api.resource.DocumentResource;
import com.cdc.pcp.api.resource.PreferencesResource;
import com.cdc.pcp.api.resource.SampleResource;

public class SwaggerApplication extends Application {

	HashSet<Object> singletons = new HashSet<Object>();

	public SwaggerApplication() {
		singletons.add(new DocumentResource());
		singletons.add(new PreferencesResource());
		singletons.add(new SampleResource());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>();
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
