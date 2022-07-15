package br.com.letscode.moviesbattle.model.dto;

import java.util.UUID;

import br.com.letscode.moviesbattle.model.entity.GameEntity;
import br.com.letscode.moviesbattle.model.entity.MovieEntity;
import br.com.letscode.moviesbattle.model.entity.RoundEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoundDTO {
	
	private UUID id;
	private MovieWithoutRatingDTO movieOne;
	private MovieWithoutRatingDTO movieTwo;
	private MovieWithoutRatingDTO movieChosen = null;
	private Boolean result = null;
	private GameDTO game;
	
	public static RoundDTO convertToDTO(RoundEntity roundEntity){
		return RoundDTO.builder()
				.game(GameDTO.convertToDTO(roundEntity.getGame()))
				.id(roundEntity.getId())
				.movieOne(MovieWithoutRatingDTO.convertToDTO(roundEntity.getMovieOne()))
				.movieTwo(MovieWithoutRatingDTO.convertToDTO(roundEntity.getMovieTwo()))
				.movieChosen(MovieWithoutRatingDTO.convertToDTO(roundEntity.getMovieChosen()))
				.result(roundEntity.getResult())
				.build();
	}
}
