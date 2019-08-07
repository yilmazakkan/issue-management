package com.yilmazakkan.issueManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.service.impl.ProjectServiceImpl;
import com.yilmazakkan.issueManagement.util.ApiPaths;
import com.yilmazakkan.issueManagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@Slf4j
public class ProjectController {

	/**
	 * 	Http Methodları
	 *  GET
	 *  POST
	 *  PUT
	 *  DELETE
	 *  
	 *  ResponseEntity bizim tüm webservisleri içerisinde geliştirceğimiz  methodlerımız ve Restcontrolerımızın dışarda ortak bir imzaya sahip olması 
	 *   ayrıca her entity mizin üzerinde  Http statusleri belirlemede ve mesaj vermede kolaylık sağlar.
	 */
	
	@Autowired
	private final ProjectServiceImpl projectServiceImpl;
	
	public ProjectController(ProjectServiceImpl projectServiceImpl) {
		this.projectServiceImpl = projectServiceImpl;
	}
	
	
	@GetMapping("/pagination")
	@ApiOperation(value = "Get By Id Pagination Operation", response = ProjectDto.class)
	 public ResponseEntity<TPage<ProjectDto>> getAllPageable (Pageable pageable) {
		TPage<ProjectDto> data = projectServiceImpl.getAllPageable(pageable);
		return ResponseEntity.ok(data);
    
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> getById(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		log.info("ProjectController-> GetByID");
		
		log.debug("ProjectController-> GetByID -> PARAM: " +id);
		ProjectDto projectDto = projectServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
	
	}
	
	@PostMapping
	@ApiOperation(value = "Create Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto>createProject(@Valid @RequestBody ProjectDto project){
		
		return ResponseEntity.ok(projectServiceImpl.save(project));
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDto  project){   // Update Put işlemi için ID vermemiz gerekior PathVarible ile ve dto isticez
																													/*save gönderip insert update handle etmesini bekleyebiliriz ama bu SOLID prensiplerine uymaz
																													SOLID presiplerinin ilki single responsibility her method veya her işlem kendi görevini yapmalı başka bir metodun görevini yüklememeliyiz*/
		return ResponseEntity.ok(projectServiceImpl.update(id,project));
			
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Operation", response = Boolean.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value = "id" ,required =  true) Long id){
		
		return ResponseEntity.ok(projectServiceImpl.delete(id));
		
	}
	
	
	
}
