package com.yilmazakkan.issueManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.UserDto;
import com.yilmazakkan.issueManagement.service.impl.UserServiceImpl;
import com.yilmazakkan.issueManagement.util.ApiPaths;
import com.yilmazakkan.issueManagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "User APIs")
@CrossOrigin( origins = "http://localhost:4200")
public class UserController {

	
	
	
	private final UserServiceImpl userServiceImpl;
	
	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	
	  @GetMapping("/pagination")
	    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
	    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable) {
	        TPage<UserDto> data = userServiceImpl.getAllPageable(pageable);
	        return ResponseEntity.ok(data);
	    }

	 @GetMapping()
	    @ApiOperation(value = "Get All By Operation", response = UserDto.class)
	    public ResponseEntity<List<UserDto>> getAll() {
	        List<UserDto> data = userServiceImpl.getAll();
	        return ResponseEntity.ok(data);
	    }
	 
	@GetMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = UserDto.class)
	public ResponseEntity<UserDto> getById(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		UserDto user = userServiceImpl.getById(id);
		return ResponseEntity.ok(user);
	}
	
	 @PostMapping
	    @ApiOperation(value = "Create Operation", response = UserDto.class)
	    public ResponseEntity<UserDto> createProject(@Valid @RequestBody UserDto user) {
	        return ResponseEntity.ok(userServiceImpl.save(user));
	    }
	
	
	
	
}
