package com.example.omdb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Data;

@ConfigurationProperties ("omdb")
@Data
@Service
public class ApplicationConfig
{
	private Api api;
	private Movies movies;
	
	@Data
	public static class Api
	{
		private String baseUrl;
		private String key;
	}
	
	@Data
	public static class Movies
	{
		private Sql sql;
		
		@Data
		public static class Sql
		{
			private String delete;
			private String insert;
			private String selectAll;
			private String selectOne;
			private String update;
		}
	}
}
