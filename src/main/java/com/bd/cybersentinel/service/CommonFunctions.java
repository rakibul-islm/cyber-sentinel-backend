package com.bd.cybersentinel.service;

import com.bd.cybersentinel.util.Response;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonFunctions {
	<R> Response<R> getSuccessResponse(String message);

	<R>Response<R> getSuccessResponse(String message, R r);

	<R>Response<R> getSuccessResponse(String message, List<R> list);

	<R>Response<R> getSuccessResponse(String message, Page<R> page);

	<R>Response<R> getErrorResponse(String message);
}
