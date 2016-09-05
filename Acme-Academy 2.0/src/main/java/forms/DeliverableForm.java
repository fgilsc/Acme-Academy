package forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

public class DeliverableForm {
	
	private int uploaderStudentID;
	private Date moment;
	private String content;
	
	
	public int getUploaderStudentID() {
		return uploaderStudentID;
	}
	public void setUploaderStudentID(int uploaderStudentID) {
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
	@SafeHtml(whitelistType= WhiteListType.NONE)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
