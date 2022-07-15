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
public class UserDTO {

	private String email;
	private String completeName;
	private String password;
	private Integer totalScore;
	
	public static UserDTO convertToDTO(UserEntity userEntity){
		return UserDTO.builder()
				.email(userEntity.getEmail())
				.completeName(userEntity.getCompleteName())
				.password(userEntity.getPassword())
				.totalScore(userEntity.getTotalScore())
				.build();
	}
	
}
