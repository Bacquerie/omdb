package com.example.omdb.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OmdbApiSearchResult
{
	@JsonProperty ("Search")
	private OmdbApiMovie [] search;
}
