package com.yilmazakkan.issueManagement.dto;




import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	private Long id;
	@NotNull
	private  String projectName;
	@NotNull
	private  String projectCode;
	
}
