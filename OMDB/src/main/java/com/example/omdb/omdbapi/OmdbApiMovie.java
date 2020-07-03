package com.example.omdb.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OmdbApiMovie
{
	@JsonProperty ("imdbID")
	private String id;
	
	@JsonProperty ("Title")
	private String title;
	
	@JsonProperty ("Year")
	private String year;
}
