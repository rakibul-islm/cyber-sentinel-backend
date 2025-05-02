package com.bd.cybersentinel.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class ManualIdGenerator extends BaseEntity {

	@Id
	@Basic(optional = false)
	private Long id;
}
