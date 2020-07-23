package com.reljicd.controller;

import com.reljicd.model.ChangeMajorDeclarationApproversHolder;
import com.reljicd.model.Form;
import com.reljicd.model.Settings;
import com.reljicd.model.User;
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
import java.util.List;

@Controller
public class SettingsController {

    private final Settings settings;
    private final FormService formService;

    @Autowired
    public SettingsController(Settings settings, FormService formService) {
        this.formService = formService;
        this.settings = settings;
    }

    @RequestMapping(value = "/admin/allSettings", method = RequestMethod.GET)
    public ModelAndView allSettings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/allSettings");
        return modelAndView;
    }

    @RequestMapping(value =  "/admin/allSettings/changeApproversSettingsHome" , method = RequestMethod.GET)
    public ModelAndView approverSettingsHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/changeApproversSettingsHome");
        return modelAndView;
    }
    @RequestMapping(value =  "/admin/allSettings/changeApproversSettingsHome/changeMajorDeclarationApprovers" , method = RequestMethod.GET)
    public ModelAndView changeMajorDeclarationApproversGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("changeMajorDeclarationApproversHolder", new ChangeMajorDeclarationApproversHolder());
        modelAndView.setViewName("/changeMajorDeclarationApprovers");
        return modelAndView;
    }

    @RequestMapping(value =  "/admin/allSettings/changeApproversSettingsHome/changeMajorDeclarationApprovers" , method = RequestMethod.POST)
    public ModelAndView changeMajorDeclarationApprovers(ChangeMajorDeclarationApproversHolder changeMajorDeclarationApproversHolder) {
        ModelAndView modelAndView = new ModelAndView();
        String newApprover = changeMajorDeclarationApproversHolder.getChangeTo();
        String oldApprover = "approver1";//gotta get this
        String major = changeMajorDeclarationApproversHolder.getMajor();

        //Change all that exist in DB already
        List<Form> allFormsInDB = formService.findAllFormsByApprover1(oldApprover);
        for(Form form : allFormsInDB){
            form.setApprover1(newApprover);
            formService.saveForm(form);
        }
        modelAndView.addObject("successMessage", "Submitted successfully!");
        modelAndView.addObject("changeMajorDeclarationApproversHolder", new ChangeMajorDeclarationApproversHolder());
        modelAndView.setViewName("/changeMajorDeclarationApprovers");
        return modelAndView;

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
            this.settings.setAllowStudentReminders(settings.isAllowStudentReminders());
        	modelAndView.addObject("successMessage", "Settings saved successfully");
            modelAndView.addObject("settings", new Settings());
            modelAndView.setViewName("/settings");
        }
        return modelAndView;
    }
}
