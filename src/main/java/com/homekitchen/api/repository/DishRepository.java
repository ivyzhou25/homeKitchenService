package com.homekitchen.api.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.Dish;

@Repository
public class DishRepository extends RepositoryBase {
	private String FIND_BY_ID = "select * from Dish where id=%s";
	private String FIND_ALL_BY_NAME = "select * from Dish where name='%s'";
	private String FIND_ALL_BY_BIZ = "select * from Dish where bizId=%s";
	
	private String ADD_DISH = "insert into Dish (name, cuisine, description, price, priceStart, bizId) values (?,?,?,?,?,?)";
	private String FIND_ALL = "select * from Dish where bizId is not NULL";
	
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
