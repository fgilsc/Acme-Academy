package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Assigment extends DomainEntity{

	public Assigment() {
		super();
	}
	
	private String title, description;
	private Integer mark;
	private Date openingTime, deadline;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull
	@Range(min= 0,max=100)
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	@NotNull
	@Future
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	
	//-------------RELATIONSHIPS-----------------------------------------------------
	
	private Lecturer lecturer;
	private Subject subject;
	private Group group;
	private Collection<Deliverable>deliverables;
	private Collection<Rubric>rubrics;

	@Valid
	@ManyToOne(optional = false)
	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
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
	@ManyToOne(optional = false)
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="assigment")
	public Collection<Deliverable> getDeliverables() {
		return deliverables;
	}
	public void setDeliverables(Collection<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="assigment")
	public Collection<Rubric> getRubrics() {
		return rubrics;
	}
	public void setRubrics(Collection<Rubric> rubrics) {
		this.rubrics = rubrics;
	}
	
	
	
	
	
	
	
}
