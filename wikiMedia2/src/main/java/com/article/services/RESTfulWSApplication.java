package main.java.com.article.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * This class represents RESTful web services application.
 */
@ApplicationPath("main")
public class RESTfulWSApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> classes = new HashSet<>();
		classes.add(Search.class);
		return classes;
	}

}