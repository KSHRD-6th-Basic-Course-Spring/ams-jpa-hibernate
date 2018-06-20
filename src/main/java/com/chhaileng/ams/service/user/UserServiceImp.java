package com.chhaileng.ams.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.User;
import com.chhaileng.ams.repository.jparepository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
}
