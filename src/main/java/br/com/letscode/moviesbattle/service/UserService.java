package br.com.letscode.moviesbattle.service;

import br.com.letscode.moviesbattle.model.dto.UserDTO;
import br.com.letscode.moviesbattle.model.dto.UserWithoutPasswordDTO;

public interface UserService {

	public UserWithoutPasswordDTO createUser(UserDTO user) throws Exception;
	
	public void loadSingleData(UserDTO user);
}
