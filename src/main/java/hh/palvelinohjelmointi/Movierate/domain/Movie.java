package hh.palvelinohjelmointi.Movierate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	
	@Id
	@Column(name="movieid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String director;
	private double imdbrating;
	private double userrating;
	private String review;
	private String categoriesstring;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	 @JoinTable(name = "category_movie", joinColumns = { @JoinColumn(name =
		       "movieid") }, inverseJoinColumns = { @JoinColumn(name = "categoryId") }) 
	
	private Set<Category> categories = new HashSet<Category>(0); 
	@ManyToOne
	private Category category;
	
	// String consisting needed categories for table input
	public void setCategoriesString(String categoriesstring) {
		this.setCategoriesstring(categoriesstring);
	}
	
	 public Set<Category> getCategories() {
	        return categories;
	      }

	      public void setCategories(Set<Category> categories) {
	        this.categories = categories;
	      }
	
	
	
	
	// GETTERS
	public long getId() {
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
	
	// SETTERS
	public void setId(long id) {
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
	
	// CONSTRUCTORS
	public Movie(String name, String director, double imdbrating, double userrating, String review,
			Category category, String categoriesstring) {
		super();
		this.name = name;
		this.director = director;
		this.imdbrating = imdbrating;
		this.userrating = userrating;
		this.review = review;
		this.category = category;
		this.setCategoriesstring(categoriesstring);
	}
	public Movie() {
		this.name = null;
		this.director = null;
		this.imdbrating = 0;
		this.userrating = 0;
		this.review = null;
		this.category = null;
		this.setCategoriesstring(null);
	}
	
	// TOSTRING
	@Override
	public String toString() {
		if (this.category != null)
			return "Movie [id=" + id + ", name=" + name + ", director=" + director + ", imdbrating=" + imdbrating + 
					", userrating=" + userrating + ", review=" + review + ", category=" + this.getCategory() + "]";
		else
			return "Movie [is=" + id + ", name=" + name + ", director=" + director + ", imdbrating=" + imdbrating +
					", userrating=" + userrating + ", review=" + review + "]";
	}

	public String getCategoriesstring() {
		return categoriesstring;
	}

	public void setCategoriesstring(String categoriesstring) {
		this.categoriesstring = categoriesstring;
	}

	
	
}
