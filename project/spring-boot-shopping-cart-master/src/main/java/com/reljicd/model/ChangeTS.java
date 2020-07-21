package com.reljicd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "changeTS")
public class ChangeTS {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "form")
	private Long id;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your first name")
	private String name;

	@Column(name = "middleName")
	@NotEmpty(message = "*Please provide your middle name")
	private String middleName;

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "mailAddress")
	@NotEmpty(message = "*Please provide your mailing address")
	private String mailAdress;

	@Column(name = "city")
	@NotEmpty(message = "*Please provide your city")
	private String city;

	@Column(name = "state")
	@NotEmpty(message = "*Please provide your email address")
	private String state;

	@Column(name = "zipCode")
	@NotEmpty(message = "*Please provide your zip code")
	private String zipCode;

	@Column(name = "studentEmail")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your email address")
	private String studentEmail;

	@Column (name = "YUID")
	@NotEmpty(message = "*Please provide your YU ID")
	@Length(min = 9, max = 9, message = "*Your YUID must have exactly 9 characters")
	private String YUID;

	@Column(name = "currentProgram")
	@NotEmpty(message = "*Please provide your current jewish studies program")
	private String currentProgram;

	@Column(name = "switchIntoProgam")
	@NotEmpty(message = "*Please provide the program you would like to switch into")
	private String switchIntoProgam;

	@Column(name = "school")
	@NotEmpty(message = "*Please provide the school you are currently enrolled in")
	private String school;

	@Column(name = "currentClass")
	@NotEmpty(message = "*Please provide the program you would like to switch into")
	private String currentClass;

	//need to enter in date of submission to each form

	@Column (name = "phoneNumber")
	@NotEmpty(message = "*Please provide your number")
	@Length(min = 10, max = 10, message = "*Your Phone number must have exactly 10 numbers")
	//@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
	private String phoneNumber;

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

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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


	public String  getYUID() {
		return YUID;
	}

	public void setYUID(String YUID) {
		this.YUID = YUID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	public String getSwitchIntoProgam() {
		return switchIntoProgam;
	}

	public void setSwitchIntoProgam(String switchIntoProgam) {
		this.switchIntoProgam = switchIntoProgam;
	}

	public String getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(String currentProgram) {
		this.currentProgram = currentProgram;
	}
}
