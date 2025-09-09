package com.homekitchen.api.repository;

import java.util.List;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.Business;

@Repository
public class BusinessRepository extends RepositoryBase {
	private String FIND_BIZ_BY_USER = "select * from business where user_id=%s";
	private String FIND_BIZ_BY_NAME = "select * from business where biz_name='%s'";
	private String FIND_BIZ_BY_ID = "select * from business where id=%s";
	private String FIND_ALL = "select * from business";
	
	private String ADD_BUSINESS = "insert into business (biz_name, biz_addr, biz_city, biz_state, biz_zipcode, description, user_id) values (?,?,?,?,?,?,?)";
	
	public List<Business> findAll() {
		return queryEntity(FIND_ALL, Business.class);
	}

	public List<Business> findByUser(int userId) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_USER, userId), Business.class);
		return result;
	}
	
	public List<Business> findByName(String name) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_NAME, name), Business.class);
		return result;
	}
	
	public List<Business> findById(Integer bizId) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_ID, bizId), Business.class);
		return result;
	}
	
	public void insertBusiness(Business biz) {
		jdbcTemplate.update(ADD_BUSINESS, biz.getBizName(), biz.getBizAddr(), biz.getBizCity(), biz.getBizState(), biz.getBizZipcode(), biz.getDescription(), biz.getUserId());
	}
}
