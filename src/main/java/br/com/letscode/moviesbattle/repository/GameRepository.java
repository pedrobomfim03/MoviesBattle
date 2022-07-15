package br.com.letscode.moviesbattle.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.letscode.moviesbattle.model.entity.GameEntity;
import br.com.letscode.moviesbattle.model.entity.UserEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID>{
	@Query("SELECT g FROM GameEntity g WHERE g.endDatetime IS NULL AND g.user = :user")
	Optional<GameEntity> findByUser(@Param("user") UserEntity user);
}
