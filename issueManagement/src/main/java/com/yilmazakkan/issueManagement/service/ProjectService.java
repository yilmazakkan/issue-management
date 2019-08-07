package com.yilmazakkan.issueManagement.service;

import java.util.List;


import org.springframework.data.domain.Pageable;

import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.util.TPage;




public interface ProjectService {
	
	ProjectDto save(ProjectDto project);
	
	ProjectDto getById(Long id); 
	
	Project getByProjectCode(String projectCode);
	
	List<Project> getByProjectContains(String projectCode);
	
	ProjectDto update(Long id, ProjectDto project);
		
	TPage<ProjectDto> getAllPageable(Pageable pageable);
	
	Boolean delete(Project project);

	
}
