package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserLoginDTOTest {

	@Test
	public void should_dto_test() {
		
		UserLoginDTO user1 = new UserLoginDTO();
		user1.setEmail("email");
		user1.setPassword("password");
		
		UserLoginDTO user2 = UserLoginDTO.builder()
			.email("email")
			.password("password")
			.build();
		
		UserLoginDTO user3 = new UserLoginDTO("email", "password");
		
		assertTrue(user1.getEmail().equals(user2.getEmail()));
		assertTrue(user2.getEmail().equals(user3.getEmail()));
		
		assertTrue(user1.getPassword().equals(user2.getPassword()));
		assertTrue(user2.getPassword().equals(user3.getPassword()));
	}
	
}
