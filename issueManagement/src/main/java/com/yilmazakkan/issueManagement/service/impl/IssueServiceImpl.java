package com.yilmazakkan.issueManagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yilmazakkan.issueManagement.dto.IssueDetailDto;
import com.yilmazakkan.issueManagement.dto.IssueDto;
import com.yilmazakkan.issueManagement.dto.IssueHistoryDto;
import com.yilmazakkan.issueManagement.dto.IssueUpdateDto;
import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Issue;
import com.yilmazakkan.issueManagement.entity.IssueStatus;
import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.entity.User;
import com.yilmazakkan.issueManagement.repository.IssueRepository;
import com.yilmazakkan.issueManagement.repository.ProjectRepository;
import com.yilmazakkan.issueManagement.repository.UserRepository;
import com.yilmazakkan.issueManagement.service.IssueHistoryService;
import com.yilmazakkan.issueManagement.service.IssueService;
import com.yilmazakkan.issueManagement.util.TPage;

@Service
public class IssueServiceImpl implements IssueService{

	
	private final IssueRepository issueRepository;  //final yapıp constructor kullanmamızın sebebi daha sonra başka biri metod içinde issueRepository değiştirmemesi farklı bir değer atamaması exp: null... yapamaz 
	
	private final ModelMapper modelMapper;
	
	private final ProjectRepository projectRepository;
	
	private final UserRepository userRepository;
	
	  private final IssueHistoryService issueHistoryService;
	
	@Autowired //spring 4.0 dan sonra constructor başına @Autowired yazmasakta spring onun inject edileceğini biliyor
	public IssueServiceImpl(IssueRepository issueRepository,UserRepository userRepository, IssueHistoryService issueHistoryService, ModelMapper modelMapper,ProjectRepository projectRepository) {
		this.issueRepository=issueRepository;
		this.modelMapper=modelMapper;
	    this.issueHistoryService = issueHistoryService;
	    this.userRepository =userRepository;
	    this.projectRepository = projectRepository;
	}



	@Override
	public IssueDto save(IssueDto issue) {
		
		issue.setDate(new Date());
		issue.setIssueStatus(IssueStatus.OPEN);  //issue ilk açıldığında Status 
		
		Issue issueDb = modelMapper.map(issue, Issue.class);  // dışardan gelen nesneyi yani "issue" içerdeki modele dönüştür yani "issueDb" dönüştürdü
		
		 issueDb = issueRepository.save(issueDb);   //modeldende kaydettikten sonra
		 
		 return modelMapper.map(issueDb, IssueDto.class);  // issueDb bu sefer bu nesneyi dto dönüştürüp geri ver.
	}



	@Override
	public IssueDto getById(Long id) {
		Issue p = issueRepository.getOne(id);
		return modelMapper.map(p, IssueDto.class);
	}



	  
	public List<IssueDto> getAll() {
		
		List<Issue> data = issueRepository.findAll();
		return Arrays.asList(modelMapper.map(data, IssueDto[].class));
	}



	@Override
	public Boolean delete(Long issueId) {
		 issueRepository.deleteById(issueId);
		 return true;
	}



	@Override
	public IssueDto update(Long id, IssueDto project) {
		
	return null;

		
	}
	
	@Transactional
	public IssueDetailDto update(Long id, IssueUpdateDto issue) {
        
		Issue issueDb = issueRepository.getOne(id);
		User user = userRepository.getOne(issue.getAssignee_id());
		
		issueHistoryService.addHistory(id,issueDb);
		
		issueDb.setAssignee(user);
		issueDb.setDate(issue.getDate());
		issueDb.setDescription(issue.getDescription());
		issueDb.setDetails(issue.getDetails());
		issueDb.setIssueStatus(issue.getIssueStatus());
		issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
		
		issueRepository.save(issueDb);
		return getByIdWithDetails(id);
    }
	
	
	 @Override
	    public TPage<IssueDto> getAllPageable(Pageable pageable) {
	        Page<Issue> data = issueRepository.findAll(pageable);
	        TPage<IssueDto> respnose = new TPage<IssueDto>();
	        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
	        return respnose;
	    }



	public IssueDetailDto getByIdWithDetails(Long id) {
		 Issue issue = issueRepository.getOne(id);
	        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
	        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
	        detailDto.setIssueHistories(issueHistoryDtos);
	        return detailDto;
	}
	 
	 
}
	  

