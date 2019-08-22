package com.yilmazakkan.issueManagement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;


import com.yilmazakkan.issueManagement.dto.UserDto;

import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.service.UserService;
import com.yilmazakkan.issueManagement.util.TPage;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	
	
	@Override
    public UserDto save(UserDto user) {
		 User u = modelMapper.map(user, User.class);
	        u = userRepository.save(u);
	        user.setId(u.getId());
	        return user;
    }

	@Override
	public UserDto getById(Long id) {
		User u = userRepository.getOne(id);
        return modelMapper.map(u, UserDto.class);
	}

	 @Override
	    public TPage<UserDto> getAllPageable(Pageable pageable) {
	        Page<User> data = userRepository.findAll(pageable);
	        TPage<UserDto> respnose = new TPage<UserDto>();
	        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
	        return respnose;
	    }
	
	 public List<UserDto> getAll() {
	        List<User> data = userRepository.findAll();
	        return Arrays.asList(modelMapper.map(data, UserDto[].class));
	    }
	
	 
	@Override
	public UserDto getByUsername(String username) {
		 User u = userRepository.findByUsername(username);
	        return modelMapper.map(u, UserDto.class);
	}

}
