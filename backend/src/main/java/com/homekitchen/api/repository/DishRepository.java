package com.homekitchen.api.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.Dish;

@Repository
public class DishRepository extends RepositoryBase {
	private String FIND_BY_ID = "select * from dish where id=%s";
	private String FIND_ALL_BY_NAME = "select * from dish where name='%s'";
	private String FIND_ALL_BY_BIZ = "select * from dish where biz_id=%s";
	
	private String ADD_DISH = "insert into dish (name, cuisine, description, price, price_start, biz_id) values (?,?,?,?,?,?)";
	private String FIND_ALL = "select * from dish where biz_id is not NULL";
	
	// custom query to find all dish
	public List<Dish> findById(Integer id) {
		List<Dish> result = queryEntity(String.format(FIND_BY_ID, id), Dish.class);
		return result;
	}
	
	public List<Dish> findByName(String name) {
		List<Dish> result = queryEntity(String.format(FIND_ALL_BY_NAME, name), Dish.class);
		return result;
	}
	
	public List<Dish> findByBiz(Integer bizId) {
		List<Dish> result = queryEntity(String.format(FIND_ALL_BY_BIZ, bizId), Dish.class);
		return result;
	}
	
	public void insertDish(Dish dish) {
		jdbcTemplate.update(ADD_DISH, dish.getName(), dish.getCuisine(), dish.getDescription(), dish.getPrice(), dish.getPriceStart(), dish.getBizId());
	}

	public List<Dish> findAll() {
		List<Dish> result = queryEntity(String.format(FIND_ALL ), Dish.class);
		return result;
	}
}
