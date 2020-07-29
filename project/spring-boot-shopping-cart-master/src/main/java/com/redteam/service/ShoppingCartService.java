package com.redteam.service;

import com.redteam.model.ChangeTS;
import com.redteam.model.Form;
import com.redteam.model.LeaveOfAb;

public interface ShoppingCartService {
    
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
