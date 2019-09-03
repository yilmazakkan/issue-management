package com.yilmazakkan.issueManagement.entity;

import java.util.Date;
import java.util.List;

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
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "issue_history")
public class IssueHistory extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "issue_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Issue issue;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	
	@Column(name = "issue_status")
	@Enumerated(EnumType.STRING) 
	private IssueStatus issueStatus;
	
	@Column(name = "details", length = 4000)
	private String details;
	
	
	@JoinColumn(name = "assignee_user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private User assignee;


	
}
