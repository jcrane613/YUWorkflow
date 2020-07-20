package com.reljicd.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reljicd.model.Form;
import com.reljicd.model.TrackingIdHolder;
import com.reljicd.service.TrackingService;

@Controller
public class TrackingController {
	
	private final TrackingService trackingService;
	private Form form = null;

    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;  
    }
    
    @RequestMapping(value = "/tracking", method = RequestMethod.GET)
    public ModelAndView tracking() {
		ModelAndView modelAndView = new ModelAndView();
		TrackingIdHolder trackingIdHolder = new TrackingIdHolder();
		modelAndView.addObject("trackingIdHolder", trackingIdHolder);
		modelAndView.setViewName("/tracking");
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
		this.form = trackingService.getFormByTrackingId(trackingId);
		modelAndView.addObject("form", form);
		if (this.form != null) {
			modelAndView.addObject("currentApprover", form.getCurrentApprover());
		}
		return modelAndView;
	}
		
}

