package com.example.omdb.ratedmovies;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RatedMovie
{
	@Size (max = 63)
	private String userEmail;
	
	@Size (max = 15)
	private String movieId;
	
	@Size (max = 2047)
	private String commentaries;
	
	@Max (10)
	@Min (0)
	private Integer rating;
	
	private Date seen;
}
