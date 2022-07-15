package br.com.letscode.moviesbattle.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.letscode.moviesbattle.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
	public Optional<UserEntity>  findByEmail(String email);
	
	@Query(nativeQuery=true,value="SELECT * FROM users u ORDER BY u.total_score DESC LIMIT 20")
	public List<UserEntity> findRankingUser();
}
