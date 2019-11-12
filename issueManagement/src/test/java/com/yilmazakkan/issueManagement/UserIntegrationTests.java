package com.yilmazakkan.issueManagement;


import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.yilmazakkan.issueManagement.dto.UserDto;

import com.yilmazakkan.issueManagement.service.impl.UserServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.profiles.active=dev"})
public class UserIntegrationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	

	
	@Test
	public void testGetAllUsers() {
		List<UserDto> user = userServiceImpl.getAll();
		MatcherAssert.assertThat(user.size(), Matchers.equalTo(3));
	}
	

}
						