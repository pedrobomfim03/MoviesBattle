package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.entity.UserEntity;

@ExtendWith(SpringExtension.class)
public class UserDTOTest {

	@Test
	public void should_dto_test() {
		
		UserDTO user1 = new UserDTO();
		user1.setCompleteName("name");
		user1.setEmail("email");
		user1.setPassword("password");
		user1.setTotalScore(10);
		
		UserDTO user2 = UserDTO.builder()
			.completeName("name")
			.email("email")
			.password("password")
			.totalScore(10)
			.build();
		
		UserDTO user3 = new UserDTO("email","name", "password",10);

		assertTrue(user1.getCompleteName().equals(user2.getCompleteName()));
		assertTrue(user2.getCompleteName().equals(user3.getCompleteName()));
		
		assertTrue(user1.getEmail().equals(user2.getEmail()));
		assertTrue(user2.getEmail().equals(user3.getEmail()));
		
		assertTrue(user1.getPassword().equals(user2.getPassword()));
		assertTrue(user2.getPassword().equals(user3.getPassword()));
	}
	
	@Test
	public void should_dto_convert_test() {
		
		UserEntity userEntity = UserEntity.builder()
				.completeName("name")
				.email("email")
				.password("password")
				.totalScore(10)
				.build();
		
		UserDTO user = UserDTO.convertToDTO(userEntity);
		
		assertTrue(userEntity.getCompleteName().equals(user.getCompleteName()));
		
		assertTrue(userEntity.getEmail().equals(user.getEmail()));
		
		assertTrue(userEntity.getPassword().equals(user.getPassword()));
		
		assertTrue(userEntity.getTotalScore().equals(user.getTotalScore()));
	}
}
