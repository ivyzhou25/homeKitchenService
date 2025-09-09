package com.homekitchen.api.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.homekitchen.api.entity.User;

@Repository
public class UserRepository extends RepositoryBase {
	private String FIND_BY_PHONE = "select * from user where phone='%s'";
	private String FIND_BY_EMAIL = "select * from user where email='%s'";
	private String FIND_BY_ID = "select * from user where id='%s'";
	
	private String ADD_USER = "insert into user (first_name, last_name, pass_word, email, phone) values (?,?,?,?,?)";
	
	public List<User> findUserById(Integer id) {
		List<User> result = queryEntity(String.format(FIND_BY_ID, id), User.class);
		return result;
	}
	
	public List<User> findUserByPhone(String phone) {
		List<User> result = queryEntity(String.format(FIND_BY_PHONE, phone), User.class);
		return result;
	}
	
	public List<User> findUserByEmail(String email) {
		List<User> result = queryEntity(String.format(FIND_BY_EMAIL, email), User.class);
		return result;
	}	
	
	public User insertUser(User user) {
		jdbcTemplate.update(ADD_USER, user.getFirstName(), user.getLastName(), user.getPassWord(), user.getEmail(), user.getPhone());
		return findUserByEmail(user.getEmail()).get(0);
	}
}
