package com.homekitchen.api.repository;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.UserOrder;

@Repository
public class OrderRepository extends RepositoryBase {
	private String FIND_BY_ID = "select * from UserOrder where id=%s";
	private String FIND_ALL_BY_USER_ID = "select * from UserOrder where userId=%s";
	private String FIND_LAST_BY_USER_ID = "select * from UserOrder where userId=%s ORDER BY id DESC LIMIT 1";
	
	private String ADD_ORDER = "insert into UserOrder (userId, deliveryCost, orderDate) values (?,?,?)";
	
	// custom query to find all dish
	public List<UserOrder> findOrderById(Integer id) {
		List<UserOrder> result = queryEntity(String.format(FIND_BY_ID, id), UserOrder.class);
		return result;
	}
	
	public List<UserOrder> findOrderByUser(Integer userId) {
		List<UserOrder> result = queryEntity(String.format(FIND_ALL_BY_USER_ID, userId), UserOrder.class);
		return result;
	}
	
	public List<UserOrder> findLastOrderByUser(Integer userId) {
		List<UserOrder> result = queryEntity(String.format(FIND_LAST_BY_USER_ID, userId), UserOrder.class);
		return result;
	}
	
	public void insertOrder(UserOrder order) {
		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);
		jdbcTemplate.update(ADD_ORDER, order.getUserId(), order.getDeliveryCost(), sqlDate);
	}
}
