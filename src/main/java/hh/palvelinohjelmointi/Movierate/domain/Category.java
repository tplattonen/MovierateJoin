package hh.palvelinohjelmointi.Movierate.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	//@id creates an ID column for the table
	@Id
	//Generates automatically a unique PK for every new entity object
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryId;
	private String name;
	
	@ManyToMany(mappedBy="categories")
	//private List<Movie> movies;
	private Set<Movie> movies;
	
	
	// CONSTRUCTORS
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Set<Movie> getMovies() {
        return movies;
      }

      public void setMovies(Set<Movie> movies) {
        this.movies = movies;
      }
	
	// GETTERS
	public Long getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	/*public List<Movie> getMovies() {
		return movies;
	} */

	// SETTERS
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public void setMovies(List<Movie> movies) {
		this.movies = movies;
	} */
	
	// Movies attribuutti jätetty tarkoituksella pois tulostuksesta
	// ettei tule ikuista looppia
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}

	
	
}
