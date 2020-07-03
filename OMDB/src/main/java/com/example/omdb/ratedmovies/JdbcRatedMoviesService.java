package com.example.omdb.ratedmovies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.omdb.ApplicationConfig;

@Service
public class JdbcRatedMoviesService implements RatedMoviesService
{
	@Autowired
	private ApplicationConfig config;
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public long delete (String userEmail, String movieId)
	{
		return jdbc.update (
			config.getMovies ().getSql ().getDelete (),
			userEmail,
			movieId
		);
	}

	@Override
	public List <RatedMovie> findAll (String userEmail)
	{
		return jdbc.query (
			config.getMovies ().getSql ().getSelectAll (),
			this::ratedMovieMapper,
			userEmail
		);
	}
	
	@Override
	public Optional <RatedMovie> findOne (String userEmail, String movieId)
	{
		try
		{
			return Optional.of (jdbc.queryForObject (
				config.getMovies ().getSql ().getSelectOne (),
				this::ratedMovieMapper,
				userEmail,
				movieId
			));
		}
		
		catch (Exception e)
		{
			return Optional.ofNullable (null);
		}
	}
	
	@Override
	public long insert (RatedMovie ratedMovie)
	{
		return jdbc.update (
			config.getMovies ().getSql ().getInsert (),
			ratedMovie.getUserEmail (),
			ratedMovie.getMovieId (),
			ratedMovie.getCommentaries (),
			ratedMovie.getRating (),
			ratedMovie.getSeen ()
		);
	}
	
	@Override
	public long update (RatedMovie ratedMovie)
	{
		return jdbc.update (
			config.getMovies ().getSql ().getUpdate (),
			ratedMovie.getCommentaries (),
			ratedMovie.getRating (),
			ratedMovie.getSeen (),
			ratedMovie.getUserEmail (),
			ratedMovie.getMovieId ()
		);
	}
	
	/// PRIVATE METHODS
	
	private RatedMovie ratedMovieMapper (ResultSet rs, int rowNumber) throws SQLException
	{
		return RatedMovie
			.builder ()
			.userEmail (rs.getString ("user_email"))
			.movieId (rs.getString ("movie_id"))
			.commentaries (rs.getString ("commentaries"))
			.rating (rs.getInt ("rating"))
			.seen (rs.getDate ("seen"))
			.build ();
	}
}
