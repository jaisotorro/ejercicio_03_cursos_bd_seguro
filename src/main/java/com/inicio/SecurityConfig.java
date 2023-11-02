package com.inicio;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {	
	//roles y usuarios
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetails() throws Exception {
		List<UserDetails> users = List.of(
				User.withUsername("user1")
					.password("{noop}user1")
					.roles("Invitado")
					.build(),
				User.withUsername("user2")
					.password("{noop}user2")
					.roles("Operador")
					.build(),
					User.withUsername("user3")
					.password("{noop}user3")
					.roles("Admin")
					.build(),
					User.withUsername("user4")
					.password("{noop}user4")
					.roles("Operador","Admin")
					.build());
		return new InMemoryUserDetailsManager(users);
	}
	// politica accesos por roles	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST,"/curso")
			.hasRole("Admin")
		.requestMatchers(HttpMethod.DELETE,"/curso/*")
			.hasAnyRole("Operador","Admin")
		.requestMatchers(HttpMethod.PUT,"/curso/**")
			.hasAnyRole("Operador","Admin")
		.requestMatchers(HttpMethod.GET,"/curso/*")
			.authenticated()
		.requestMatchers(HttpMethod.GET,"/cursos")
			.authenticated()
		.requestMatchers(HttpMethod.GET,"/cursos/**")
			.authenticated()
		.and()
		.httpBasic();
		return httpSecurity.build();
	}		

}
