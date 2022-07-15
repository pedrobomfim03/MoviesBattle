package br.com.letscode.moviesbattle.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;

public class CustomAuthenticationManager implements AuthenticationManager{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		Optional<UserEntity> userEntityOptional = repository.findByEmail(email);
				if(!userEntityOptional.isPresent()) {
			throw new BadCredentialsException("1000");
		}
		
		UserEntity userEntity = userEntityOptional.get();
		
		if(!encoder.matches(password, userEntity.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		
		return new UsernamePasswordAuthenticationToken(email, null);
	}

}
