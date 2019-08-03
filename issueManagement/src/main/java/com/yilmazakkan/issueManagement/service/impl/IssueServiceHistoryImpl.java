package com.yilmazakkan.issueManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.yilmazakkan.issueManagement.entity.IssueHistory;
import com.yilmazakkan.issueManagement.repository.IssueHistoryRepository;
import com.yilmazakkan.issueManagement.service.IssueServiceHistory;

@Service
public class IssueServiceHistoryImpl implements IssueServiceHistory {

	@Autowired
	private IssueHistoryRepository  issueHistoryRepository;
	
	@Override
	public IssueHistory save(IssueHistory issueHistory) {
	
		if (issueHistory.getDate() == null) {
			throw new IllegalArgumentException("Issue connot be null");
		}
		issueHistory = issueHistoryRepository.save(issueHistory);
		return issueHistory;
	}

	@Override
	public IssueHistory getById(Long id) {
		
		return issueHistoryRepository.getOne(id);
	}

	@Override
	public Page<IssueHistory> getAllPageable(Pageable pageable) {
		
		return issueHistoryRepository.findAll(pageable);
	}

	@Override
	public Boolean delete(IssueHistory issueHistory) {
		issueHistoryRepository.delete(issueHistory);
		return Boolean.TRUE;
	}

}
