package com.yilmazakkan.issueManagement.web.project;



import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yilmazakkan.issueManagement.dto.ProjectDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class ProjectRestControllerTests {

	@Autowired
	private TestRestTemplate  restTemplate;
	
	@Test
	public void setUp() {
	restTemplate = restTemplate.withBasicAuth("yilmazakkan", "123");
	}
	
	@Test
	public void testGetById() {
		ResponseEntity<ProjectDto> response = restTemplate.getForEntity("http://localhost:8000/rest/project/1000",ProjectDto.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getProjectCode(),Matchers.equalTo("CRM"));
	}
}
