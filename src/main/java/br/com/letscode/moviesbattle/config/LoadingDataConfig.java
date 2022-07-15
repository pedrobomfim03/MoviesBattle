package br.com.letscode.moviesbattle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.letscode.moviesbattle.service.MovieService;

@Configuration
public class LoadingDataConfig {

	@Autowired
	private MovieService movieService;
	
	@Bean
	public void loadingData() {
		//System.out.println("\n\n\n"+users.get(0).getCompleteName()+"\n\n\n");
		movieService.loadData();
		//users.forEach(user->userService.loadSingleData(user));
	}
}
