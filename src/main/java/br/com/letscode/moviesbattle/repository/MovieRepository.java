package br.com.letscode.moviesbattle.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.letscode.moviesbattle.model.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID>{
	@Query(nativeQuery=true,value="select * from movies m order by RAND() LIMIT 2")
	public List<MovieEntity> findRandomMovies();
}
