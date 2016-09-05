package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Deliverable extends DomainEntity{

	public Deliverable() {
		super();		
	}
	
	private int uploaderStudentID;
	private Date moment;
	private String content;
	
	@Valid
	@NotNull
	public int getUploaderStudentID() {
		return uploaderStudentID;
	}
	public void setUploaderStudentID(int uploaderStudentID ) {
		this.uploaderStudentID = uploaderStudentID;
	}
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	@URL
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	//------------RELATIONSHIPS---------------------------------------------------------
	
	private Group group;
	private Assigment assigment;
	private Collection<Assesment> assesments;

	@Valid
	@ManyToOne(optional = false)
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
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
	@OneToMany(mappedBy="deliverable")
	public Collection<Assesment> getAssesments() {
		return assesments;
	}
	public void setAssesments(Collection<Assesment> assesments) {
		this.assesments = assesments;
	}
	
	
	
	
	

}
