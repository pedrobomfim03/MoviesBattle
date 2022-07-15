package br.com.letscode.moviesbattle.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.letscode.moviesbattle.model.dto.MovieDTO;
import br.com.letscode.moviesbattle.model.dto.MovieWithoutRatingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",length=16,nullable=false,updatable=false,unique=true)
	private UUID id;
	
	@Column(name="image")
	private String image;
	
	@Column(name="name",nullable=false,unique=true)
	private String name;
	
	@Column(name="rating_imdb",nullable=false)
	private Double ratingIMDB;
	
	public static MovieEntity convertToEntity(MovieDTO movie){
		return MovieEntity.builder()
				.id(movie.getId())
				.image(movie.getImage())
				.name(movie.getName())
				.ratingIMDB(movie.getRatingIMDB())
				.build();
	}
	
	public static MovieEntity convertToEntity(MovieWithoutRatingDTO movie){
		return MovieEntity.builder()
				.id(movie.getId())
				.image(movie.getImage())
				.name(movie.getName())
				.build();
	}
	
}
