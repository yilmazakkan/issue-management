package com.yilmazakkan.issueManagement.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazakkan.issueManagement.dto.IssueDetailDto;
import com.yilmazakkan.issueManagement.dto.IssueDto;
import com.yilmazakkan.issueManagement.dto.IssueUpdateDto;
import com.yilmazakkan.issueManagement.entity.IssueStatus;
import com.yilmazakkan.issueManagement.service.impl.IssueServiceImpl;
import com.yilmazakkan.issueManagement.util.ApiPaths;
import com.yilmazakkan.issueManagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
@CrossOrigin
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
	
	@GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable (value = "id",required = true) Long id){  //required = true null bir id geldiğinde islem yapmaya calısmasın.
		IssueDto issueDto = issueServiceImpl.getById(id);
		return ResponseEntity.ok(issueDto);
	
	}
	
	@GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id) {
        IssueDetailDto detailDto = issueServiceImpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }
	
	@PostMapping
	@ApiOperation(value = "Create Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto>createProject(@Valid @RequestBody IssueDto project){
		
		return ResponseEntity.ok(issueServiceImpl.save(project));
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Operation", response = IssueDto.class)
	public ResponseEntity<IssueDetailDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody IssueUpdateDto  issue){   // Update Put işlemi için ID vermemiz gerekior PathVarible ile ve dto isticez
																													/*save gönderip insert update handle etmesini bekleyebiliriz ama bu SOLID prensiplerine uymaz
																													SOLID presiplerinin ilki single responsibility her method veya her işlem kendi görevini yapmalı başka bir metodun görevini yüklememeliyiz*/
	 
		return ResponseEntity.ok(issueServiceImpl.update(id, issue));
			
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Operation", response = Boolean.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value = "id" ,required =  true) Long id){
		
		return ResponseEntity.ok(issueServiceImpl.delete(id));
		
	}
	@GetMapping("/statuses")
	@ApiOperation(value="Get All Issue Statuses Operation", response = String.class, responseContainer = "List")
	public ResponseEntity<List<IssueStatus>> getAll(){
		
		return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
	}
	
	
}
