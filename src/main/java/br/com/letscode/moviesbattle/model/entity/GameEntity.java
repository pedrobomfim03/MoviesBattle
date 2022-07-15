package br.com.letscode.moviesbattle.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.letscode.moviesbattle.model.dto.GameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="games")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",length=16,nullable=false,updatable=false,unique=true)
	private UUID id;
	
	@Column(name="begin_datetime",nullable=false)
	@Builder.Default
	private LocalDateTime beginDatetime = LocalDateTime.now();
	
	@Builder.Default
	@Column(name="end_datetime")
	private LocalDateTime endDatetime = null;
	
	@Builder.Default
	@Column(name="score",nullable=false)
	private Integer score = 0;
	
	@JoinColumn(name="cod_user")
	@ManyToOne(fetch=FetchType.EAGER)
	private UserEntity user;
	
	public static GameEntity convertToDTO(GameDTO gameEntity) {
		return GameEntity.builder()
				.beginDatetime(gameEntity.getBeginDatetime())
				.endDatetime(gameEntity.getEndDatetime())
				.score(gameEntity.getScore())
				.build();
	}
	
}