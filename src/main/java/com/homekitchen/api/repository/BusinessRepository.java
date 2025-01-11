package com.homekitchen.api.repository;

import java.util.List;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.Business;

@Repository
public class BusinessRepository extends RepositoryBase {
	private String FIND_BIZ_BY_USER = "select * from Business where userId=%s";
	private String FIND_BIZ_BY_NAME = "select * from Business where bizName='%s'";
	private String FIND_BIZ_BY_Id = "select * from Business where id=%s";
	
	private String ADD_BUSINESS = "insert into Business (bizName, bizAddr, bizCity, bizState, bizZipcode, description, userId) values (?,?,?,?,?,?,?)";
	
	public List<Business> findByUser(int userId) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_USER, userId), Business.class);
		return result;
	}
	
	public List<Business> findByName(String name) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_NAME, name), Business.class);
		return result;
	}
	
	public List<Business> findById(Integer bizId) {
		List<Business> result = queryEntity(String.format(FIND_BIZ_BY_Id, bizId), Business.class);
		return result;
	}
	
	public void insertBusiness(Business biz) {
		jdbcTemplate.update(ADD_BUSINESS, biz.getBizName(), biz.getBizAddr(), biz.getBizCity(), biz.getBizState(), biz.getBizZipcode(), biz.getDescription(), biz.getUserId());
	}
}
