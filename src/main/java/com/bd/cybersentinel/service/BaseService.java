package com.bd.cybersentinel.service;

import com.bd.cybersentinel.service.exception.ServiceException;
import com.bd.cybersentinel.util.Response;
import org.springframework.data.domain.Pageable;

public interface BaseService<R, E> {

	Response<R> find(Long id) throws ServiceException;

	Response<R> save(E reqDto) throws ServiceException;

	Response<R> update(E reqDto) throws ServiceException;

	Response<R> getAll(Pageable pageable, Boolean isPageable) throws ServiceException;

	Response<R> delete(E reqDto) throws ServiceException;

	Response<R> remove(Long id) throws ServiceException;
}
