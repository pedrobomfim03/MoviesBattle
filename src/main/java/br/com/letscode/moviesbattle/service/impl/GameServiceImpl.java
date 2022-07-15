package br.com.letscode.moviesbattle.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.moviesbattle.helper.PayloadHelper;
import br.com.letscode.moviesbattle.model.dto.GameDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;
import br.com.letscode.moviesbattle.model.entity.GameEntity;
import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.GameRepository;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.GameService;
import br.com.letscode.moviesbattle.service.TokenService;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserWithoutPasswordDTO> rankingGame() throws Exception{
		
		return userRepository.findRankingUser().stream()
				.map(user->UserWithoutPasswordDTO.convertToDTO(user))
				.collect(Collectors.toList());
	}
	
	public GameDTO beginGame(String token) throws Exception {
		
		UUID userId = PayloadHelper.getIdUserLogged(token,tokenService,userRepository);
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		GameEntity game = new GameEntity();
		if(userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			Optional<GameEntity> gameOptional = repository.findByUser(user);
			if(gameOptional.isEmpty()) {
				game.setUser(user);
				game = repository.save(game);
				return GameDTO.convertToDTO(game);
			}
			throw new Exception("Jogo já se encontra em andamento");
		}
		
		return null;
	}
	
	public GameDTO endGame(String token) throws Exception {
		
		UUID userId = PayloadHelper.getIdUserLogged(token,tokenService,userRepository);
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		GameEntity game = new GameEntity();
		if(userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			Optional<GameEntity> gameOptional = repository.findByUser(user);
			if(gameOptional.isPresent()) {
				game.setEndDatetime(LocalDateTime.now());;
				game = repository.save(game);
				return GameDTO.convertToDTO(game);
			}
			throw new Exception("Nenhum jogo para ser finalizado");
		}
		
		return null;
	}
	
}
