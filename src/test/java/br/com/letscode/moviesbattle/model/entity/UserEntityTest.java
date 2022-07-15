package br.com.letscode.moviesbattle.model.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.dto.UserDTO;

@ExtendWith(SpringExtension.class)
public class UserEntityTest {

	@Test
	public void should_dto_test() {
		
		UserEntity user1 = new UserEntity();
		user1.setCompleteName("name");
		user1.setEmail("email");
		user1.setPassword("password");
		user1.setTotalScore(10);
		
		UserEntity user2 = UserEntity.builder()
			.completeName("name")
			.email("email")
			.password("password")
			.totalScore(10)
			.build();
		
		UserEntity user3 = new UserEntity(UUID.randomUUID(),"email","name", "password",10);

		assertTrue(user1.getCompleteName().equals(user2.getCompleteName()));
		assertTrue(user2.getCompleteName().equals(user3.getCompleteName()));
		
		assertTrue(user1.getEmail().equals(user2.getEmail()));
		assertTrue(user2.getEmail().equals(user3.getEmail()));
		
		assertTrue(user1.getPassword().equals(user2.getPassword()));
		assertTrue(user2.getPassword().equals(user3.getPassword()));
	}
	
	@Test
	public void should_dto_convert_test() {
		
		UserDTO userEntity = UserDTO.builder()
				.completeName("name")
				.email("email")
				.password("password")
				.totalScore(10)
				.build();
		
		UserEntity user = UserEntity.convertToEntity(userEntity);
		
		assertTrue(userEntity.getCompleteName().equals(user.getCompleteName()));
		
		assertTrue(userEntity.getEmail().equals(user.getEmail()));
		
		assertTrue(userEntity.getPassword().equals(user.getPassword()));
		
		assertTrue(userEntity.getTotalScore().equals(user.getTotalScore()));
	}
}
