package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MessageDTOTest {

	@Test
	public void should_dto_test() {
		
		MessageDTO message1 = new MessageDTO();
		message1.setMessage("message");
		
		MessageDTO message2 = MessageDTO.builder()
			.message("message")
			.build();
		
		MessageDTO message3 = new MessageDTO("message");

		assertTrue(message1.getMessage().equals(message2.getMessage()));
		assertTrue(message2.getMessage().equals(message3.getMessage()));
		
	}
	
}
