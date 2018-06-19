package com.chhaileng.ams.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseConfiguration {

	@Bean
	@Profile("mem")
	public DataSource inMemory() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		return builder.build();
	}
	
	@Bean
	@Profile("db")
	public DataSource dataSource() {
		DriverManagerDataSource db = new DriverManagerDataSource();
		db.setDriverClassName("org.postgresql.Driver");
		db.setUrl("jdbc:postgresql://localhost:5432/ams");
		db.setUsername("postgres");
		db.setPassword("1234");
		return db;
	}
	
}
