package com.yilmazakkan.issueManagement.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.yilmazakkan.issueManagement.entity.IssueHistory;

public interface IssueServiceHistory {
	
	IssueHistory save(IssueHistory issueHistory);
	
	IssueHistory getById(Long id); 
	
	Page<IssueHistory> getAllPageable(Pageable pageable);
	
	Boolean delete(IssueHistory issueHistory);

	
}
