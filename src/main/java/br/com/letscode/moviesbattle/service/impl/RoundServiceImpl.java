package br.com.letscode.moviesbattle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.moviesbattle.helper.PayloadHelper;
import br.com.letscode.moviesbattle.model.dto.RoundChosenDTO;
import br.com.letscode.moviesbattle.model.dto.RoundDTO;
import br.com.letscode.moviesbattle.model.entity.GameEntity;
import br.com.letscode.moviesbattle.model.entity.MovieEntity;
import br.com.letscode.moviesbattle.model.entity.RoundEntity;
import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.GameRepository;
import br.com.letscode.moviesbattle.repository.MovieRepository;
import br.com.letscode.moviesbattle.repository.RoundRepository;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.RoundService;
import br.com.letscode.moviesbattle.service.TokenService;

@Service
public class RoundServiceImpl implements RoundService{
	
	@Autowired
	private RoundRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public RoundDTO searchRound(String token) throws Exception{
		Boolean valide = Boolean.FALSE;
		UUID userId = PayloadHelper.getIdUserLogged(token,tokenService,userRepository);
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			List<MovieEntity> movies = new ArrayList<>();
			 
			while(!valide) {
				Boolean equals = Boolean.TRUE;
				Boolean invalidMovies = Boolean.TRUE;
				movies = movieRepository.findRandomMovies();
				if(!movies.get(0).getName().equals(movies.get(1).getName())) {
					equals = Boolean.FALSE;
				}
				
				Optional<RoundEntity> roundOptional = repository.findRoundInvalid(user.getId(),
												movies.get(0).getId(), movies.get(1).getId(),
												movies.get(0).getId(), movies.get(1).getId());
				
				if(!roundOptional.isPresent()) {
					invalidMovies = Boolean.FALSE;
				}
				
				if(!equals && !invalidMovies) {
					valide = Boolean.TRUE;
				}
			}
			
			Optional<GameEntity> gameOptional = gameRepository.findByUser(user);
			if(gameOptional.isPresent()) {
				RoundEntity round = RoundEntity.builder()
					.game(gameOptional.get())
					.movieOne(movies.get(0))
					.movieTwo(movies.get(1))
					.build();
				round = repository.save(round);
				return RoundDTO.convertToDTO(round);
			}
		}
		return null;
	}

	
	public RoundDTO chosenMovie(String token,RoundChosenDTO roundChosen) throws Exception{
		UUID userId = PayloadHelper.getIdUserLogged(token,tokenService,userRepository);
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			Optional<RoundEntity> roundOptional = repository.findRoundChosen(user.getId(),roundChosen.getIdRoundChosen());
			if(roundOptional.isPresent()) {
				RoundEntity round = roundOptional.get();
				round.setMovieChosen(movieRepository.findById(roundChosen.getIdMovieChosen()).get());
				if(round.getMovieOne().getId().equals(roundChosen.getIdMovieChosen())) {
					if(round.getMovieOne().getRatingIMDB()>round.getMovieTwo().getRatingIMDB()) {
						round.setResult(Boolean.TRUE);
						user.setTotalScore(user.getTotalScore()+1);
						round = repository.save(round);
						userRepository.save(user);
						
						return RoundDTO.convertToDTO(round);
					}
				}
			}
		}
		return null;
	}
}
