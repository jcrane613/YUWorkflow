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
public class AdminDashboardController {

	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;
	private int[] totalFormsCache = new int[3];

	@Autowired
	public AdminDashboardController(FormService formService , ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
		this.formService = formService;
		this.changeTSService = changeTSService;
		this.leaveOfAbService = leaveOfAbService;
	}

	@GetMapping("/admin/dashboard")
	public ModelAndView adminDashboard() {
		ModelAndView modelAndView = new ModelAndView();
		totalFormsCache[0] = this.getTotalMajorForms();
		totalFormsCache[1] = this.getTotalChangeTSForms();
		totalFormsCache[2] = this.getTotalLeaveOfAbForms();
		modelAndView.addObject("totalMajorForms", totalFormsCache[0]);
		modelAndView.addObject("totalChangeTSForms", totalFormsCache[1]);
		modelAndView.addObject("totalLeaveOfAbForms", totalFormsCache[2]);
		modelAndView.addObject("homeIsExpanded", false);
		modelAndView.addObject("changeTSIsExpanded", false);
		modelAndView.addObject("leaveOfAbIsExpanded", false);
		modelAndView.setViewName("/adminDashboard");
		return modelAndView;
	}
	
	@GetMapping("/admin/dashboard/home")
	public ModelAndView adminDashboardHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("totalMajorForms", totalFormsCache[0]);
		modelAndView.addObject("totalChangeTSForms", totalFormsCache[1]);
		modelAndView.addObject("totalLeaveOfAbForms", totalFormsCache[2]);
		modelAndView.addObject("majorForms", formService.findAllForms());
		modelAndView.addObject("homeIsExpanded", true);
		modelAndView.setViewName("/adminDashboard");
		return modelAndView;
	}
	
	@GetMapping("/admin/dashboard/changeTS")
	public ModelAndView adminDashboardChangeTS() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("totalMajorForms", totalFormsCache[0]);
		modelAndView.addObject("totalChangeTSForms", totalFormsCache[1]);
		modelAndView.addObject("totalLeaveOfAbForms", totalFormsCache[2]);
		modelAndView.addObject("changeTSForms", changeTSService.findAllForms());
		modelAndView.addObject("changeTSIsExpanded", true);
		modelAndView.setViewName("/adminDashboard");
		return modelAndView;
	}
	
	@GetMapping("/admin/dashboard/leaveOfAb")
	public ModelAndView adminDashboardLeaveOfAb() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("totalMajorForms", totalFormsCache[0]);
		modelAndView.addObject("totalChangeTSForms", totalFormsCache[1]);
		modelAndView.addObject("totalLeaveOfAbForms", totalFormsCache[2]);
		modelAndView.addObject("leaveOfAbForms", leaveOfAbService.findAllForms());
		modelAndView.addObject("leaveOfAbIsExpanded", true);
		modelAndView.setViewName("/adminDashboard");
		return modelAndView;
	}

	private int getTotalMajorForms(){
		return formService.findAllForms().size();
	}

	private int getTotalChangeTSForms(){
		return changeTSService.findAllForms().size();
	}

	private int getTotalLeaveOfAbForms(){
		return leaveOfAbService.findAllForms().size();
	}
}
