package br.com.letscode.moviesbattle.model.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.entity.GameEntity;
import br.com.letscode.moviesbattle.model.entity.MovieEntity;
import br.com.letscode.moviesbattle.model.entity.RoundEntity;

@ExtendWith(SpringExtension.class)
public class RoundDTOTest {

	@Test
	public void should_dto_test() {
		UUID id = UUID.randomUUID();
		MovieWithoutRatingDTO movie = MovieWithoutRatingDTO.builder().build();
		GameDTO game = GameDTO.builder().build();
		
		RoundDTO round1 = new RoundDTO();
		round1.setId(id);
		round1.setMovieOne(movie);
		round1.setMovieTwo(movie);
		round1.setMovieChosen(movie);
		round1.setResult(Boolean.FALSE);
		round1.setGame(game);
		
		RoundDTO round2 = RoundDTO.builder()
    			.id(id)
    			.movieOne(movie)
    			.movieTwo(movie)
    			.movieChosen(movie)
    			.game(game)
    			.result(Boolean.FALSE)
    			.build();
		
		RoundDTO round3 = new RoundDTO(id,movie,movie,movie,Boolean.FALSE , game);

		assertTrue(round1.getId().equals(round2.getId()));
		assertTrue(round2.getId().equals(round3.getId()));
		
		assertTrue(round1.getMovieOne().equals(round2.getMovieOne()));
		assertTrue(round2.getMovieOne().equals(round3.getMovieOne()));
		
		assertTrue(round1.getMovieTwo().equals(round2.getMovieTwo()));
		assertTrue(round2.getMovieTwo().equals(round3.getMovieTwo()));
		
		assertTrue(round1.getMovieChosen().equals(round2.getMovieChosen()));
		assertTrue(round2.getMovieChosen().equals(round3.getMovieChosen()));
		
		assertTrue(round1.getResult().equals(round2.getResult()));
		assertTrue(round2.getResult().equals(round3.getResult()));
	}
	
	@Test
	public void should_dto_convert_test() {
		UUID id = UUID.randomUUID();
		MovieEntity movie = MovieEntity.builder().build();
		GameEntity game = GameEntity.builder().build();
		
		RoundEntity roundEntity = RoundEntity.builder()
				.id(id)
    			.movieOne(movie)
    			.movieTwo(movie)
    			.movieChosen(movie)
    			.game(game)
    			.result(Boolean.FALSE)
    			.build();;
		
		RoundDTO round = RoundDTO.convertToDTO(roundEntity);
		
		assertTrue(roundEntity.getId().equals(round.getId()));
	}
}
