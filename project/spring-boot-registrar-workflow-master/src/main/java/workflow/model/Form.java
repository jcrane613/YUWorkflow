package workflow.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "form")
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "form")
	private Long id;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your first name")
	private String name;

	@Column(name = "lastName")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "email")
    @Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your email address")
	private String email;

	@Column(name = "major")
	@NotEmpty(message = "*Please provide the major you would like to declare")
	private String major;

	@Column (name = "yuid")
	@NotEmpty(message = "*Please provide your YU ID")
	@Length(min = 9, max = 9, message = "*Your YUID must have exactly 9 characters")
	private String yuid;

	@Column (name = "phone")
	@NotEmpty(message = "*Please provide your phone number")
	@Length(min = 10, max = 10, message = "*Your Phone number must have exactly 10 numbers")
	//@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
	private String phone;

	@Column(name = "approver1")
	private String approver1;

	@Column(name = "approver2")
	private String approver2;

	@Column(name = "current")
	private Integer current = 1;

	@Column(name = "total_steps")
	private Integer totalSteps = 2;

	@Column(name = "status")
	private String status = "OPEN";

	@Column(name = "tracking_id")
	private String trackingId = UUID.randomUUID().toString();

	@Column(name = "comments", length = 10000)
	private String comments = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public String getApprover1() {
		return approver1;
	}

	public void setApprover1(String approver1) {
		this.approver1 = approver1;
	}

	public String getApprover2() {
		return approver2;
	}

	public void setApprover2(String approver2) {
		this.approver2 = approver2;
	}

	public Integer getCurrent() {
		return current;
	}

	public String getCurrentApprover() {
 		String result = "";
		switch(this.current) {
	 		case 1:
	 			result = this.approver1;
	 			break;
	 		case 2:
	 			result = this.approver2;
	 			break;
 		}
		return result;
	}

	public String getDenyer() {
		int denyStep = this.current * -1;
		String result = "";
		switch(denyStep) {
	 		case 1:
	 			result = this.approver1;
	 			break;
	 		case 2:
	 			result = this.approver2;
	 			break;
		}
		return result;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getTotalSteps() {
		return totalSteps;
	}


	public String getYuid() { return yuid;}

	public void setYuid(String yuid) {
		this.yuid = yuid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public void addComment(String commentor, String comment) {
		this.comments += String.format("Comment from %s at %s -> %s ### ", commentor, (new Date()).toString(), comment);
	}

	public String[] getCommentsArray() {
		return this.comments.split(" ### ");
	}

	
}
