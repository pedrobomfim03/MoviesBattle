package br.com.letscode.moviesbattle.model.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.letscode.moviesbattle.model.dto.MovieDTO;
import br.com.letscode.moviesbattle.model.dto.MovieWithoutRatingDTO;

@ExtendWith(SpringExtension.class)
public class MovieEntityTest {

	@Test
	public void should_dto_test() {
		
		UUID id = UUID.randomUUID();

		MovieEntity movie1 = new MovieEntity();
		movie1.setId(id);
		movie1.setImage("image");
		movie1.setName("name");
		movie1.setRatingIMDB(13.2);

		MovieEntity movie2 = MovieEntity.builder()
				.id(id)
				.image("image")
				.name("name")
				.ratingIMDB(13.2)
				.build();

		MovieEntity movie3 = new MovieEntity(id, "image","name", 13.2);

		assertTrue(movie1.getId().equals(movie2.getId()));
		assertTrue(movie2.getId().equals(movie3.getId()));
		
		assertTrue(movie1.getImage().equals(movie2.getImage()));
		assertTrue(movie2.getImage().equals(movie3.getImage()));
		
		assertTrue(movie1.getName().equals(movie2.getName()));
		assertTrue(movie2.getName().equals(movie3.getName()));
		
		assertTrue(movie1.getRatingIMDB().equals(movie2.getRatingIMDB()));
		assertTrue(movie2.getRatingIMDB().equals(movie3.getRatingIMDB()));
	}

	@Test
	public void should_dto_convert_test() {

		MovieDTO movieEntity = MovieDTO.builder()
				.id(UUID.randomUUID())
				.image("image")
				.name("name")
				.ratingIMDB(13.2)
				.build();

		MovieEntity movie = MovieEntity.convertToEntity(movieEntity);

		assertTrue(movieEntity.getId().equals(movie.getId()));

		assertTrue(movieEntity.getImage().equals(movie.getImage()));

		assertTrue(movieEntity.getName().equals(movie.getName()));
		
		assertTrue(movieEntity.getRatingIMDB().equals(movie.getRatingIMDB()));
	}
	
	public void should_dto_convert_without_rating_test() {

		MovieWithoutRatingDTO movieEntity = MovieWithoutRatingDTO.builder()
				.id(UUID.randomUUID())
				.image("image")
				.name("name")
				.build();

		MovieEntity movie = MovieEntity.convertToEntity(movieEntity);

		assertTrue(movieEntity.getId().equals(movie.getId()));

		assertTrue(movieEntity.getImage().equals(movie.getImage()));

		assertTrue(movieEntity.getName().equals(movie.getName()));
		
	}
}
