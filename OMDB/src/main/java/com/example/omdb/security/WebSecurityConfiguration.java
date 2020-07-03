package com.example.omdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.inMemoryAuthentication ()
				.withUser ("alejandrobacquerie@gmail.com")
				.password (encoder.encode ("bacquerie"))
				.roles ("USER")
			.and ()
				.withUser ("otro@mail.com")
				.password (encoder.encode ("admin"))
				.roles ("ADMIN");
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception
	{
		http
			.csrf ()
				.disable ()
			.httpBasic ()
			.and ()
			.authorizeRequests ()
				.antMatchers ("/**")
				.fullyAuthenticated ()
			.and ()
			.formLogin ()
				.disable ();
	}
}
