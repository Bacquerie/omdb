package com.example.omdb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.omdb.omdbapi.OmdbApiService;
import com.example.omdb.ratedmovies.RatedMovie;
import com.example.omdb.ratedmovies.RatedMoviesService;
import com.example.omdb.omdbapi.OmdbApiMovie;

@RestController
@RequestMapping ("/movies")
public class MoviesController
{
	@Autowired
	private OmdbApiService omdbApiService;
	
	@Autowired
	private RatedMoviesService ratedMoviesService;
	
	/// PUBLIC METHODS
	
	@GetMapping ("/search")
	@ResponseStatus (HttpStatus.OK)
	public OmdbApiMovie [] findByTitle (@RequestParam (name = "title") String title)
	{
		return omdbApiService.findByTitle (title);
	}
	
	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteByImdbId (@PathVariable ("id") String id, Principal user)
	{
		ratedMoviesService.delete (user.getName (), id);
	}
	
	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public RatedMovie [] findAllRated (Principal user)
	{
		System.out.println (user.getName ());
		return ratedMoviesService.findAll (user.getName ()).toArray (new RatedMovie [] {});
	}
	
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public void addRatedMovie (@RequestBody @Validated RatedMovie movie, Principal user)
	{
		if (!ratedMoviesService.findOne (user.getName (), movie.getMovieId ()).isPresent ())
		{
			movie.setUserEmail (user.getName ());
			
			ratedMoviesService.insert (movie);
		}
	}
	
	@PutMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void updateRatedMovie (
		@PathVariable ("id") String id,
		@RequestBody @Validated RatedMovie movie,
		Principal user)
	{
		movie.setUserEmail (user.getName ());
		movie.setMovieId (id);
		
		if (ratedMoviesService.findOne (user.getName (), id).isPresent ())
		{
			ratedMoviesService.update (movie);
		}
	}
}
