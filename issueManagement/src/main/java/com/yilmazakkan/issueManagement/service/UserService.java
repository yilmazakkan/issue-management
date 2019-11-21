package com.yilmazakkan.issueManagement.service;

import org.springframework.data.domain.Pageable;
import com.yilmazakkan.issueManagement.dto.UserDto;
import com.yilmazakkan.issueManagement.util.TPage;

public interface UserService {
	
		UserDto save(UserDto user);

	    UserDto getById(Long id);

	    TPage<UserDto> getAllPageable(Pageable pageable);

	    UserDto getByUsername(String username);
	    
	    Boolean delete(Long userId);
}
