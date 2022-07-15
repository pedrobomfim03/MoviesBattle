package br.com.letscode.moviesbattle.config;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import br.com.letscode.moviesbattle.model.entity.UserEntity;
import br.com.letscode.moviesbattle.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TokenAuthenticationFilterTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private UserRepository repository;
	
	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3Mi"
			+ "OiJNb3ZpZXMgQmF0dGxlIFRva2VuIiwic3ViIjoiam9nYWRvci5jb21wbGV0b0"
			+ "BlbWFpbC5jb20iLCJpYXQiOjE2NTc4ODgyNTAsImV4cCI6MTY1Nzg5MDA1MH0."
			+ "I5IC6JVcfHh_V3JkxZLV6TfZ3xjIHrOJ0t7lWsNVltw";
	
	@Test
	public void should_test_request_auth() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/auth");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_test_request() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/game");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_test_request_begin_game() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/game/begin");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_test_request_end_game() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/game/end");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_test_request_round() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/round");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void should_test_request_game_ranking() {
		
		try {
			HttpUriRequest request = new HttpGet( "http://localhost:"+port+"/game/ranking");
			request.setHeader("Authorization", TOKEN);
			HttpClientBuilder.create().build().execute( request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
