package com.example.omdb.ratedmovies;

import java.util.List;
import java.util.Optional;

public interface RatedMoviesService
{
	long delete (String userEmail, String movieId);
	
	List <RatedMovie> findAll (String userEmail);
	
	Optional <RatedMovie> findOne (String userEmail, String movieId);
	
	long insert (RatedMovie ratedMovie);
	
	long update (RatedMovie ratedMovie);
}
