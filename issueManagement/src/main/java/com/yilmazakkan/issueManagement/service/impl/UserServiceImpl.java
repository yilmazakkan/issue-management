package com.yilmazakkan.issueManagement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yilmazakkan.issueManagement.dto.RegistrationRequest;
import com.yilmazakkan.issueManagement.dto.UserDto;
import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.service.UserService;
import com.yilmazakkan.issueManagement.util.TPage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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

	@Transactional
	public Boolean register(RegistrationRequest registrationRequest) {
		try {
			User user = new User();
			user.setEmail(registrationRequest.getEmail());
			user.setNameSurname(registrationRequest.getNameSurname());
			user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
			user.setUsername(registrationRequest.getUsername());
			userRepository.save(user);
			return Boolean.TRUE;
		} catch (Exception e) {
			log.error("REGISTRATION=>", e);
			return Boolean.FALSE;
		}
	}

}
