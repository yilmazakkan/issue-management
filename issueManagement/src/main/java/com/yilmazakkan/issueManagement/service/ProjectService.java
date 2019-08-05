package com.yilmazakkan.issueManagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Project;




public interface ProjectService {
	
	ProjectDto save(ProjectDto project);
	
	ProjectDto getById(Long id); 
	
	Project getByProjectCode(String projectCode);
	
	List<Project> getByProjectContains(String projectCode);
	
	ProjectDto update(Long id, ProjectDto project);
		
	Page<Project> getAllPageable(Pageable pageable);
	
	Boolean delete(Project project);
}
