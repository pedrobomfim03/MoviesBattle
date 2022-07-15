package br.com.letscode.moviesbattle.service;

import br.com.letscode.moviesbattle.model.dto.RoundChosenDTO;
import br.com.letscode.moviesbattle.model.dto.RoundDTO;

public interface RoundService {
	public RoundDTO searchRound(String token) throws Exception;
	public RoundDTO chosenMovie(String token,RoundChosenDTO round) throws Exception;
}
