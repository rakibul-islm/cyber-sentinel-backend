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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class AuthenticationServiceImpl extends AbstractBaseService<User> implements AuthenticationService<AuthenticationResDTO, AuthenticationReqDTO> {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserServiceImpl userService;
	private final UserRepo userRepo;
	final String GOOGLE_CLIENT_ID = "128093420618-9v0n7iir2b4522v9bbo0vf2q8ef2ika4.apps.googleusercontent.com";

	AuthenticationServiceImpl(
			UserRepo userRepo,
			AuthenticationManager authenticationManager,
			JwtUtil jwtUtil,
			UserServiceImpl userService){
		super(userRepo);
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
		this.userRepo = userRepo;
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
	public Response<AuthenticationResDTO> googleSignIn(String idTokenString) throws Exception {
		GoogleIdToken idToken = verifyGoogleToken(idTokenString);
		if (idToken == null) {
			return getErrorResponse("Invalid Google ID Token");
		}
		GoogleIdToken.Payload payload = idToken.getPayload();
		String email = payload.getEmail();
		String name = (String) payload.get("name");

		// Check if user exists or create new
		User user = userRepo.findByEmail(email);
		if (user == null) {
			userService.saveGoogleUser(new User()
					.setUsername(email)
					.setEmail(email)
					.setFullName(name));
		}
		UserDetails userDetails = userService.loadUserByUsername(email);
		String jwt = jwtUtil.generateToken(userDetails);

		AuthenticationResDTO resDto = new AuthenticationResDTO(jwt);
		return getSuccessResponse("Google sign-in successful", resDto);
	}

	private GoogleIdToken verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
				GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
				.setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
				.build();

		return verifier.verify(idTokenString);
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
