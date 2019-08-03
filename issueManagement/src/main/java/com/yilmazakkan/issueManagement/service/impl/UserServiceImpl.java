package com.yilmazakkan.issueManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		if (user.getEmail() == null) {
			throw new IllegalArgumentException("User e-mail cannot be null");
		}
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User getById(Long id) {
		
		return userRepository.getOne(id);
	}

	@Override
	public Page<User> getAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User getByUsername(String username) {
	
		return userRepository.findByUsername(username);
	}

}
