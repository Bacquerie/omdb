package com.example.omdb.omdbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.omdb.ApplicationConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OmdbApiService
{
	@Autowired
	private ApplicationConfig config;
	
	@Autowired
	private RestTemplate rest;
	
	public OmdbApiMovie [] findByTitle (String title)
	{
		String uri = UriComponentsBuilder
			.fromUriString (config.getApi ().getBaseUrl ())
			.queryParam ("apiKey", config.getApi ().getKey ())
			.queryParam ("s", title)
			.toUriString ();
		
		log.debug ("Requesting URI: " + uri);
		
		ResponseEntity <OmdbApiSearchResult> response = getMovies (uri);
		
		log.debug ("Reponded with status: " + response.getStatusCode ());
		
		if (response.getStatusCode ().is2xxSuccessful ())
		{
			return response.getBody ().getSearch ();
		}
		
		return null;
	}
	
	public ResponseEntity <OmdbApiSearchResult> getMovies (String uri)
	{
		return rest.getForEntity (uri, OmdbApiSearchResult.class);
	}
}
