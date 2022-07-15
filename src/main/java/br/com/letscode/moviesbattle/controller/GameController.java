package br.com.letscode.moviesbattle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.moviesbattle.model.dto.GameDTO;
import br.com.letscode.moviesbattle.model.dto.MessageDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;
import br.com.letscode.moviesbattle.service.GameService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

	@Autowired
	private GameService service;
	
	@GetMapping("begin")
	public ResponseEntity<Object> beginGame(@RequestHeader("Authorization") String token) {
		try {
			GameDTO game = service.beginGame(token);
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
	
	@GetMapping("ranking")
	public ResponseEntity<Object> rankingGame() {
		try {
			List<UserWithoutPasswordDTO> users = service.rankingGame();
			if(users==null) {
				throw new Exception("Ocorreu erro ao realizar operação no banco de dados");
			}
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}catch(Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					MessageDTO.builder()
					.message(e.getMessage())
					.build()
			);
		}
	}
	
	@GetMapping("end")
	public ResponseEntity<Object> endGame(@RequestHeader("Authorization") String token) {
		try {
			GameDTO game = service.endGame(token);
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
	
	@GetMapping
	public ResponseEntity<?> testRequest(){
		return ResponseEntity.ok("test");
	}
	
}
