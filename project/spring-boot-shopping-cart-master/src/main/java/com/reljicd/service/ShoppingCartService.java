package com.reljicd.service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;

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
