package com.bd.cybersentinel.service;


import com.bd.cybersentinel.dto.req.UserSignupReqDto;
import com.bd.cybersentinel.dto.res.UserProfileResDTO;
import com.bd.cybersentinel.dto.res.UserResDTO;
import com.bd.cybersentinel.entity.User;
import com.bd.cybersentinel.service.exception.ServiceException;
import com.bd.cybersentinel.util.Response;

public interface UserService<R, E> extends BaseService<R, E>{
	
	Response<UserResDTO> saveNormalUser(UserSignupReqDto reqDto) throws ServiceException;

	void saveGoogleUser(User reqDto) throws ServiceException;

	Response<UserProfileResDTO> userProfile() throws ServiceException;
}
