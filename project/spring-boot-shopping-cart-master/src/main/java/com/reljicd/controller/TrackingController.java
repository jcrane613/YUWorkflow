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

import com.reljicd.model.CommentHolder;
import com.reljicd.model.Form;
import com.reljicd.model.TrackingIdHolder;
import com.reljicd.service.FormService;
import com.reljicd.service.TrackingService;
import com.reljicd.util.CurrentState;

@Controller
public class TrackingController {

	private final TrackingService trackingService;
	private final FormService formService;
	private Form form = null;

    @Autowired
    public TrackingController(TrackingService trackingService, FormService formService) {
        this.trackingService = trackingService;
        this.formService = formService;
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
		CommentHolder commentHolder = new CommentHolder();
		modelAndView.addObject("commentHolder", commentHolder);
		if (this.form != null) {
			modelAndView.addObject("currentApprover", form.getCurrentApprover());
			modelAndView.addObject("denyer", form.getDenyer());
			modelAndView.addObject("comments", form.getCommentsArray());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/tracking/{trackingId}", method = RequestMethod.POST)
	public String addComment(@PathVariable("trackingId") String trackingId, @Valid CommentHolder commentHolder, BindingResult bindingResult) {
		Form form = this.form;
		String commentor = CurrentState.getCurrentUsername();
		if (commentor.equals("anonymousUser")) commentor = "student";
		form.addComment(commentor, commentHolder.getComment());
		formService.saveForm(form);
		return "redirect:/tracking/" + trackingId;
	}

}

