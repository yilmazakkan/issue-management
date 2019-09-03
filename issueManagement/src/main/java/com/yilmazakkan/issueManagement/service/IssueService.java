package com.yilmazakkan.issueManagement.service;




import org.springframework.data.domain.Pageable;

import com.yilmazakkan.issueManagement.dto.IssueDto;
import com.yilmazakkan.issueManagement.util.TPage;


public interface IssueService {
	
	IssueDto save(IssueDto issue);
	
	IssueDto getById(Long id); 
	
	TPage<IssueDto> getAllPageable(Pageable pageable);
	
	Boolean delete(Long issue);
	
	IssueDto update(Long id, IssueDto project);


}
