package com.yilmazakkan.issueManagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users", indexes = { @Index(name = "idx_username", columnList = "uname") })
public class User extends BaseEntity {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "uname", length = 100, unique = true)
	private String username;

	@Column(name = "pwd", length = 200)
	private String password;

	@Column(name = "name_surname", length = 200)
	private String nameSurname;

	@Email
	@Column(name = "email", length = 100)
	private String email;

	@JoinColumn(name = "assignee_user_id")
	@OneToMany(fetch = FetchType.LAZY)
	private List<Issue> issues;
}
