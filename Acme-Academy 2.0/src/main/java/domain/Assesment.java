package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Assesment extends DomainEntity{

	public Assesment() {
		super();
	}
	
	private Integer points;
	private String explanation;
	
	@NotNull
	@Range(min=0,max=100)
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	@NotBlank
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	//Relationships
	private Deliverable deliverable;
	private Rubric rubric;
	
	
	@Valid
	@ManyToOne(optional = false)
	public Deliverable getDeliverable() {
		return deliverable;
	}
	public void setDeliverable(Deliverable deliverable) {
		this.deliverable = deliverable;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = true)
	public Rubric getRubric() {
		return rubric;
	}
	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}
	
	
	
	
	
	
	

}
