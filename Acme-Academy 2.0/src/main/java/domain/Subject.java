package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Subject extends DomainEntity{

	public Subject() {
		super();
	}
	
	private String code, title;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="\\w{2}-\\d{3}")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
//--------------------RELATIONSHIPS-------------------------------------------
	
	private Collection<Group> groups;
	private Collection<Assigment> assigments;
	private Collection<Lecturer> lecturers;
	private Collection<Syllabus> syllabi;
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="subject")
	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="subject")
	public Collection<Assigment> getAssigments() {
		return assigments;
	}

	public void setAssigments(Collection<Assigment> assigments) {
		this.assigments = assigments;
	}
	
	@Valid
	@ManyToMany
	public Collection<Lecturer> getLecturers() {
		return lecturers;
	}

	public void setLecturers(Collection<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="subject")
	public Collection<Syllabus> getSyllabi() {
		return syllabi;
	}
	public void setSyllabi(Collection<Syllabus> syllabi) {
		this.syllabi = syllabi;
	}
	
	
	
	
	
	

}
