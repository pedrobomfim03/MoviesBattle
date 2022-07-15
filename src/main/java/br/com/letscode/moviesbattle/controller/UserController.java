package br.com.letscode.moviesbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.moviesbattle.model.dto.MessageDTO;
import br.com.letscode.moviesbattle.model.dto.UserDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;
import br.com.letscode.moviesbattle.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {
		try {
			UserWithoutPasswordDTO userReturn = service.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userReturn);
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
