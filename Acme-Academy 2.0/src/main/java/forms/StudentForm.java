package forms;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class StudentForm {
	
	private String name, surname,email, phone;
	
	private String username;
	private String password;
	private String repeatPassword;
	private Boolean valid;
	
	
	@Size(min=5,max=32)
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Size(min=5,max=32)
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Size(min=5,max=32)
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
	
	@SafeHtml(whitelistType= WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@SafeHtml(whitelistType= WhiteListType.NONE)
	@NotBlank
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	
	@Email
	@SafeHtml(whitelistType= WhiteListType.NONE)
	@NotBlank
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	

}
