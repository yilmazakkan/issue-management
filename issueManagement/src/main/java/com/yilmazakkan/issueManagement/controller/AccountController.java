package com.yilmazakkan.issueManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.LoginRequest;
import com.yilmazakkan.issueManagement.dto.RegistrationRequest;
import com.yilmazakkan.issueManagement.dto.TokenResponse;
import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.security.JwtTokenUtil;
import com.yilmazakkan.issueManagement.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/token")
public class AccountController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final User user = userRepository.findByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest)
			throws AuthenticationException {
		Boolean response = userService.register(registrationRequest);
		return ResponseEntity.ok(response);
	}
}
