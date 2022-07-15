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
public class MovieWithoutRatingDTO {
	
	private UUID id;
	private String image;
	private String name;
	
	public static MovieWithoutRatingDTO convertToDTO(MovieEntity movieEntity){
		if(movieEntity!=null) {
			return MovieWithoutRatingDTO.builder()
				.id(movieEntity.getId())
				.image(movieEntity.getImage())
				.name(movieEntity.getName())
				.build();
		}
		return null;
	}
}
