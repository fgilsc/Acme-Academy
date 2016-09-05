package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class BiographyEntry extends DomainEntity{

	public BiographyEntry() {
		super();
	}
	
	private String title, locator, url;
	private Collection<String> author;
	
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@NotBlank
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	
	@URL
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@NotEmpty
	@ElementCollection
	@NotNull
	public Collection<String> getAuthor() {
		return author;
	}
	public void setAuthor(Collection<String> author) {
		this.author = author;
	}
	
	
	
	//--------------------RELATIONSHIPS-------------------------------------------
	private Collection<Syllabus> syllabi;

	
	@Valid
	@ManyToMany(mappedBy="biographyEntries",fetch = FetchType.EAGER)
	public Collection<Syllabus> getSyllabi() {
		return syllabi;
	}
	public void setSyllabi(Collection<Syllabus> syllabi) {
		this.syllabi = syllabi;
	}
	
	
	
	
	
	
	
	
	

}
