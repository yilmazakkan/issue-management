package com.yilmazakkan.issueManagement.service;

import org.springframework.data.domain.Pageable;

import java.util.List;


import com.yilmazakkan.issueManagement.dto.IssueHistoryDto;
import com.yilmazakkan.issueManagement.entity.Issue;
import com.yilmazakkan.issueManagement.util.TPage;

public interface IssueHistoryService {
	
		IssueHistoryDto  save(IssueHistoryDto issueHistory);
	
		IssueHistoryDto getById(Long id);

	    List<IssueHistoryDto> getByIssueId(Long id);

	    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);

	    Boolean delete(IssueHistoryDto issueHistory);

	    void addHistory(Long id, Issue issue);

	
}
