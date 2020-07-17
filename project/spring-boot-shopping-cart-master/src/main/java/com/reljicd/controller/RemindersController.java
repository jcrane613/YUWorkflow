package com.reljicd.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reljicd.model.Form;
import com.reljicd.service.EmailService;
import com.reljicd.service.FormService;
import com.reljicd.service.ProductService;
import com.reljicd.service.RemindersService;
import com.reljicd.service.ShoppingCartService;

@Controller
public class RemindersController {
	
	private final RemindersService remindersService;

    @Autowired
    public RemindersController(RemindersService remindersService) {
        this.remindersService = remindersService;
    }
	
	private String successMessage = "";
	
	@RequestMapping(value = "/reminders", method = RequestMethod.GET)
	public ModelAndView reminders() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/reminders");
		modelAndView.addObject("successMessage", successMessage);
		return modelAndView;
	}
	
	@RequestMapping(value = "/reminders/sendAllReminders", method = RequestMethod.GET)
	public String sendAllReminders() {
		try {
			remindersService.sendAllReminders();
			successMessage = "Reminders Sent Successfully!";
		} catch (MessagingException e) {
			e.printStackTrace();
			successMessage = "There was an error sending the reminders: " + e.getLocalizedMessage();
		}
		return "redirect:/reminders";
	}
	
	
}

