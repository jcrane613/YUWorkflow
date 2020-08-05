package workflow.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.ui.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class Query {

	private String input;

	private String type;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}