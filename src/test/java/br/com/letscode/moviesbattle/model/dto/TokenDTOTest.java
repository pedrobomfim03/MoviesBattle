package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TokenDTOTest {

	@Test
	public void should_dto_test() {
		
		TokenDTO token1 = new TokenDTO();
		token1.setToken("token");
		token1.setType("bearer");
		
		TokenDTO token2 = TokenDTO.builder()
			.token("token")
			.type("bearer")
			.build();
		
		TokenDTO token3 = new TokenDTO("token","bearer");

		assertTrue(token1.getToken().equals(token2.getToken()));
		assertTrue(token2.getToken().equals(token3.getToken()));
		
	}
	
}
