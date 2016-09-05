package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity{

	public SocialIdentity() {
		super();
	}
	
	
	private String socialPlatformName, nick, homepage, email;

	@NotBlank
	public String getSocialPlatformName() {
		return socialPlatformName;
	}


	public void setSocialPlatformName(String socialPlatformName) {
		this.socialPlatformName = socialPlatformName;
	}

	@NotBlank
	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@URL
	@NotBlank
	public String getHomepage() {
		return homepage;
	}


	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Email
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	//Relationships
	private Actor actor;
	
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}


	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
	
	

}
