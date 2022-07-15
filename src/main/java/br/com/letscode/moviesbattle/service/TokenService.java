package br.com.letscode.moviesbattle.service;

import org.springframework.security.core.Authentication;

import br.com.letscode.moviesbattle.model.entity.UserEntity;

public interface TokenService {
	public String generateToken(Authentication authentication);
	public String generateToken(UserEntity usuario);
	public boolean isTokenValid(String token);
	public String getTokenId(String token);
}
