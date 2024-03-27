package hello.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {

	// resource/static/index.html => welcome page
	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

}
