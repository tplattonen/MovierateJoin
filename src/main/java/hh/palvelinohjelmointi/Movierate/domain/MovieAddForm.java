package hh.palvelinohjelmointi.Movierate.domain;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MovieAddForm {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
	private String director;
	private double imdbrating;
	private double userrating;
	private String review;
    
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryId")
	private Category category;
    
    
    // Arraylist for 
    private ArrayList<Category> categories = new ArrayList<>();
	
    public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "movieid")
	private Movie movie;
	private String categoriesstring;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDirector() {
		return director;
	}

	public double getImdbrating() {
		return imdbrating;
	}

	public double getUserrating() {
		return userrating;
	}

	public String getReview() {
		return review;
	}

	public Category getCategory() {
		return category;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setImdbrating(double imdbrating) {
		this.imdbrating = imdbrating;
	}

	public void setUserrating(double userrating) {
		this.userrating = userrating;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public MovieAddForm(Long id, String name, String director, double imdbrating, double userrating, String review,
			Category category, String categoriesstring) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.imdbrating = imdbrating;
		this.userrating = userrating;
		this.review = review;
		this.category = category;
		this.categoriesstring = categoriesstring;
	}
    
	@Override
	public String toString() {
		return "MovieAddForm [id=" + id + ", name=" + name + ", director=" + director + ", imdbrating=" + imdbrating
				+ ", userrating=" + userrating + ", review=" + review + ", category=" + category + ", categories="
				+ categories + ", movie=" + movie + ", categoriesstring=" + categoriesstring + "]";
	}

	public String getCategoriesstring() {
		return categoriesstring;
	}

	public void setCategoriesstring(String categoriesstring) {
		this.categoriesstring = categoriesstring;
	}

	public MovieAddForm () {}	
	
    
}