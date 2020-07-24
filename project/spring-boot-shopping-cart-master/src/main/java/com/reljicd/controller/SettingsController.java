package com.reljicd.controller;

import com.reljicd.config.GlobalSettings;
import com.reljicd.model.Settings;
import com.reljicd.model.User;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SettingsController {

	private GlobalSettings globalSettings;
	
    @Autowired
    public SettingsController(GlobalSettings globalSettings) {
    	this.globalSettings = globalSettings;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView modelAndView = new ModelAndView();
        Settings settings = new Settings();
        modelAndView.addObject("settings", settings);
        modelAndView.setViewName("/settings");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.POST)
    public ModelAndView createNewSettings(@Valid Settings settings, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/settings");
        } else {
            globalSettings.setAllowStudentReminders(settings.isAllowStudentReminders());
            globalSettings.setDaysBeforeReminder(settings.getDaysBeforeReminder());
            globalSettings.setRegistrarEmail(settings.getRegistrarEmail());
            globalSettings.majorToApproverMap.put("COM", settings.getCOM_routing());
        	modelAndView.addObject("successMessage", "Settings saved successfully");
            modelAndView.addObject("settings", new Settings());
            modelAndView.addObject("globalSettings", globalSettings);            
            modelAndView.setViewName("/settings");
        }
        return modelAndView;
    }
}
