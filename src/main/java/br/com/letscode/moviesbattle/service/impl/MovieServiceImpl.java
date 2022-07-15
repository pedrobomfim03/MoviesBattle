package br.com.letscode.moviesbattle.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.letscode.moviesbattle.model.dto.MovieDTO;
import br.com.letscode.moviesbattle.model.entity.MovieEntity;
import br.com.letscode.moviesbattle.repository.MovieRepository;
import br.com.letscode.moviesbattle.service.MovieService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	private static final String PATH_MOVIES = "src/main/resources/data/movies.json";
	
	public void loadData() {
		
		try {
			
			log.info("============ LOADING MOVIES ============");
			byte[] bytesMovieList = Files.readAllBytes(Paths.get(PATH_MOVIES));
			ObjectMapper mapper = new ObjectMapper();
			List<MovieDTO> movieList = mapper.readValue(bytesMovieList, new TypeReference<List<MovieDTO>>(){});
			List<MovieEntity> movieListEntity = movieList
					.stream()
					.map(movieDTO->MovieEntity.convertToEntity(movieDTO))
					.collect(Collectors.toList());
			movieRepository.saveAll(movieListEntity);
			log.info("============ END LOADING MOVIES ============");
			
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
	}
}
