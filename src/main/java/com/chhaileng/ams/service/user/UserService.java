package com.chhaileng.ams.service.user;

import java.util.List;

import com.chhaileng.ams.entity.User;

public interface UserService {
	void save(User user);
	User findOne(Integer id);
	List<User> findAll();
}
