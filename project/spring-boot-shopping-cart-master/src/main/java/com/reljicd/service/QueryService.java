package com.reljicd.service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;

public interface QueryService {

	Form getFormsByName(String name);

	ChangeTS getChangeTSFormByName(String name);

	LeaveOfAb getLeaveOfAbFormByName(String name);

}
