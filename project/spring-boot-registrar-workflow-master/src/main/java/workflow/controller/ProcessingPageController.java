package workflow.controller;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.util.CurrentState;
import workflow.model.CommentHolder;
import workflow.model.Form;
import workflow.service.ChangeTSService;
import workflow.service.EmailService;
import workflow.service.FormService;
import workflow.service.LeaveOfAbService;
import workflow.service.ProcessingPageService;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProcessingPageController {

    private final ProcessingPageService processingPageService;
    private final FormService formService;
    private final ChangeTSService changeTSService;
    private final LeaveOfAbService leaveOfAbService;
    private final EmailService emailService;

    @Autowired
    public ProcessingPageController(ProcessingPageService processingPageService, FormService formService, ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService,
    		EmailService emailService) {
        this.processingPageService = processingPageService;
        this.formService = formService;
        this.changeTSService = changeTSService;
        this.leaveOfAbService = leaveOfAbService;
        this.emailService = emailService;
    }

    @GetMapping("/processingPage")
    public ModelAndView processingPage() {
        ModelAndView modelAndView = new ModelAndView();
        Form form = processingPageService.getForm();
        ChangeTS changeTS = processingPageService.getChangeTSForm();
        LeaveOfAb leaveOfAb = processingPageService.getLeaveOfAbForm();
        if (form != null) {
            modelAndView.addObject("form", form);
        	modelAndView.addObject("comments", form.getCommentsArray());
        }
        if (changeTS != null) {
            modelAndView.addObject("changeTS", changeTS);
            modelAndView.addObject("comments", changeTS.getCommentsArray());
        }
        if (leaveOfAb != null) {
            modelAndView.addObject("leaveOfAb", leaveOfAb);
            modelAndView.addObject("comments", leaveOfAb.getCommentsArray());   
        }     
        CommentHolder commentHolder = new CommentHolder();
		modelAndView.addObject("commentHolder", commentHolder);
		modelAndView.setViewName("/processingPage");
        return modelAndView;
    }
    
    @RequestMapping(value = "/processingPage", method = RequestMethod.POST)
	public String formSubmit(@Valid CommentHolder commentHolder, BindingResult bindingResult) {
		Form form = processingPageService.getForm();
        ChangeTS changeTS = processingPageService.getChangeTSForm();
        LeaveOfAb leaveOfAb = processingPageService.getLeaveOfAbForm();
		if (form != null) {
			form.addComment(CurrentState.getCurrentUsername(), commentHolder.getComment());
			formService.saveForm(form);
		}
		if (changeTS != null) {
			changeTS.addComment(CurrentState.getCurrentUsername(), commentHolder.getComment());
			changeTSService.saveForm(changeTS);
		}
		if (leaveOfAb != null) {
			leaveOfAb.addComment(CurrentState.getCurrentUsername(), commentHolder.getComment());
			leaveOfAbService.saveForm(leaveOfAb);
		}
		return "redirect:/processingPage/";
	}
    
    @GetMapping("/processingPage/processForm/{formId}")
    public String addFormToCart(@PathVariable("formId") Long formId) {
        formService.findById(formId).ifPresent(processingPageService::addForm);
        return "redirect:/processingPage";
    }
    
    @GetMapping("/processingPage/processChangeTSForm/{formId}")
    public String addChangeTSFormToCart(@PathVariable("formId") Long formId) {
        changeTSService.findById(formId).ifPresent(processingPageService::addChangeTSForm);
        return "redirect:/processingPage";
    }
    
    @GetMapping("/processingPage/processLeaveOfAbForm/{formId}")
    public String addLeaveOfAbFormToCart(@PathVariable("formId") Long formId) {
        leaveOfAbService.findById(formId).ifPresent(processingPageService::addLeaveOfAbForm);
        return "redirect:/processingPage";
    }
    
    @GetMapping("/processingPage/approveForm/{formId}")
    public String approveForm(@PathVariable("formId") Long formId) throws MessagingException {
        formService.findById(formId).ifPresent(processingPageService::approveForm);
        emailService.sendNextMessage(formId);
        return "redirect:/home";
    }
    
    @GetMapping("/processingPage/approveChangeTSForm/{formId}")
    public String approveChangeTSForm(@PathVariable("formId") Long formId) throws MessagingException {
        changeTSService.findById(formId).ifPresent(processingPageService::approveChangeTSForm);
        emailService.sendNextChangeTSMessage(formId);
        return "redirect:/homeForTSForms";
    }
    
    @GetMapping("/processingPage/approveLeaveOfAbForm/{formId}")
    public String approveLeaveOfAbForm(@PathVariable("formId") Long formId) throws MessagingException {
        leaveOfAbService.findById(formId).ifPresent(processingPageService::approveLeaveOfAbForm);
        emailService.sendNextLeaveOfAbMessage(formId);
        return "redirect:/homeForLeaveOfAb";
    }
    
    @GetMapping("/processingPage/denyForm/{formId}")
    public String denyForm(@PathVariable("formId") Long formId) {
        Form form = formService.findById(formId).orElse(null);
    	if (form != null) processingPageService.denyForm(form);
        try {
			emailService.sendStudentDenialMessage(form.getEmail(), form.getTrackingId(), form.getDenyer());;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        return "redirect:/home";
    }
    
    @GetMapping("/processingPage/denyChangeTSForm/{formId}")
    public String denyChangeTSForm(@PathVariable("formId") Long formId) {
    	ChangeTS form = changeTSService.findById(formId).orElse(null);
    	if (form != null) processingPageService.denyChangeTSForm(form);
        try {
			emailService.sendStudentDenialMessage(form.getEmail(), form.getTrackingId(), form.getDenyer());;
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
        return "redirect:/homeForTSForms";
    }
    
    @GetMapping("/processingPage/denyLeaveOfAbForm/{formId}")
    public String denyLeaveOfAbForm(@PathVariable("formId") Long formId) {
    	LeaveOfAb form = leaveOfAbService.findById(formId).orElse(null);
    	if (form != null) processingPageService.denyLeaveOfAbForm(form);
        try {
			emailService.sendStudentDenialMessage(form.getEmail(), form.getTrackingId(), form.getDenyer());;
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
        return "redirect:/homeForLeaveOfAb";
    }

}
