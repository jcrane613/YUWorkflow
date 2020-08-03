package workflow.controller;

import workflow.model.FilterBy;
import workflow.model.Settings;
import workflow.service.ChangeTSService;
import workflow.service.FormService;
import workflow.service.LeaveOfAbService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDashboardController {

	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;
	private int[] totalFormsCache = new int[3];
	private FilterBy filterBy;

	@Autowired
	public AdminDashboardController(FormService formService , ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
		this.formService = formService;
		this.changeTSService = changeTSService;
		this.leaveOfAbService = leaveOfAbService;
		this.filterBy = new FilterBy();
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
		modelAndView.addObject("majorForms", formService.findAllForms());
		modelAndView.addObject("changeTSForms", changeTSService.findAllForms());
		modelAndView.addObject("leaveOfAbForms", leaveOfAbService.findAllForms());
		modelAndView.addObject("homeIsExpanded", false);
		modelAndView.addObject("changeTSIsExpanded", false);
		modelAndView.addObject("leaveOfAbIsExpanded", false);
		modelAndView.addObject("filterBy", this.filterBy);
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
		modelAndView.addObject("filterBy", this.filterBy);
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
		modelAndView.addObject("filterBy", this.filterBy);
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
		modelAndView.addObject("filterBy", this.filterBy);
		modelAndView.setViewName("/adminDashboard");
		return modelAndView;
	}
	
    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.POST)
    public String filter(@Valid FilterBy filterBy, BindingResult bindingResult) {
    	this.filterBy = filterBy; 
    	return "redirect:/admin/dashboard";
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
