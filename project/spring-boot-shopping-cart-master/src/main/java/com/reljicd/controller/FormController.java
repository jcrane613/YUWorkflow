package com.reljicd.controller;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.ChangeTSService;
import com.reljicd.service.EmailService;
import com.reljicd.service.FormService;
import com.reljicd.service.LeaveOfAbService;
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
	private Map<String , String> torahStudiesToApproverMap = new HashMap<>();
	private Map<String , String> schoolToDeanMap = new HashMap<>();
	private final FormService formService;
	private final EmailService emailService;
	private final UserRepository userRepository;
	private final LeaveOfAbService leaveOfAbService;
	private final ChangeTSService changeTSService;

	@Autowired
	public FormController(FormService formService, EmailService emailService, UserRepository userRepository, LeaveOfAbService leaveOfAbService, ChangeTSService changeTSService) {
		this.formService = formService;
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.leaveOfAbService = leaveOfAbService;
		this.changeTSService = changeTSService;
		populateMap();
	}
	private void populateMap(){
		//This is the form table for the major decleartion form
		majorToApproverMap.put("COM" , "approver1");
		majorToApproverMap.put("ART" , "approver2");
		majorToApproverMap.put("MAT" , "approver3");
		majorToApproverMap.put("JST" , "approver4");
		majorToApproverMap.put("HIS" , "approver5");
		majorToApproverMap.put("LAW" , "approver6");
		majorToApproverMap.put("GEM" , "approver7");

		//This is the form table for the change of torah studies
		torahStudiesToApproverMap.put("IBC" , "approver1");
		torahStudiesToApproverMap.put("Mechinah/JSS" , "approver2");
		torahStudiesToApproverMap.put("MYP" , "approver3");
		torahStudiesToApproverMap.put("SBMP" , "approver4");

		//This is the form table for the leave of absence
		schoolToDeanMap.put("KATZ", "approver1");
		schoolToDeanMap.put("RIETS", "approver2");
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
			String approver1Email = userRepository.findByUsername(approver1).get().getEmail();
			try {
				emailService.sendNewApprovalHtmlMessage(approver1Email, ("http://localhost:8070/shoppingCart/processForm/"+form.getId()) );
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				emailService.sendInitialStudentMessage(form.getStudentEmail(), form.getTrackingId());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Submitted successfully! You will receive an email confirmation shortly.");
			modelAndView.addObject("form", new Form());
			modelAndView.setViewName("/form");
		}
		return modelAndView;
	}

	//This is the code for changing the Torah Study Form
	@RequestMapping(value = "/changeTS", method = RequestMethod.GET)
	public ModelAndView changeTS() {
		ModelAndView modelAndView = new ModelAndView();
		ChangeTS changeTS = new ChangeTS();
		modelAndView.addObject("changeTS", changeTS);
		modelAndView.setViewName("/changeTS");
		return modelAndView;
	}

	@RequestMapping(value = "/changeTS", method = RequestMethod.POST)
	public ModelAndView changeTS(@Valid ChangeTS changeTS, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors())
		{
			modelAndView.setViewName("/changeTS");
		}
		else {
			String approver1 = torahStudiesToApproverMap.get((changeTS.getSwitchIntoProgam()));
			changeTS.setApprover1(approver1);
			changeTS.setApprover2("approver2");
			changeTS.setApprover3(torahStudiesToApproverMap.get((changeTS.getCurrentProgram())));
			changeTSService.saveForm(changeTS);

			String approver1Email = userRepository.findByUsername(approver1).get().getEmail();
			try {
				emailService.sendNewApprovalHtmlMessage(approver1Email, ("http://localhost:8070/shoppingCart/processChangeTSForm/"+changeTS.getId()) );
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				emailService.sendInitialStudentMessage(changeTS.getStudentEmail(), changeTS.getTrackingId());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Submitted successfully! You will receive an email confirmation shortly.");
			modelAndView.addObject("changeTS", new ChangeTS());
			modelAndView.setViewName("/changeTS");
		}
		return modelAndView;

	}

	//This is the controller for the leave of absence form

	@RequestMapping(value = "/leaveOfAb", method = RequestMethod.GET)
	public ModelAndView leaveOfAb() {
		ModelAndView modelAndView = new ModelAndView();
		LeaveOfAb leaveOfAb = new LeaveOfAb();
		modelAndView.addObject("leaveOfAb", leaveOfAb);
		modelAndView.setViewName("/leaveOfAb");
		return modelAndView;
	}

	@RequestMapping(value = "/leaveOfAb", method = RequestMethod.POST)
	public ModelAndView leatveOfAb(@Valid LeaveOfAb leaveOfAb, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/leaveOfAb");
		}
		else {
			leaveOfAb.setApprover1("approver1");//This is the registrar
			String approver2 = schoolToDeanMap.get((leaveOfAb.getSchool()));
			leaveOfAb.setApprover2(approver2);
			leaveOfAb.setApprover3("approver1");//this is for the registrar
			leaveOfAbService.saveForm(leaveOfAb);

			String approver1Email = userRepository.findByUsername(approver2).get().getEmail();
			try {
				emailService.sendNewApprovalHtmlMessage(approver1Email, ("http://localhost:8070/shoppingCart/processLeaveOfAbForm/"+leaveOfAb.getId()) );
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				emailService.sendInitialStudentMessage(leaveOfAb.getStudentEmail(), leaveOfAb.getTrackingId());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Submitted successfully! You will receive an email confirmation shortly.");
			modelAndView.addObject("leaveOfAb", new LeaveOfAb());
			modelAndView.setViewName("/leaveOfAb");
		}
		return modelAndView;
	}
}
