package com.bd.cybersentinel.dto.req;

import com.bd.cybersentinel.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseRequestDTO<E> implements BaseDTO<E> {

	protected Long id;
	protected boolean deleted;

	@JsonIgnore
	public abstract E getBean();

}
