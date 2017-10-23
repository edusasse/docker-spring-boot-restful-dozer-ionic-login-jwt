package com.edusasse.app.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edusasse.app.Application;
import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.facade.impl.UsuarioFacade;
import com.edusasse.app.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class AuthController {

	private final UsuarioFacade usuarioFacade;

	private final TokenProvider tokenProvider;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthController(PasswordEncoder passwordEncoder, UsuarioFacade usuarioFacade, TokenProvider tokenProvider,
			AuthenticationManager authenticationManager) {
		this.usuarioFacade = usuarioFacade;
		this.tokenProvider = tokenProvider;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * We don't have to do anything here this is just a secure endpoint and the
	 * JWTFilter validates the token this service is called at startup of the
	 * app to check if the jwt token is still valid
	 */
	@GetMapping("/authenticate")
	public void authenticate() {
	}

	@PostMapping("/login")
	public String authorize(@Valid @RequestBody UsuarioDTO loginUser, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginUser.getApelido(), loginUser.getSenha());

		try {
			this.authenticationManager.authenticate(authenticationToken);
			return tokenProvider.createToken(loginUser.getApelido());
		} catch (AuthenticationException e) {
			Application.logger.info("Security exception {}", e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
	}

	@PostMapping("/signup")
	public String signup(@RequestBody UsuarioDTO signupUser) {
		if (this.usuarioFacade.retrieveByName(signupUser.getApelido()) != null) {
			return "EXISTS";
		}

		signupUser.encodePassword(this.passwordEncoder);
		this.usuarioFacade.create(signupUser);
		return this.tokenProvider.createToken(signupUser.getApelido());
	}

}
