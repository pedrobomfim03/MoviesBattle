package br.com.letscode.moviesbattle.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;
import br.com.letscode.moviesbattle.service.TokenService;
@Component
@Order(1)
public class TokenAuthenticationFilter implements Filter {
	
	private TokenService tokenService;
	
	private UserRepository repository;
	
	public TokenAuthenticationFilter(TokenService tokenService,UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	private UserEntity authenticate(String tokenFromHeader) {
		String id = tokenService.getTokenId(tokenFromHeader);
		
		Optional<UserEntity> optionalUser = repository.findByEmail(id);
		
		if(optionalUser.isPresent()) {
			
			UserEntity user = optionalUser.get();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			return user;
		}
		return null;
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		try {
			if(request.getServletPath().equals("/user")||request.getServletPath().equals("/auth")) {
				filterChain.doFilter(request, response);
				return;
			}
			Boolean invalidate = false;
			String tokenFromHeader = getTokenFromHeader(request);
			if(tokenFromHeader!=null) {
				invalidate = validateToken(tokenFromHeader,request,response,filterChain);	
			}else {
				invalidate = true;
			}
			
			if(invalidate) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().print("{\"message\":\"Ocorreu um erro.\"}");
		}
	}
	
	public Boolean validateToken(String tokenFromHeader,
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws Exception {
		Boolean invalidate = Boolean.FALSE;
		Boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
		if(tokenValid) {
			UserEntity user = this.authenticate(tokenFromHeader);
			if(user!=null) {
				response.addHeader("Authorization", tokenService.generateToken(user));
				filterChain.doFilter(request, response);
			}else {
				invalidate = true;
			}
		}else {
			invalidate = true;
		}
		
		return invalidate;
	}

}