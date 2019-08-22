package com.yilmazakkan.issueManagement.service.impl;


import java.util.Arrays;
import java.util.List;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.ProjectRepository;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.service.ProjectService;
import com.yilmazakkan.issueManagement.util.TPage;


@Service
public class ProjectServiceImpl implements ProjectService {

	 private final ProjectRepository projectRepository;
	    private final ModelMapper modelMapper;
	    private final UserRepository userRepository;

	    public ProjectServiceImpl(ProjectRepository projectRepository,UserRepository userRepository, ModelMapper modelMapper) {
	        this.projectRepository = projectRepository;
	        this.modelMapper = modelMapper;
	        this.userRepository= userRepository;
	    }
	
	@Override
	public ProjectDto save(ProjectDto project) {
		//Business Logic
		Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());
		if (projectCheck!=null) {
			throw new IllegalArgumentException("Project Code Already Exist");
		}
		 Project p = modelMapper.map(project, Project.class);
		 User user = userRepository.getOne(project.getManagerId());
	        p.setManager(user);

	        p = projectRepository.save(p);
	        project.setId(p.getId());
	        return project;
	}

	@Override
	public ProjectDto getById(Long id) {
		
		Project p = projectRepository.getOne(id);
		return modelMapper.map(p, ProjectDto.class);
	}

	@Override
	public Project getByProjectCode(String projectCode) {
		
		return null;
	}

	@Override
	public List<Project> getByProjectContains(String projectCode) {
		
		return null;
	}

	

	@Override
	public Boolean delete(Project project) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean delete(Long id) {
		 projectRepository.deleteById(id);
		 return true;
	}

	@Override
	public ProjectDto update(Long id, ProjectDto project) {
		
		//Valid
		Project projectDb= projectRepository.getOne(id);
		if (projectDb == null)
		{
			throw new IllegalArgumentException("Project Does Not Exist ID:" +id);
		}
		//Business Logic
		//getByProjectCodeAndIdNot  project codu şu olan ve id düzenlediğimiz id olmayan ve bize prametre gelen code varsa var hatası alırız.onu çözdük
		Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(),id);
		
		if (projectCheck!=null) {
			throw new IllegalArgumentException("Project Code Already Exist");
		}
		
		projectDb.setProjectCode(project.getProjectCode());
		projectDb.setProjectName(project.getProjectName());
		
		projectRepository.save(projectDb);
		return modelMapper.map(projectDb, ProjectDto.class);
	}

	@Override
	public TPage<ProjectDto> getAllPageable(Pageable pageable) {
		Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> respnose = new TPage<ProjectDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return respnose;
	}



	public List<ProjectDto> getAll() {
        List<Project> data = projectRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ProjectDto[].class));
    }


	

	
	
}