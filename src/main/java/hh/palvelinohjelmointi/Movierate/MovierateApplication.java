package hh.palvelinohjelmointi.Movierate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.Movierate.domain.Category;
import hh.palvelinohjelmointi.Movierate.domain.CategoryRepository;
import hh.palvelinohjelmointi.Movierate.domain.Movie;
import hh.palvelinohjelmointi.Movierate.domain.MovieRepository;
import hh.palvelinohjelmointi.Movierate.domain.User;
import hh.palvelinohjelmointi.Movierate.domain.UserRepository;

@SpringBootApplication
public class MovierateApplication {
	private static final Logger log = LoggerFactory.getLogger(MovierateApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MovierateApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieRate(MovieRepository movieRepository, CategoryRepository cRepository, UserRepository uRepository){ 
	return (args) -> {
		log.info("save movie categories and couple demo movies");
		/** Categories **/
		cRepository.save(new Category("Action"));
		cRepository.save(new Category("Adventure"));
		cRepository.save(new Category("Animation"));
		cRepository.save(new Category("Comedy"));
		cRepository.save(new Category("Crime"));
		cRepository.save(new Category("Documentary"));
		cRepository.save(new Category("Drama"));
		cRepository.save(new Category("Historical"));
		cRepository.save(new Category("Horror"));
		cRepository.save(new Category("Family"));
		cRepository.save(new Category("Fantasy"));
		cRepository.save(new Category("Musical"));
		cRepository.save(new Category("Romance"));
		cRepository.save(new Category("Sci-Fi"));
		cRepository.save(new Category("War"));
		cRepository.save(new Category("Western"));
		
		/** Demo movies **/
		movieRepository.save(new Movie("The Hateful Eight", "Quentin Tarantino", 7.8, 8.3, "User review would be here", cRepository.findByName("Drama").get(0), "Drama"));
		movieRepository.save(new Movie("Interstellar", "Christopher Nolan", 8.6, 9.1, "User review would be here", cRepository.findByName("Sci-Fi").get(0), "Sci-Fi"));
		
		
		// Create users: admin/admin, user/user, tume/tume (admin)
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		User user3 = new User("tume", "$2a$04$gk0fPK1DJ58c/ZCNtimj4ejjQPyYVfAjta9QIVDVMZ3l6g23lDXuq", "ADMIN");
		
		uRepository.save(user1);
		uRepository.save(user2);
		uRepository.save(user3);
		
		log.info("fetch all movies");
		for (Movie movie : movieRepository.findAll()) {
			log.info(movie.toString());
		}
		
	};
	}
}
