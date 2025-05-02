package com.bd.cybersentinel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	@NotNull
	@Column(name = "created_by", nullable = false, length = 20)
	private String createdBy;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@NotNull
	@Column(name = "updated_by", nullable = false, length = 20)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

}
