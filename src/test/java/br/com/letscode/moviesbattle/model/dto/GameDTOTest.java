package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.entity.GameEntity;

@ExtendWith(SpringExtension.class)
public class GameDTOTest {

	@Test
	public void should_dto_test() {
		LocalDateTime date = LocalDateTime.now();
		
		GameDTO game1 = new GameDTO();
		game1.setBeginDatetime(date);
		game1.setEndDatetime(date);
		game1.setScore(10);
		
		GameDTO game2 = GameDTO.builder()
    			.beginDatetime(date)
    			.endDatetime(date)
    			.score(10)
    			.build();;
		
		GameDTO game3 = new GameDTO(date, date, 10);

		assertTrue(game1.getBeginDatetime().equals(game2.getBeginDatetime()));
		assertTrue(game2.getBeginDatetime().equals(game3.getBeginDatetime()));
		
		assertTrue(game1.getEndDatetime().equals(game2.getEndDatetime()));
		assertTrue(game2.getEndDatetime().equals(game3.getEndDatetime()));
		
		assertTrue(game1.getScore().equals(game2.getScore()));
		assertTrue(game2.getScore().equals(game3.getScore()));
	}
	
	@Test
	public void should_dto_convert_test() {
		LocalDateTime date = LocalDateTime.now();
		
		GameEntity gameEntity = GameEntity.builder()
    			.beginDatetime(date)
    			.endDatetime(date)
    			.score(10)
    			.build();;
		
		GameDTO game = GameDTO.convertToDTO(gameEntity);
		
		assertTrue(gameEntity.getBeginDatetime().equals(game.getBeginDatetime()));
		
		assertTrue(gameEntity.getEndDatetime().equals(game.getEndDatetime()));
		
		assertTrue(gameEntity.getScore().equals(game.getScore()));
	}
}
