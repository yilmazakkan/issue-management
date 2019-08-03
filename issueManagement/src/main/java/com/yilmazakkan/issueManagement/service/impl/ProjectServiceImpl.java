package com.yilmazakkan.issueManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.repository.ProjectRepository;
import com.yilmazakkan.issueManagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project save(Project project) {
		if(project.getProjectCode()==null)
		{
			throw new IllegalArgumentException("Project code connot be null !!!");
		}
		
		 project = projectRepository.save(project);
		 return project;
	}

	@Override
	public Project getById(Long id) {
		
		return projectRepository.getOne(id);
	}

	@Override
	public List<Project> getByProjectCode(String projectCode) {
		
		return null;
	}

	@Override
	public List<Project> getByProjectContains(String projectCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Project> getAllPageable(Pageable pageable) {
		
		return projectRepository.findAll(pageable);
	}

	@Override
	public Boolean delete(Project project) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}