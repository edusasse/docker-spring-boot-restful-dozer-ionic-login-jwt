package com.edusasse.app.config.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.facade.impl.UsuarioFacade;

@Component
public class AppUserDetailService implements UserDetailsService {
 
	private final UsuarioFacade usuarioFacade;

	public AppUserDetailService(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public final UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		final UsuarioDTO user = this.usuarioFacade.retrieveByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return org.springframework.security.core.userdetails.User
			.withUsername(username)
			.password(user.getSenha())
			.roles(user.getPerfil())
			.authorities(Collections.emptyList())
			.accountExpired(false)
			.accountLocked(false)
			.credentialsExpired(false)
			.disabled(false)
			.build();
	}

}