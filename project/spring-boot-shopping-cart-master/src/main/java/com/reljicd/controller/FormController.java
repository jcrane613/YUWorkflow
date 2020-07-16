package com.reljicd.controller;

import com.reljicd.model.Form;
import com.reljicd.model.User;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.EmailService;
import com.reljicd.service.FormService;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

	private Map<String , String> majorToApproverMap = new HashMap<>();
	private final FormService formService;
	private final EmailService emailService;
	private final UserRepository userRepository;

	@Autowired
	public FormController(FormService formService, EmailService emailService, UserRepository userRepository) {
		this.formService = formService;
		this.emailService = emailService;
		this.userRepository = userRepository;
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

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/form");
		} else {

			String approver1 = majorToApproverMap.get((form.getMajor()));
			form.setApprover1(majorToApproverMap.get((form.getMajor())));
			form.setApprover2("approver2");
			formService.saveForm(form);
			
			String approverEmail = userRepository.findByUsername(approver1).get().getEmail();
			try {
				emailService.sendHtmlMessage(approverEmail, "Registrar Forms Update", "You have a new approval!", ("http://localhost:8075/shoppingCart/processForm/"+form.getId()) );
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Submitted successfully! You will receive an email confirmation shortly.");
			modelAndView.addObject("form", new Form());
			modelAndView.setViewName("/form");
		}
		return modelAndView;
	}

}