create table user_rates
(
	user_email varchar(63) not null,
	movie_id varchar(15) not null,
	
	commentaries varchar(2047),
	rating integer,
	seen date,
	
	primary key (user_email, movie_id)
);