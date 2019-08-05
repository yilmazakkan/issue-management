package com.yilmazakkan.issueManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.service.impl.ProjectServiceImpl;

@RestController
@RequestMapping("/project")
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
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> getById(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		ProjectDto projectDto = projectServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
	
	}
	
	@PostMapping
	public ResponseEntity<ProjectDto>createProject(@Valid @RequestBody ProjectDto project){
		
		return ResponseEntity.ok(projectServiceImpl.save(project));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDto  project){   // Update Put işlemi için ID vermemiz gerekior PathVarible ile ve dto isticez
																													/*save gönderip insert update handle etmesini bekleyebiliriz ama bu SOLID prensiplerine uymaz
																													SOLID presiplerinin ilki single responsibility her method veya her işlem kendi görevini yapmalı başka bir metodun görevini yüklememeliyiz*/
		return ResponseEntity.ok(projectServiceImpl.update(id,project));
			
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable(value = "id" ,required =  true) Long id){
		
		return ResponseEntity.ok(projectServiceImpl.delete(id));
		
	}
	
	
	
}
