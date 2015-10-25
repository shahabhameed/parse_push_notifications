package com.folio3.configuration;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration extends ResourceConfig {
	
	public RestConfiguration() {
		packages("com.folio3.resource");
        register(RequestContextFilter.class);
        register(LoggingFilter.class);
    }
}
