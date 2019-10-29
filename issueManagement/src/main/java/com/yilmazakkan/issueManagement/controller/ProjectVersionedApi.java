package com.yilmazakkan.issueManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.service.impl.ProjectServiceImpl;
import com.yilmazakkan.issueManagement.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/versioning")
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@CrossOrigin
public class ProjectVersionedApi {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@GetMapping(value = "/{id}" , params = "version=1")
	@ApiOperation(value = "Get By Id Operation V1", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> getByIdV1(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		ProjectDto projectDto = projectServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
	
	}
	
	@GetMapping(value = "/{id}", params = "version=2")
	@ApiOperation(value = "Get By Id Operation V2", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> getByIdV2(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		ProjectDto projectDto = projectServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
	
	}

}
