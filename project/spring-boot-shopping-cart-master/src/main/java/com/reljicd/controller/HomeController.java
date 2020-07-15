package com.reljicd.controller;

import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.service.FormService;
import com.reljicd.service.ProductService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Page<Form> formsByApprover1 = formService.findAllFormsPageableByApprover1(new PageRequest(evalPage, 5) , username);
        Page<Form> formsByApprover2 = formService.findAllFormsPageableByApprover2(new PageRequest(evalPage, 5) , username);
        List<Form> list = new ArrayList<>();
        for(Form form : formsByApprover1){
            if(form.getCurrent() == 1){
                list.add(form);
            }
        }
        for(Form form : formsByApprover2){
            if(form.getCurrent() == 2){
                list.add(form);
            }
        }
        Page<Form> allFormsPage = new PageImpl<>(list);
        /*for (Iterator<Form> it = forms.iterator(); it.hasNext(); ) {
            Form form = it.next();
            if(form.getCurrent() != 1){
                it.remove();

            }
        }*/

        Pager pager = new Pager(allFormsPage);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("forms", allFormsPage);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

}
