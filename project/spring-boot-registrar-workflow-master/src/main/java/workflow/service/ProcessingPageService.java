package workflow.service;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.model.Form;

public interface ProcessingPageService {
    
    void addForm(Form form);
    
    void addChangeTSForm(ChangeTS form);
    
    void addLeaveOfAbForm(LeaveOfAb form);
    
    void approveForm(Form form);

    void approveChangeTSForm(ChangeTS form);

    void approveLeaveOfAbForm(LeaveOfAb form);
    
    void denyForm(Form form);
    
	void denyChangeTSForm(ChangeTS form);
    
	void denyLeaveOfAbForm(LeaveOfAb form);
    
	Form getForm();

	ChangeTS getChangeTSForm();

	LeaveOfAb getLeaveOfAbForm();

}
