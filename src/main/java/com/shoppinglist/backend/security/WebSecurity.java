package com.shoppinglist.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shoppinglist.backend.model.Role;
import com.shoppinglist.backend.repository.UserRepository;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserRepository userRepo;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(passwordEncoder);
	}

	
	@Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/oauth/**", "/oauth/token/revokeById/**", "/tokens/**","/registration","/logout").permitAll()
                .antMatchers("/api/users", "/api/users/**").hasAnyAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().csrf().disable();
    }
	

}
