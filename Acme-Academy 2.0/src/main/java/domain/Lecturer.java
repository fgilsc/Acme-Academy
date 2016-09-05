package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ManyToMany;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;



@Entity
@Access(AccessType.PROPERTY)
public class Lecturer extends Actor {

	public Lecturer() {
		super();
	}
	
//-----------------RELATIONSHIPS
	
	private Collection<Group> groups;
	private Collection<Assigment> assigments;
	private Collection<Subject> subjects;
	private Collection<LearningMaterial> learningMaterials;
	private Collection<Syllabus> syllabus;
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="lecturer")
	public Collection<Group> getGroups() {
		return groups;
	}
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="lecturer")
	public Collection<Assigment> getAssigments() {
		return assigments;
	}
	public void setAssigments(Collection<Assigment> assigments) {
		this.assigments = assigments;
	}
	
	@NotNull
	@Valid
	@ManyToMany(mappedBy="lecturers")
	public Collection<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="lecturer")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}
	public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}
	
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="lecturer")
	public Collection<Syllabus> getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(Collection<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}
	
	
	
}
