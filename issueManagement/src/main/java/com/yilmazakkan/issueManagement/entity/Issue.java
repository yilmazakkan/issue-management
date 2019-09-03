package com.yilmazakkan.issueManagement.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "issue")
public class Issue extends BaseEntity{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "details", length = 4000)
	private String details;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "issue_status")
	@Enumerated(EnumType.STRING) //database de enumların nasıl tutulcağını belirtik string şekilde yaptık , int olsaydı eklediğimiz sıralamada olurdu.
	private IssueStatus issueStatus;
	
	
	@JoinColumn(name = "assignee_user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private User assignee;
	
	@JoinColumn(name = "project_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Project project;
	
}
