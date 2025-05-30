package com.bd.cybersentinel.service;


import com.bd.cybersentinel.dto.req.AuthenticationReqDTO;
import com.bd.cybersentinel.dto.req.TokenValidationReqDTO;
import com.bd.cybersentinel.dto.res.AuthenticationResDTO;
import com.bd.cybersentinel.dto.res.TokenValidationResDTO;
import com.bd.cybersentinel.util.Response;

public interface AuthenticationService <R, E> extends BaseService<R, E> {

	Response<AuthenticationResDTO> generateToken(AuthenticationReqDTO reqDto);
	Response<TokenValidationResDTO> validateToken(TokenValidationReqDTO reqDto);
	Response<AuthenticationResDTO> googleSignIn(String idToken) throws Exception;
}
