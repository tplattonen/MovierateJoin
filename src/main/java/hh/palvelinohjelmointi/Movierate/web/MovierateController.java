package hh.palvelinohjelmointi.Movierate.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.Movierate.domain.CategoryRepository;
import hh.palvelinohjelmointi.Movierate.domain.Movie;
import hh.palvelinohjelmointi.Movierate.domain.MovieRepository;

@Controller
public class MovierateController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	// redirect to movielist after login
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String defis(Model model) {
		model.addAttribute("movies", movieRepository.findAll());
		return "movielist";
	}
	
	
	/** List all movies from database **/
	@RequestMapping(value="/movielist")
	public String movieList(Model model) {
		model.addAttribute("movies", movieRepository.findAll());
		return "movielist";
	}
	
	// RESTful service to get all movies
	@RequestMapping(value="/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {	
		return (List<Movie>) movieRepository.findAll();
	}
	 
	// RESTful service to get book by id *HOX responsebody! -> JSON*
	@RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long Id) {
		return movieRepository.findById(Id);
	}
	
	// Returns empty form for adding new movie
	/*
	@RequestMapping(value="/add")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addmovie";
	}*/
	
	// Delete book using id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
		movieRepository.deleteById(movieId);
		return "redirect:../movielist";
	}
	
	// Saves movie
	/*
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Movie movie){
	     movieRepository.save(movie);
	     return "redirect:movielist";
	} */
	
	/*
	// Edit movie using id
	@RequestMapping (value="/edit/{id}")
	public String editMovie(@PathVariable("id") Long movieId,Model model){
		model.addAttribute("movie",movieRepository.findById(movieId));
		model.addAttribute("categories",categoryRepository.findAll());
		return "editmovie";
	} */
	
	// LOGIN
	@RequestMapping(value="/login")
    public String login() {	
    return "login";
	}	
	
	
}
