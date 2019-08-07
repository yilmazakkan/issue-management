package com.yilmazakkan.issueManagement;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@SpringBootApplication
public class IssueManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueManagementApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);  // 
		return  modelMapper;
		
	}
//	Converter org.modelmapper.internal.converter.BooleanConverter@6d099f0a failed to convert com.yilmazakkan.issueManagement.entity.IssueStatus to java.lang.Boolean.

		@Bean
	    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
	        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
	        factory.setResources(new Resource[]{new ClassPathResource("projects.json")});
	        return factory;
	    }
}
