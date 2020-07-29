package com.redteam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redteam.model.ChangeTS;
import com.redteam.model.CommentHolder;
import com.redteam.model.Form;
import com.redteam.model.LeaveOfAb;
import com.redteam.model.TrackingIdHolder;
import com.redteam.service.ChangeTSService;
import com.redteam.service.FormService;
import com.redteam.service.LeaveOfAbService;
import com.redteam.service.TrackingService;
import com.redteam.util.CurrentState;

@Controller
public class TrackingController {
	
	private final TrackingService trackingService;
	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;
	private Form form = null;
	private ChangeTS changeTS = null;
	private LeaveOfAb leaveOfAb = null;

    @Autowired
    public TrackingController(TrackingService trackingService, FormService formService, ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
        this.trackingService = trackingService; 
        this.formService = formService;
        this.changeTSService = changeTSService;
        this.leaveOfAbService = leaveOfAbService;
    }
    
    @RequestMapping(value = "/tracking", method = RequestMethod.GET)
    public ModelAndView tracking() {
		ModelAndView modelAndView = new ModelAndView();
		TrackingIdHolder trackingIdHolder = new TrackingIdHolder();
		modelAndView.addObject("trackingIdHolder", trackingIdHolder);
		modelAndView.setViewName("/tracking");
		this.form = null;
		this.changeTS = null;
		this.leaveOfAb = null;
		return modelAndView;
    }
    
	@RequestMapping(value = "/tracking", method = RequestMethod.POST)
	public String formSubmit(@Valid TrackingIdHolder trackingIdHolder, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/tracking";
		} else {
			return "redirect:/tracking/" + trackingIdHolder.getTrackingId();
		}
	}
	
	@RequestMapping(value = "/tracking/{trackingId}", method = RequestMethod.GET)
	public ModelAndView trackById(@PathVariable("trackingId") String trackingId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/trackingById");
		CommentHolder commentHolder = new CommentHolder();
		this.form = trackingService.getFormByTrackingId(trackingId);
		modelAndView.addObject("form", form);
		this.changeTS = trackingService.getChangeTSFormByTrackingId(trackingId);
		modelAndView.addObject("changeTS", changeTS);
		this.leaveOfAb = trackingService.getLeaveOfAbFormByTrackingId(trackingId);
		modelAndView.addObject("leaveOfAb", leaveOfAb);
		modelAndView.addObject("commentHolder", commentHolder);
		if (this.form != null) {
			modelAndView.addObject("currentApprover", form.getCurrentApprover());
			modelAndView.addObject("denyer", form.getDenyer());
			modelAndView.addObject("comments", form.getCommentsArray());
		}
		else if (this.changeTS != null) {
			modelAndView.addObject("currentApprover", changeTS.getCurrentApprover());
			modelAndView.addObject("denyer", changeTS.getDenyer());
			modelAndView.addObject("comments", changeTS.getCommentsArray());
		}
		else if (this.leaveOfAb != null) {
			modelAndView.addObject("currentApprover", leaveOfAb.getCurrentApprover());
			modelAndView.addObject("denyer", leaveOfAb.getDenyer());
			modelAndView.addObject("comments", leaveOfAb.getCommentsArray());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/tracking/{trackingId}", method = RequestMethod.POST)
	public String addComment(@PathVariable("trackingId") String trackingId, @Valid CommentHolder commentHolder, BindingResult bindingResult) {
		String commentor = CurrentState.getCurrentUsername();
		if (commentor.equals("anonymousUser")) commentor = "student";
		if (this.form != null) {
			form.addComment(commentor, commentHolder.getComment());
			formService.saveForm(form);
		}
		if (this.changeTS != null) {
			changeTS.addComment(commentor, commentHolder.getComment());
			changeTSService.saveForm(changeTS);
		}
		if (this.leaveOfAb != null) {
			leaveOfAb.addComment(commentor, commentHolder.getComment());
			leaveOfAbService.saveForm(leaveOfAb);
		}
		return "redirect:/tracking/" + trackingId;
	}
	
	@RequestMapping(value = "/tracking/withdraw/{trackingId}", method = RequestMethod.GET)
	public String withdraw(@PathVariable("trackingId") String trackingId) {
		if (this.form != null && this.form.getTrackingId().equals(trackingId)) {
			formService.withdrawForm(form);
		}
		if (this.changeTS != null && this.changeTS.getTrackingId().equals(trackingId)) {
			changeTSService.withdrawForm(changeTS);
		}
		if (this.leaveOfAb != null && this.leaveOfAb.getTrackingId().equals(trackingId)) {
			leaveOfAbService.withdrawForm(leaveOfAb);
		}
		return "redirect:/tracking/"  + trackingId;
	}
		
}

