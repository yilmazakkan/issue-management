package com.yilmazakkan.issueManagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.yilmazakkan.issueManagement.dto.IssueDto;
import com.yilmazakkan.issueManagement.dto.ProjectDto;
import com.yilmazakkan.issueManagement.entity.Issue;
import com.yilmazakkan.issueManagement.entity.Project;
import com.yilmazakkan.issueManagement.repository.IssueRepository;
import com.yilmazakkan.issueManagement.service.IssueService;
import com.yilmazakkan.issueManagement.util.TPage;

@Service
public class IssueServiceImpl implements IssueService{

	
	private final IssueRepository issueRepository;  //final yapıp constructor kullanmamızın sebebi daha sonra başka biri metod içinde issueRepository değiştirmemesi farklı bir değer atamaması exp: null... yapamaz 
	
	private final ModelMapper modelMapper;
	
	@Autowired //spring 4.0 dan sonra constructor başına @Autowired yazmasakta spring onun inject edileceğini biliyor
	public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
		this.issueRepository=issueRepository;
		this.modelMapper=modelMapper;
	}



	@Override
	public IssueDto save(IssueDto issue) {
		if (issue.getDate() == null) {
			
			throw new IllegalArgumentException("Issue date cannot be null!! ");
		}		
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
	
	 @Override
	    public TPage<IssueDto> getAllPageable(Pageable pageable) {
	        Page<Issue> data = issueRepository.findAll(pageable);
	        TPage<IssueDto> respnose = new TPage<IssueDto>();
	        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
	        return respnose;
	    }
}



	


	


	
