package com.yilmazakkan.issueManagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import com.yilmazakkan.issueManagement.entity.Project;




public interface ProjectService {
	
	Project save(Project project);
	
	Project getById(Long id); 
	
	List<Project> getByProjectCode(String projectCode);
	
	List<Project> getByProjectContains(String projectCode);
	
		
	Page<Project> getAllPageable(Pageable pageable);
	
	Boolean delete(Project project);
}
