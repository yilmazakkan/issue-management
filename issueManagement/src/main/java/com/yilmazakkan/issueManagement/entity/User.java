package com.yilmazakkan.issueManagement.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users")
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

	    @Column(name = "email", length = 100)
	    private String email;

	    @JoinColumn(name = "assignee_user_id")
	    @OneToMany(fetch = FetchType.LAZY)
	    private List<Issue> issues;
}
