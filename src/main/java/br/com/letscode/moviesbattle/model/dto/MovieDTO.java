package br.com.letscode.moviesbattle.model.dto;

import java.util.UUID;

import br.com.letscode.moviesbattle.model.entity.MovieEntity;
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
public class MovieDTO {
	
	private UUID id;
	private String image;
	private String name;
	private Double ratingIMDB;
	
	public static MovieDTO convertToDTO(MovieEntity movieEntity){
		return MovieDTO.builder()
				.id(movieEntity.getId())
				.image(movieEntity.getImage())
				.name(movieEntity.getName())
				.ratingIMDB(movieEntity.getRatingIMDB())
				.build();
	}
}
