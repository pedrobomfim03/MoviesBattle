package br.com.letscode.moviesbattle.model.dto;

import java.time.LocalDateTime;

import br.com.letscode.moviesbattle.model.entity.GameEntity;
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
public class GameDTO {
	
	private LocalDateTime beginDatetime;
	private LocalDateTime endDatetime;
	private Integer score;
	
	public static GameDTO convertToDTO(GameEntity gameEntity) {
		return GameDTO.builder()
				.beginDatetime(gameEntity.getBeginDatetime())
				.endDatetime(gameEntity.getEndDatetime())
				.score(gameEntity.getScore())
				.build();
	}
}
