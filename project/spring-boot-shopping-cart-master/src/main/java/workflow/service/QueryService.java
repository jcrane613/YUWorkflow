package workflow.service;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.model.Form;

public interface QueryService {

	//first name
	Form getFormsByName(String name);

	ChangeTS getChangeTSFormByName(String name);

	LeaveOfAb getLeaveOfAbFormByName(String name);

	//last name
	Form getFormsBylastName(String lastName);

	ChangeTS getChangeTSFormBylastName(String lastName);

	LeaveOfAb getLeaveOfAbFormBylastName(String lastName);
}
