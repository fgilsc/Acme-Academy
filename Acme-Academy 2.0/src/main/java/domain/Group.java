package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



@Table(name = "AcademicGroup")
@Entity
@Access(AccessType.PROPERTY)
public class Group extends DomainEntity{
	
	public Group(){
		super();
	}
	
	private String name, description;
	private Integer academicYear;
	
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull
	public Integer getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}
	
//-----------------RELATIONSHIPS------------------------------------------
	private Lecturer lecturer;
	private Collection<Student> students;
	private Subject subject;
	private Collection<Assigment>assigments;
	private Collection<Deliverable>deliverables;
	private Collection<LearningMaterial> learningMaterials;
	
	
	
	@Valid
	@ManyToOne(optional = false)
	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	@Valid
	@ManyToMany(cascade=CascadeType.ALL)
	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="group")
	public Collection<Assigment> getAssigments() {
		return assigments;
	}

	public void setAssigments(Collection<Assigment> assigments) {
		this.assigments = assigments;
	}
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="group")
	public Collection<Deliverable> getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(Collection<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="group")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}

	public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}
	
	
	
	

}
