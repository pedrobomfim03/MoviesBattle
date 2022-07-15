package br.com.letscode.moviesbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.moviesbattle.model.dto.MessageDTO;
import br.com.letscode.moviesbattle.model.dto.RoundChosenDTO;
import br.com.letscode.moviesbattle.model.dto.RoundDTO;
import br.com.letscode.moviesbattle.service.RoundService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/round")
@Slf4j
public class RoundController {

	@Autowired
	private RoundService service;
	
	@GetMapping
	public ResponseEntity<?> searchMovies(@RequestHeader("Authorization") String token){
		try {
			RoundDTO game = service.searchRound(token);
			if(game==null) {
				throw new Exception("Ocorreu erro ao realizar operação no banco de dados");
			}
			return ResponseEntity.status(HttpStatus.OK).body(game);
		}catch(Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					MessageDTO.builder()
					.message(e.getMessage())
					.build()
			);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> chosenMovie(@RequestHeader("Authorization") String token,@RequestBody RoundChosenDTO round){
		try {
			RoundDTO game = service.chosenMovie(token,round);
			if(game==null) {
				throw new Exception("Ocorreu erro ao realizar operação no banco de dados");
			}
			return ResponseEntity.status(HttpStatus.OK).body(game);
		}catch(Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					MessageDTO.builder()
					.message(e.getMessage())
					.build()
			);
		}
	}
}
