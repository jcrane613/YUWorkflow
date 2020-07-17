package com.reljicd.controller;

import com.reljicd.model.Form;
import com.reljicd.service.FormService;
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

	@Autowired
	public UserDashboardController(FormService formService) {
		this.formService = formService;
	}

	@GetMapping("/userDashboard")
	public ModelAndView userDashboard() {


		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("nMajorForms", this.getNMajorForms());
		modelAndView.setViewName("/userDashboard");
		return modelAndView;
	}

	private int getNMajorForms(){

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		List<Form> formsByApprover1 = formService.findAllFormsByApprover1(username);
		List<Form> formsByApprover2 = formService.findAllFormsByApprover2(username);
		int nMajorForms = 0;
		for(Form form : formsByApprover1){
			if(form.getCurrent() == 1){
				nMajorForms++;
			}
		}
		for(Form form : formsByApprover2){
			if(form.getCurrent() == 2){
				nMajorForms++;
			}
		}
		return nMajorForms;
	}
}
