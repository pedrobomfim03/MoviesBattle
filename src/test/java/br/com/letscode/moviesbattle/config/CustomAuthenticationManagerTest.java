package br.com.letscode.moviesbattle.config;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class CustomAuthenticationManagerTest {

	@Mock
    Authentication authentication;
	
	@InjectMocks
	CustomAuthenticationManager custom;
	
	@Mock
	UserRepository repository;
	
	@Mock
	PasswordEncoder encoder;
	
    @Test
    public void should_authenticate() throws Exception {
    	
    	UserEntity user = new UserEntity();
    	user.setEmail("email@email.com");
    	user.setPassword("senha");
    	
    	when(authentication.getPrincipal()).thenReturn("email@email.com");
    	when(authentication.getCredentials()).thenReturn("senha");
    	
    	when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
    	when(encoder.matches(anyString(),anyString())).thenReturn(Boolean.TRUE);
    	
    	custom.authenticate(authentication);
    }
	
}
