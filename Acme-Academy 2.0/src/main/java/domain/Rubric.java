package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Rubric extends DomainEntity{

	public Rubric() {
		super();
	}
	
	private String explanation;
	private Integer percentage;
	
	
	@NotBlank
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanantion) {
		this.explanation = explanantion;
	}
	
	@NotNull
	@Range(min=0,max=100)
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	
	
	//Relationships
	private Assigment assigment;
	private Collection<Assesment> assesments;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Assigment getAssigment() {
		return assigment;
	}
	public void setAssigment(Assigment assigment) {
		this.assigment = assigment;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="rubric")
	public Collection<Assesment> getAssesments() {
		return assesments;
	}
	public void setAssesments(Collection<Assesment> assesments) {
		this.assesments = assesments;
	}
	
	
	
	
	
	
	
	
	

}
