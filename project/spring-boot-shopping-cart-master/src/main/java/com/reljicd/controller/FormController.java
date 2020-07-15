package com.reljicd.controller;

import com.reljicd.model.Form;
import com.reljicd.model.User;
import com.reljicd.service.FormService;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

	private Map<String , String> majorToApproverMap = new HashMap<>();
	private final FormService formService;

	@Autowired
	public FormController(FormService formService) {
		this.formService = formService;
		populateMap();
	}
	private void populateMap(){
		majorToApproverMap.put("COM" , "approver1");
		majorToApproverMap.put("ART" , "approver2");
		majorToApproverMap.put("MAT" , "approver3");
		majorToApproverMap.put("JST" , "approver4");
		majorToApproverMap.put("HIS" , "approver5");
		majorToApproverMap.put("LAW" , "approver6");
		majorToApproverMap.put("GEM" , "approver7");
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView();
		Form form = new Form();
		modelAndView.addObject("form", form);
		modelAndView.setViewName("/form");
		return modelAndView;
	}
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView formSubmit(@Valid Form form, BindingResult bindingResult) {



		ModelAndView modelAndView = new ModelAndView();


			form.setApprover1(majorToApproverMap.get((form.getMajor())));
			form.setApprover2("user");
			formService.saveForm(form);


		modelAndView.addObject("successMessage", "Submitted successfully");
			modelAndView.addObject("form", new Form());
			modelAndView.setViewName("/form");

		return modelAndView;
	}

}