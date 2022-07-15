package br.com.letscode.moviesbattle.helper;

import java.util.Optional;
import java.util.UUID;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.TokenService;

public class PayloadHelper {
	
	public static UUID getIdUserLogged(String token,TokenService tokenService,UserRepository repository) {
		String tokenFromHeader = getToken(token);
		if(tokenFromHeader!=null) {
			boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
			
			if(tokenValid) {
				String id = tokenService.getTokenId(tokenFromHeader);
				
				Optional<UserEntity> optionalUser = repository.findByEmail(id);
				
				if(optionalUser.isPresent()) {
					return optionalUser.get().getId();
				}
			}
		}
		
		return null;
	}
	
	private static String getToken(String token) {
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
