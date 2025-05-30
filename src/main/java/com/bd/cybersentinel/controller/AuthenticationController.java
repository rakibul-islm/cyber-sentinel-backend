package com.bd.cybersentinel.controller;

import com.bd.cybersentinel.annotation.RestApiController;
import com.bd.cybersentinel.dto.req.AuthenticationReqDTO;
import com.bd.cybersentinel.dto.req.TokenValidationReqDTO;
import com.bd.cybersentinel.dto.res.AuthenticationResDTO;
import com.bd.cybersentinel.dto.res.TokenValidationResDTO;
import com.bd.cybersentinel.service.AuthenticationService;
import com.bd.cybersentinel.util.Response;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestApiController
@RequestMapping("/authenticate")
@Tag(name = "1.0 Authentication", description = "API")
public class AuthenticationController extends AbstractBaseController<AuthenticationResDTO, AuthenticationReqDTO> {

	private AuthenticationService<AuthenticationResDTO, AuthenticationReqDTO> authservice;

	public AuthenticationController(AuthenticationService<AuthenticationResDTO, AuthenticationReqDTO> authservice) {
		super(authservice);
		this.authservice = authservice;
	}

	@Operation(summary = "Generate access token")
	@PostMapping("/token")
	public Response<AuthenticationResDTO> generateToken(@RequestBody AuthenticationReqDTO reqDto) throws Exception {
		return authservice.generateToken(reqDto);
	}

	@Operation(summary = "Sign in with Google")
	@PostMapping("/token/google")
	public Response<AuthenticationResDTO> googleSignIn(@RequestBody Map<String, String> body) throws Exception {
		String idTokenString = body.get("token");
		return authservice.googleSignIn(idTokenString);
	}

	@Operation(summary = "Validate Token")
	@PostMapping("/token/validate")
	public Response<TokenValidationResDTO> validateToken(@RequestBody TokenValidationReqDTO reqDto) throws Exception {
		return authservice.validateToken(reqDto);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> getAll(@Nullable Pageable pageable, Boolean isPageable) {
		return super.getAll(pageable, isPageable);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> save(AuthenticationReqDTO e) {
		// TODO Auto-generated method stub
		return super.save(e);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> update(AuthenticationReqDTO e) {
		// TODO Auto-generated method stub
		return super.update(e);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> find(Long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> delete(AuthenticationReqDTO e) {
		// TODO Auto-generated method stub
		return super.delete(e);
	}

	@Hidden
	@Override
	public Response<AuthenticationResDTO> remove(Long id) {
		// TODO Auto-generated method stub
		return super.remove(id);
	}

}
