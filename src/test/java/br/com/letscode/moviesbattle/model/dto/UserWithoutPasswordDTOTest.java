package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.entity.UserEntity;

@ExtendWith(SpringExtension.class)
public class UserWithoutPasswordDTOTest {

	@Test
	public void should_dto_test() {
		
		UserWithoutPasswordDTO user1 = new UserWithoutPasswordDTO();
		user1.setCompleteName("name");
		user1.setEmail("email");
		user1.setTotalScore(10);
		
		UserWithoutPasswordDTO user2 = UserWithoutPasswordDTO.builder()
			.completeName("name")
			.email("email")
			.totalScore(10)
			.build();
		
		UserWithoutPasswordDTO user3 = new UserWithoutPasswordDTO("email","name",10);

		assertTrue(user1.getCompleteName().equals(user2.getCompleteName()));
		assertTrue(user2.getCompleteName().equals(user3.getCompleteName()));
		
		assertTrue(user1.getEmail().equals(user2.getEmail()));
		assertTrue(user2.getEmail().equals(user3.getEmail()));
		
	}
	
	@Test
	public void should_dto_convert_test() {
		
		UserEntity userEntity = UserEntity.builder()
				.completeName("name")
				.email("email")
				.password("password")
				.totalScore(10)
				.build();
		
		UserWithoutPasswordDTO user = UserWithoutPasswordDTO.convertToDTO(userEntity);
		
		assertTrue(userEntity.getCompleteName().equals(user.getCompleteName()));
		
		assertTrue(userEntity.getEmail().equals(user.getEmail()));
		
		assertTrue(userEntity.getTotalScore().equals(user.getTotalScore()));
	}
}
