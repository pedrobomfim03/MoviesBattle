package br.com.letscode.moviesbattle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.letscode.moviesbattle.model.dto.UserDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;
import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void loadSingleData(UserDTO user) {
		try {
			this.createUser(user);
			log.info("============ LOADING USER {} ============",user.getCompleteName());
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		
	}
	
	public UserWithoutPasswordDTO createUser(UserDTO user) throws Exception {
		
		UserEntity userEntity = UserEntity.convertToEntity(user);
		
		userEntity.setPassword(encoder.encode(userEntity.getPassword()));
		
		userEntity.setTotalScore(0);
		
		repository.save(userEntity);
		
		return UserWithoutPasswordDTO.convertToDTO(userEntity);
	}
}
