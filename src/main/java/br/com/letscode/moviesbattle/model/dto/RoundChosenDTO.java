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
public class RoundChosenDTO {
	private UUID idMovieChosen;
	private UUID idRoundChosen;
}
