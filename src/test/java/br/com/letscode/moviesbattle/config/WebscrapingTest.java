package br.com.letscode.moviesbattle.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class WebscrapingTest {

	@Test
	public void should_webscraping() {
		Webscraping.main(null);
	}
	
}
