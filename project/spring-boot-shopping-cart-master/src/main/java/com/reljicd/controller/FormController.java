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

@Controller
public class FormController {

	private final FormService formService;

	@Autowired
	public FormController(FormService formService) {
		this.formService = formService;
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


			formService.saveForm(form);
			/*if(form.getMajor() == ART){
				form.aprovver = "dean joe"
			}*/

			modelAndView.addObject("successMessage", "Submitted successfully");
			modelAndView.addObject("form", new Form());
			modelAndView.setViewName("/form");

		return modelAndView;
	}

}