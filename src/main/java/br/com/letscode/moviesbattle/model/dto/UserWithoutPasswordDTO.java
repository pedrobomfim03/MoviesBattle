package br.com.letscode.moviesbattle.model.dto;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
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
public class UserWithoutPasswordDTO {

	private String email;
	private String completeName;
	private Integer totalScore;
	
	public static UserWithoutPasswordDTO convertToDTO(UserEntity userEntity){
		return UserWithoutPasswordDTO.builder()
				.email(userEntity.getEmail())
				.completeName(userEntity.getCompleteName())
				.totalScore(userEntity.getTotalScore())
				.build();
	}
}
