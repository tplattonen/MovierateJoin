package hh.palvelinohjelmointi.Movierate.web;

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
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCategory(Model model){
	    model.addAttribute("movieaddform",  new MovieAddForm());
	    model.addAttribute("categories", categoryRepository.findAll());
	    return "addmovie";
	}  
	
	// Returns filled form for editing purposes
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editMovie(@PathVariable("id") Long movieId,Model model){
		model.addAttribute("movie",movieRepository.findById(movieId));
	    model.addAttribute("categories", categoryRepository.findAll());
	    return "editmovie";
	}   
    
	// Method that enables saving of multiple categories
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(@Valid MovieAddForm movieaddform, 
	    		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	        if (!bindingResult.hasErrors()) { // validation errors
	                
			    		Category category = new Category();
			    		Movie movie = new Movie();
			    		movie.setName(movieaddform.getName());
			    		movie.setDirector(movieaddform.getDirector());
			    		movie.setImdbrating(movieaddform.getImdbrating());
			    		movie.setUserrating(movieaddform.getUserrating());
			    		movie.setReview(movieaddform.getReview());
			    		movie.setCategory(categoryRepository.findByName("Drama").get(0));
			    		System.out.println(movieaddform.getCategory());
			    		System.out.println(movieaddform.getCategories());

			    		for (Category i: movieaddform.getCategories()) {
			    			System.out.println(i.getName());
				    		movie.setCategoriesString(i.getName());
			    		}
			    		categoryRepository.save(category);
			    		movieRepository.save(movie);		
    	}
    	
    	return "redirect:/movielist";    	
    }   
	
}
