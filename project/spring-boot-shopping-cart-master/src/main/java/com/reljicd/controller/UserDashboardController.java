package com.reljicd.controller;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.service.ChangeTSService;
import com.reljicd.service.FormService;
import com.reljicd.service.LeaveOfAbService;
import com.reljicd.util.CurrentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDashboardController {

	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;

	@Autowired
	public UserDashboardController(FormService formService , ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
		this.formService = formService;
		this.changeTSService = changeTSService;
		this.leaveOfAbService = leaveOfAbService;
	}

	@GetMapping("/userDashboard")
	public ModelAndView userDashboard() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("nMajorForms", this.getNMajorForms());
		modelAndView.addObject("nChangeTSForms", this.getNChangeTSForms());
		modelAndView.addObject("nLeaveOfAbForms", this.getNLeaveOfAbsForms());
		modelAndView.setViewName("/userDashboard");
		return modelAndView;
	}

	private int getNMajorForms(){
		int nMajorForms = 0;
		for(Form form : formService.findAllForms()){
			if(form.getCurrentApprover().equals(CurrentState.getCurrentUsername())){
				nMajorForms++;
			}
		}
		return nMajorForms;
	}

	private int getNChangeTSForms(){
		String username = CurrentState.getCurrentUsername();
		List<ChangeTS> formsByApprover1 = changeTSService.findAllFormsByApprover1(username);
		List<ChangeTS> formsByApprover2 = changeTSService.findAllFormsByApprover2(username);
		int nMajorForms = 0;
		for(ChangeTS form : formsByApprover1){
			if(form.getCurrent() == 1){
				nMajorForms++;
			}
		}
		for(ChangeTS form : formsByApprover2){
			if(form.getCurrent() == 2){
				nMajorForms++;
			}
		}
		return nMajorForms;
	}

	private int getNLeaveOfAbsForms(){
		String username = CurrentState.getCurrentUsername();
		List<LeaveOfAb> LOAformsByApprover1 = leaveOfAbService.findAllFormsByApprover1(username);
		List<LeaveOfAb> LOAformsByApprover2 = leaveOfAbService.findAllFormsByApprover2(username);
		int nMajorForms = 0;
		for(LeaveOfAb form : LOAformsByApprover1){
			if(form.getCurrent() == 1){
				nMajorForms++;
			}
		}
		for(LeaveOfAb form : LOAformsByApprover2){
			if(form.getCurrent() == 2){
				nMajorForms++;
			}
		}
		return nMajorForms;
	}
}
