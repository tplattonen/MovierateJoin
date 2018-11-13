package hh.palvelinohjelmointi.Movierate.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hh.palvelinohjelmointi.Movierate.domain.Category;
import hh.palvelinohjelmointi.Movierate.domain.CategoryRepository;
import hh.palvelinohjelmointi.Movierate.domain.Movie;
import hh.palvelinohjelmointi.Movierate.domain.MovieAddForm;
import hh.palvelinohjelmointi.Movierate.domain.MovieRepository;


@Controller
public class MovieAddController {
	@Autowired
    private CategoryRepository categoryRepository; 
	
	@Autowired
    private MovieRepository movieRepository; 
	
	
	// Returns empty form for adding new movie
	@RequestMapping(value = "addmovie", method = RequestMethod.GET)
	public String addCategory(Model model){
		  if (!model.containsAttribute("movieaddform")) {
			    System.out.println("We are here");
			    MovieAddForm movieaddform = new MovieAddForm();
			    System.out.println(movieaddform.getId());
			    model.addAttribute("movieaddform",  movieaddform);
			    model.addAttribute("categories", categoryRepository.findAll());
			  }

	    return "addmovie";
	}  
	
	// Returns filled form for editing purposes
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editMovie(@PathVariable("id") Long movieId,Model model){
		Optional<Movie> movie = movieRepository.findById(movieId);
		if(movie.isPresent()) {
			Movie existingMovie = movie.get();
	    model.addAttribute("movieaddform",  new MovieAddForm(movieId, existingMovie.getName(), existingMovie.getDirector(), existingMovie.getImdbrating(),
	    		existingMovie.getUserrating(), existingMovie.getReview(), existingMovie.getCategory(), existingMovie.getCategoriesstring()));
	    model.addAttribute("categories", categoryRepository.findAll());
	}

	    return "editmovie";
	}
    
	// Method that enables saving of multiple categories
	@RequestMapping(value = "addmovie")
	    public String addmovie(@Valid MovieAddForm movieaddform, 
	    		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	        if (!bindingResult.hasErrors()) { // validation errors
	                
			    		Category category = new Category();
			    		Movie movie = new Movie();
			    		System.out.println(movieaddform.toString());
			    		if (movieaddform.getId() != null) {
			    			movie.setId(movieaddform.getId());
			    		}
			    		movie.setName(movieaddform.getName());
			    		movie.setDirector(movieaddform.getDirector());
			    		movie.setImdbrating(movieaddform.getImdbrating());
			    		movie.setUserrating(movieaddform.getUserrating());
			    		movie.setReview(movieaddform.getReview());
			    		movie.setCategory(categoryRepository.findByName("Drama").get(0));

			    		for (Category i: movieaddform.getCategories()) {
				    		movie.setCategoriesString(i.getName());
			    		}
			    		categoryRepository.save(category);
			    		saveMovie(movie);		
    	}
    	
    	return "redirect:/movielist";    	
	    }   
		 
	    public String saveMovie(Movie movie) {
	    	movieRepository.save(movie);
		return "redirect:/movielist";    	
	}
	
}
