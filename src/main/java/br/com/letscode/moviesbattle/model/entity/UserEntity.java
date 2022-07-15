package br.com.letscode.moviesbattle.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.letscode.moviesbattle.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",length=16,nullable=false,updatable=false,unique=true)
	private UUID id;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="complete_name",nullable=false,unique=true)
	private String completeName;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="total_score",nullable=true)
	@Builder.Default()
	private Integer totalScore = 0;
	
	public static UserEntity convertToEntity(UserDTO user){
		return UserEntity.builder()
				.email(user.getEmail())
				.completeName(user.getCompleteName())
				.password(user.getPassword())
				.totalScore(user.getTotalScore())
				.build();
	}
}
