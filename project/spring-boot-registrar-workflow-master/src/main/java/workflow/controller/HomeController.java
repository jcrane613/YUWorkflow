package workflow.controller;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.service.ChangeTSService;
import workflow.service.LeaveOfAbService;
import workflow.util.CurrentState;
import workflow.util.Pager;
import workflow.model.Form;
import workflow.service.FormService;
import workflow.util.ChangeTSFormPager;
import workflow.util.LeaveOfAbPager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private static final int INITIAL_PAGE = 0;

    private final FormService formService;
    private final ChangeTSService changeTSService;
    private final LeaveOfAbService leaveOfAbService;


    @Autowired
    public HomeController(FormService formService, ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
        this.changeTSService = changeTSService;
        this.formService = formService;
        this.leaveOfAbService = leaveOfAbService;
    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam("page") Optional<Integer> page) {
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<Form> forms = formService.findAllFormsPageable(new PageRequest(evalPage, 5));
        List<Form> list = new ArrayList<>();
        for(Form form : forms){
            if(form.getCurrentApprover().equals(CurrentState.getCurrentUsername())) list.add(form);
        }
        Page<Form> allFormsPage = new PageImpl<>(list);
        Pager pager = new Pager(allFormsPage);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("forms", allFormsPage);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @GetMapping("/homeForTSForms")
    public ModelAndView homeForTSForms(@RequestParam("page") Optional<Integer> page) {
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<ChangeTS> TSforms = changeTSService.findAllFormsPageable(new PageRequest(evalPage, 5));
        List<ChangeTS> list = new ArrayList<>();
        for(ChangeTS form : TSforms){
            if(form.getCurrentApprover().equals(CurrentState.getCurrentUsername())) list.add(form);
        }
        Page<ChangeTS> allFormsPage = new PageImpl<>(list);
        ChangeTSFormPager pager = new ChangeTSFormPager(allFormsPage);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("forms", allFormsPage);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/homeForTSForms");
        return modelAndView;
    }

    @GetMapping("/homeForLeaveOfAb")
    public ModelAndView homeForLeaveOfAb(@RequestParam("page") Optional<Integer> page) {
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<LeaveOfAb> leaveOfAbforms = leaveOfAbService.findAllFormsPageable(new PageRequest(evalPage, 5));
        List<LeaveOfAb> list = new ArrayList<>();
        for(LeaveOfAb form : leaveOfAbforms){
            if(form.getCurrentApprover().equals(CurrentState.getCurrentUsername())) list.add(form);
        }
        Page<LeaveOfAb> allFormsPage = new PageImpl<>(list);
        LeaveOfAbPager pager = new LeaveOfAbPager(allFormsPage);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("forms", allFormsPage);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/homeForLeaveOfAb");
        return modelAndView;
    }
    
}
