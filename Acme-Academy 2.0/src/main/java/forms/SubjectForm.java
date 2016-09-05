package forms;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Lecturer;

public class SubjectForm {
	
	private String code, title;
	private Collection<Lecturer> lecturers;
	
	@Pattern(regexp="\\w{2}-\\d{3}")
	@Valid
	@NotBlank
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	@NotNull
	public Collection<Lecturer> getLecturers() {
		return lecturers;
	}
	public void setLecturers(Collection<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}
	
	

}
