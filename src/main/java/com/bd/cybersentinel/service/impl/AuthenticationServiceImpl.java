package com.bd.cybersentinel.service.impl;


import com.bd.cybersentinel.dto.req.AuthenticationReqDTO;
import com.bd.cybersentinel.dto.req.TokenValidationReqDTO;
import com.bd.cybersentinel.dto.res.AuthenticationResDTO;
import com.bd.cybersentinel.dto.res.TokenValidationResDTO;
import com.bd.cybersentinel.entity.User;
import com.bd.cybersentinel.repository.UserRepo;
import com.bd.cybersentinel.service.AuthenticationService;
import com.bd.cybersentinel.service.exception.ServiceException;
import com.bd.cybersentinel.util.JwtUtil;
import com.bd.cybersentinel.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl extends AbstractBaseService<User> implements AuthenticationService<AuthenticationResDTO, AuthenticationReqDTO> {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private UserServiceImpl userService;

	AuthenticationServiceImpl(UserRepo userRepo){
		super(userRepo);
	}

	@Override
	public Response<AuthenticationResDTO> find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> save(AuthenticationReqDTO reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> update(AuthenticationReqDTO reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> getAll(Pageable pageable, Boolean isPageable) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> delete(AuthenticationReqDTO reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> remove(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<AuthenticationResDTO> generateToken(AuthenticationReqDTO reqDto) {
		if(StringUtils.isBlank(reqDto.getUsername()) || StringUtils.isBlank(reqDto.getPassword())){
			return getErrorResponse("Username or password can't be empty");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqDto.getUsername(), reqDto.getPassword()));
		} catch (BadCredentialsException e) {
			return getErrorResponse("Invalid username or password");
		}

		// If authentication is successful, generate token
		final UserDetails userDetails = userService.loadUserByUsername(reqDto.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		AuthenticationResDTO resDto = new AuthenticationResDTO(jwt);
		return getSuccessResponse("Token generated successfully", resDto);
	}

	@Override
	public Response<TokenValidationResDTO> validateToken(TokenValidationReqDTO reqDto) {
		String username = jwtUtil.extractUsername(reqDto.getToken());
		if(StringUtils.isBlank(username)) {
			return getErrorResponse("Invalid token");
		}
		final UserDetails userDetails = userService.loadUserByUsername(username);
		boolean valid = jwtUtil.validateToken(reqDto.getToken(), userDetails);
		if(!valid) {
			return getErrorResponse("Invalid token");
		}

		return getSuccessResponse("Valid token", new TokenValidationResDTO());
	}

}
