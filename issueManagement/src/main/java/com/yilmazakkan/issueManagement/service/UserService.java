package com.yilmazakkan.issueManagement.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import com.yilmazakkan.issueManagement.entity.User;

public interface UserService {
	
	User save(User user);
	
	User getById(Long id); 
	
	Page<User> getAllPageable(Pageable pageable);
	
	User getByUsername(String username);
}
