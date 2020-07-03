package com.example.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
@SpringBootApplication
public class Application
{
	@Bean
	public PasswordEncoder encoder ()
	{
		return new BCryptPasswordEncoder ();
	}
	
	@Bean
	public RestTemplate rest ()
	{
		return new RestTemplate ();
	}

	public static void main (String [] args)
	{
		SpringApplication.run (Application.class, args);
	}	
}
