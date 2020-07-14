package com.reljicd.controller;

import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.service.FormService;
import com.reljicd.service.ProductService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private static final int INITIAL_PAGE = 0;

    private final FormService formService;

    @Autowired
    public HomeController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam("page") Optional<Integer> page) {

        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Form> forms = formService.findAllFormsPageable(new PageRequest(evalPage, 5));
        Pager pager = new Pager(forms);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("forms", forms);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

}
