package br.com.letscode.moviesbattle.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.letscode.moviesbattle.model.entity.RoundEntity;

@Repository
public interface RoundRepository extends JpaRepository<RoundEntity, UUID>{
	@Query(nativeQuery=true,value="SELECT * FROM rounds r INNER JOIN movies m1 ON m1.id = r.cod_movie_one "
			+ "INNER JOIN movies m2 ON m2.id = r.cod_movie_two "
			+ "INNER JOIN games g ON g.id = r.cod_game "
			+ "WHERE g.end_datetime IS NULL "
			+ "AND g.cod_user = :user AND "
			+ " ((r.cod_movie_one = :movieOneFirst AND r.cod_movie_two = :movieTwoFirst) OR "
			+ "(r.cod_movie_one = :movieTwoSecond AND r.cod_movie_one = :movieOneSecond)) LIMIT 1")
	public Optional<RoundEntity> findRoundInvalid(@Param("user") UUID user,
										@Param("movieOneFirst") UUID movieOneFirst,
										@Param("movieTwoFirst") UUID movieTwoFirst,
										@Param("movieOneSecond") UUID movieOneSecond,
										@Param("movieTwoSecond") UUID movieTwoSecond);
	
	
	@Query("SELECT r FROM RoundEntity r WHERE r.game.endDatetime IS NULL "
			+ "AND r.game.user.id = :user AND r.id = :round")
	public Optional<RoundEntity> findRoundChosen(@Param("user") UUID user,
												@Param("round") UUID round);
	
}
