package br.com.letscode.moviesbattle.service;

import java.util.List;

import br.com.letscode.moviesbattle.model.dto.GameDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;

public interface GameService {
	public GameDTO beginGame(String token) throws Exception;
	public List<UserWithoutPasswordDTO> rankingGame() throws Exception;
	public GameDTO endGame(String token) throws Exception;
}
