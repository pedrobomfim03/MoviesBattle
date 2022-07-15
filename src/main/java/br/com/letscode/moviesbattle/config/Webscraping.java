package br.com.letscode.moviesbattle.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.letscode.moviesbattle.model.dto.MovieDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Webscraping {
	
	private static final String URL = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
	private static final String PATH_MOVIES = "src/main/resources/data/movies.json";

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect(URL).get();
			
			Element tbody = doc.getElementsByClass("lister-list").get(0);
			
			List<MovieDTO> movieList = new ArrayList<>();

			tbody.children().forEach(tr->
				movieList.add(
						MovieDTO.builder()
						.image(getImage(tr))
						.name(getName(tr))
						.ratingIMDB(getRating(tr))
						.build()
				)
			);
			
			saveFile(movieList);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}
	
	public static void saveFile(List<MovieDTO> movieList) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		byte[] bytesMovieList = mapper.writerWithDefaultPrettyPrinter()
										.writeValueAsBytes(movieList);
		Files.write(Paths.get(PATH_MOVIES),bytesMovieList);
	}
	
	public static String getImage(Element tr) {
		return tr.child(0).getElementsByTag("img").get(0).attr("src");
	}
	
	public static String getName(Element tr) {
		Element td = tr.child(1);
		return td.child(0).text()+" "+td.child(1).text();
	}
	
	public static Double getRating(Element tr) {
		Element td = tr.child(2);
		return Double.parseDouble(td.child(0).text());
	}
	
	

}
