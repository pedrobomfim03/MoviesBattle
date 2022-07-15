package br.com.letscode.moviesbattle.model.entity;

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

import br.com.letscode.moviesbattle.model.dto.RoundDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="rounds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoundEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",length=16,nullable=false,updatable=false,unique=true)
	private UUID id;
	
	@JoinColumn(name="cod_movie_one",nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private MovieEntity movieOne;
	
	@JoinColumn(name="cod_movie_two",nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private MovieEntity movieTwo;
	
	@JoinColumn(name="cod_movie_chosen",nullable=true)
	@ManyToOne(fetch=FetchType.EAGER)
	private MovieEntity movieChosen = null;
	
	@Column(name="result")
	private Boolean result = null;
	
	@JoinColumn(name="cod_game")
	@ManyToOne(fetch=FetchType.EAGER)
	private GameEntity game;
	
	public static RoundEntity convertToDTO(RoundDTO roundDTO){
		return RoundEntity.builder()
				.game(GameEntity.convertToDTO(roundDTO.getGame()))
				.movieOne(MovieEntity.convertToEntity(roundDTO.getMovieOne()))
				.movieTwo(MovieEntity.convertToEntity(roundDTO.getMovieTwo()))
				.movieChosen(MovieEntity.convertToEntity(roundDTO.getMovieChosen()))
				.build();
	}
	
}