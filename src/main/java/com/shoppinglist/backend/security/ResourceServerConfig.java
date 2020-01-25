package com.shoppinglist.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;




@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String SERVER_RESOURCE_ID = "oauth2-resource";
	
	@Override 
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(SERVER_RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new AuthServerConfig.CorsFilter(), ChannelProcessingFilter.class)
			.authorizeRequests()
			.antMatchers("/registration/**")
			.permitAll()
			.antMatchers("/activation/**")
			.permitAll()
			.anyRequest()
			.authenticated();
	}

}
