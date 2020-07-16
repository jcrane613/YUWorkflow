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
import java.util.Collection;

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

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "major")
	@NotEmpty(message = "*Please provide the major you would like to switch into")
	private String major;

	@Column (name = "YUID")
	@NotEmpty(message = "*Please provide your YU ID")
	@Length(min = 9, max = 9, message = "*Your YUID must have exactly 9 characters")
	private String YUID;

	@Column(name = "email", unique = true, nullable = false)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
