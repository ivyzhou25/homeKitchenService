package com.homekitchen.api.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RepositoryBase {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected <T> List<T> queryEntity(String sql, Class<T> obj) {
		BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<>(obj);
		mapper.setPrimitivesDefaultedForNullValue(true);
		List<T> result = jdbcTemplate.query(sql, mapper);
		return result;
	}
	
	protected Date getSqlToday() {
		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);
		return sqlDate;
	}
	
}
