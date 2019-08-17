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
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.IssueDto;
import com.yilmazakkan.issueManagement.service.impl.IssueServiceImpl;
import com.yilmazakkan.issueManagement.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
public class IssueController {

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
	private final IssueServiceImpl issueServiceImpl;
	
	public IssueController(IssueServiceImpl issueServiceImpl) {
		this.issueServiceImpl = issueServiceImpl;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		IssueDto issueDto = issueServiceImpl.getById(id);
		return ResponseEntity.ok(issueDto);
	
	}
	
	@PostMapping
	@ApiOperation(value = "Create Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto>createProject(@Valid @RequestBody IssueDto project){
		
		return ResponseEntity.ok(issueServiceImpl.save(project));
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody IssueDto  project){   // Update Put işlemi için ID vermemiz gerekior PathVarible ile ve dto isticez
																													/*save gönderip insert update handle etmesini bekleyebiliriz ama bu SOLID prensiplerine uymaz
																													SOLID presiplerinin ilki single responsibility her method veya her işlem kendi görevini yapmalı başka bir metodun görevini yüklememeliyiz*/
		return ResponseEntity.ok(issueServiceImpl.update(id,project));
			
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Operation", response = Boolean.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value = "id" ,required =  true) Long id){
		
		return ResponseEntity.ok(issueServiceImpl.delete(id));
		
	}
	
	
	
}