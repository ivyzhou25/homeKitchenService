package com.homekitchen.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.homekitchen.api.entity.OrderItem;

@Repository
public class OrderItemRepository extends RepositoryBase {
	private String FIND_BY_ID = "select * from order_item where id=%s";
	private String FIND_ALL_BY_ORDER_ID = "select * from order_item where order_id=%s";
	
	private String ADD_ORDER_ITEM = "insert into order_item (dish_id, order_id, unit_price, count) values (?,?,?,?)";
	
	// custom query to find all dish
	public List<OrderItem> findById(Integer id) {
		List<OrderItem> result = queryEntity(String.format(FIND_BY_ID, id), OrderItem.class);
		return result;
	}
	
	public List<OrderItem> findByOrder(Integer orderId) {
		List<OrderItem> result = queryEntity(String.format(FIND_ALL_BY_ORDER_ID, orderId), OrderItem.class);
		return result;
	}
	
	public void insertOrderItem(OrderItem orderItem) {
		jdbcTemplate.update(ADD_ORDER_ITEM, orderItem.getDishId(), orderItem.getOrder().getId(), orderItem.getUnitPrice(), orderItem.getCount());
	}
}
