package com.yilmazakkan.issueManagement.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class UserDto {
	@NotNull
    @ApiModelProperty(required = true,value = "ID")
    private Long id;
	@NotNull
    @ApiModelProperty(required = true,value = "Name Surname")
    private String nameSurname;
	@NotNull
    @ApiModelProperty(required = true,value = "Username")
    private String username;
	@NotNull
	@Email
    @ApiModelProperty(required = true,value = "email")
    private String email;
}