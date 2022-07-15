package br.com.letscode.moviesbattle.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService{

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {

		String email = (String) authentication.getPrincipal();

		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("Movies Battle Token")
                             .setSubject(email)
                             .setIssuedAt(new Date())
				             .setExpiration(exp)
                             .signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public String generateToken(UserEntity user) {

		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("Movies Battle Token")
                             .setSubject(user.getEmail())
                             .setIssuedAt(new Date())
				             .setExpiration(exp)
                             .signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public String getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return body.getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
