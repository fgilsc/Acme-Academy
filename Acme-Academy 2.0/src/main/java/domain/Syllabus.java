package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Syllabus extends DomainEntity{

	public Syllabus() {
		super();
		
	}
	
	private Integer academicYear;
	private String summary, evaluationPolicy;
	private Collection<String> goal, prerrequisite;
	
	
	
	@NotNull
	public Integer getAcademicYear() {
		return academicYear;
	}
	
	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}
	
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@NotBlank
	public String getEvaluationPolicy() {
		return evaluationPolicy;
	}
	public void setEvaluationPolicy(String evaluationPolicy) {
		this.evaluationPolicy = evaluationPolicy;
	}
	
	
	@NotEmpty
	@ElementCollection
	@NotNull
	public Collection<String> getGoal() {
		return goal;
	}
	
	public void setGoal(Collection<String> goal) {
		this.goal = goal;
	}
	
	
	@ElementCollection
	@NotNull
	public Collection<String> getPrerrequisite() {
		return prerrequisite;
	}
	public void setPrerrequisite(Collection<String> prerrequisite) {
		this.prerrequisite = prerrequisite;
	}
	
	//--------------------RELATIONSHIPS-------------------------------------------
	private Subject subject;
	private Lecturer lecturer;
	private Collection<BiographyEntry> biographyEntries;



	@Valid
	@ManyToOne(optional = true)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	
	
	@Valid
	@ManyToMany
	public Collection<BiographyEntry> getBiographyEntries() {
		return biographyEntries;
	}

	public void setBiographyEntries(Collection<BiographyEntry> biographyEntries) {
		this.biographyEntries = biographyEntries;
	}
	
	
	
	
	
	
	
	

}
