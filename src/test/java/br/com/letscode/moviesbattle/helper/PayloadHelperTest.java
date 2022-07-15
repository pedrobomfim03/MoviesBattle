package br.com.letscode.moviesbattle.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.TokenService;

@ExtendWith(SpringExtension.class)
public class PayloadHelperTest {

	@Mock
	private TokenService tokenService;
	
	@Mock
	private UserRepository repository;
	
	@Test
	public void should_payload_helper_test() {
		
		UserEntity user = UserEntity.builder().id(UUID.randomUUID()).build();
		
		when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
		
		when(tokenService.getTokenId(anyString())).thenReturn("id");
		
		when(tokenService.isTokenValid(anyString())).thenReturn(Boolean.TRUE);
		
		PayloadHelper.getIdUserLogged("Bearer token", tokenService, repository);
	}
	
}
