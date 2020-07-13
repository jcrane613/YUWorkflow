package com.reljicd.model;

import javax.persistence.*;

import java.util.ArrayList;

@Entity
public class Workflow {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	private ArrayList<User> flow;
	
	@Column(name = "currentStep") 
	private Integer current = new Integer(0);
	
	public ArrayList<User> getFlow() {
		return flow;
	}
	public void setFlow(ArrayList<User> flow) {
		this.flow = flow;
	}
	public User getCurrentUser() {
		return flow.get(current);
	}
	public Integer getCurrentStep() {
		return current;
	}
	public void setCurrentStep(Integer i) {
		this.current = i;
	}
	
	
	
}
