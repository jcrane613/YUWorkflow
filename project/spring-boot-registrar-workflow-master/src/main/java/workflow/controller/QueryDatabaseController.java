package workflow.controller;

import javax.validation.Valid;

import workflow.config.GlobalSettings;
import workflow.model.*;
import workflow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QueryDatabaseController {

	private Optional<Form> form;
	private Optional<ChangeTS> changeTS;
	private Optional<LeaveOfAb> leaveOfAb ;
	private final TrackingService trackingService;
	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;

	public QueryDatabaseController(TrackingService trackingService,FormService formService, ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
			this.trackingService = trackingService;
			this.formService = formService;
			this.changeTSService = changeTSService;
			this.leaveOfAbService = leaveOfAbService;
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
			return "redirect:/queryDatabase/" +query.getType() + "/" +query.getInput();
		}
	}

	@RequestMapping(value = "/queryDatabase/{type}/{input}", method = RequestMethod.GET)
	public ModelAndView queryGlobalFirstName(@PathVariable("type") String type, @PathVariable("input") String input) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/queryReturn");
		switch (type){
			case "firstName":
			{
				modelAndView.addObject("AllForms", formService.findByName(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByName(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByName(input));
				break;
			}
			case "lastName":
			{
				modelAndView.addObject("AllForms", formService.findByLastName(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByLastName(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByLastName(input));
				break;
			}
			case "YUID":
			{
				modelAndView.addObject("AllForms", formService.findByYuid(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByYuid(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByYuid(input));
				break;
			}
			case "studentEmail":
			{
				modelAndView.addObject("AllForms", formService.findByStudentEmail(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByStudentEmail(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByStudentEmail(input));
				break;
			}
			case "phoneNumber":
			{
				modelAndView.addObject("AllForms", formService.findByPhoneNumber(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByPhoneNumber(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByPhoneNumber(input));
				break;
			}
			case "bothNames":
			{
				String[] currencies = input.split(" ");
				//Handles the error if the user tries to search based on first and last name but only inputs one string
				if (currencies.length != 2)
				{
					return new ModelAndView("/nameError");
				}
				String firstName = currencies[0];
				String lastName = currencies[1];
				modelAndView.addObject("AllForms", formService.findByLastNameAndName(lastName, firstName));
				modelAndView.addObject("AllChangeTS", changeTSService.findByLastNameAndName(lastName, firstName));
				modelAndView.addObject("AllLeaveOfAbFirstName", leaveOfAbService.findByLastNameAndName(lastName, firstName));
				break;
			}
		}
		return modelAndView;
	}

/*
	Tracking data code


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

