package forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class SocialIdentityForm {
	
	private String socialPlatformName,nick, homepage,email;
	
	
	@NotBlank
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getSocialPlatformName() {
		return socialPlatformName;
	}

	public void setSocialPlatformName(String socialPlatformName) {
		this.socialPlatformName = socialPlatformName;
	}
	
	
	@NotBlank
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@NotBlank
	@SafeHtml(whitelistType= WhiteListType.NONE)
	@URL
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	
	@Email
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
