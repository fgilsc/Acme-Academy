package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Student extends Actor {

	public Student()  {
		super();	
	}
//--------------Relationships
	private Collection<Group> groups;
	
	@Valid
	@ManyToMany(cascade=CascadeType.ALL)
	public Collection<Group> getGroups() {
		return groups;
	}
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	
	
}
