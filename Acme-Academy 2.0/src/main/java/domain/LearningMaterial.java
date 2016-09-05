package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class LearningMaterial extends DomainEntity{

	public LearningMaterial() {
		super();
	}
	
	private String title,content;
	private Collection<String> keywords,notes;
	
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	@ElementCollection
	@NotNull
	public Collection<String> getNotes() {
		return notes;
	}
	public void setNotes(Collection<String> notes) {
		this.notes = notes;
	}
	@URL
	@NotBlank
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@ElementCollection
	@NotNull
	public Collection<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}
	
	//Relationships
	private Lecturer lecturer;
	private Group group;

	@Valid
	@ManyToOne(optional = false)
	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	
	@Valid
	@ManyToOne(optional = true)
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	
	
	
	
	

}
