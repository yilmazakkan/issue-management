package com.yilmazakkan.issueManagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "created_by", length = 50)
	  private String createdBy;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_by", length = 50)
	private String updatedBy;
	
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "status")
	private Boolean status;
	 
}
