package workflow.controller;

import javax.validation.Valid;

import workflow.model.*;
import workflow.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QueryDatabaseController {

	private final QueryService queryService;
	private Form form = null;
	private ChangeTS changeTS = null;
	private LeaveOfAb leaveOfAb = null;

	@Autowired
	public QueryDatabaseController(QueryService queryService) {
		this.queryService = queryService;
	}

	@RequestMapping(value = "/queryDatabase", method = RequestMethod.GET)
	public ModelAndView tracking() {
		ModelAndView modelAndView = new ModelAndView();
		Query query = new Query();
		modelAndView.addObject("query", query);
		modelAndView.setViewName("/queryDatabase");
		this.form = null;
		this.changeTS = null;
		this.leaveOfAb = null;
		return modelAndView;
	}

	@RequestMapping(value = "/queryDatabase", method = RequestMethod.POST)
	public String querySubmit(@Valid Query query, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/queryDatabase";
		} else {
			return "redirect:/queryDatabase/" + query.getStudentName();
		}
	}

	@RequestMapping(value = "/queryDatabase/{studentName}", method = RequestMethod.GET)
	public ModelAndView queryGlobalFirstName(@PathVariable("studentName") String studentName) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/queryReturn");
		CommentHolder commentHolder = new CommentHolder();
		this.form = queryService.getFormsByName(studentName);
		modelAndView.addObject("form", form);
		this.changeTS = queryService.getChangeTSFormByName(studentName);
		modelAndView.addObject("changeTS", changeTS);
		this.leaveOfAb = queryService.getLeaveOfAbFormByName(studentName);
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
	/*
	//query for last name
	@RequestMapping(value = "/queryDatabase/{lastName}", method = RequestMethod.GET)
	public ModelAndView queryGlobalLastName(@PathVariable("lastName") String lastName) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/queryReturn");
		CommentHolder commentHolder = new CommentHolder();
		this.form = queryService.getFormsBylastName(lastName);
		modelAndView.addObject("form", form);
		this.changeTS = queryService.getChangeTSFormBylastName(lastName);
		modelAndView.addObject("changeTS", changeTS);
		this.leaveOfAb = queryService.getLeaveOfAbFormBylastName(lastName);
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

	 */
}

