package workflow.controller;

import javax.validation.Valid;
import workflow.model.*;
import workflow.service.*;
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

	private final FormService formService;
	private final ChangeTSService changeTSService;
	private final LeaveOfAbService leaveOfAbService;

	public QueryDatabaseController(FormService formService, ChangeTSService changeTSService, LeaveOfAbService leaveOfAbService) {
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
		boolean noneFound = false;
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
				modelAndView.addObject("AllForms", formService.findByEmail(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByEmail(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByEmail(input));
				break;
			}
			case "nameContains":
			{
				modelAndView.addObject("AllForms", formService.findByEmail(input));
				modelAndView.addObject("AllChangeTS", changeTSService.findByNameContains(input));
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByEmail(input));
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
				modelAndView.addObject("AllLeaveOfAb", leaveOfAbService.findByLastNameAndName(lastName, firstName));
				break;
			}
		}
		if ( ((ArrayList)modelAndView.getModel().get("AllForms")).isEmpty()
			 && ((ArrayList)modelAndView.getModel().get("AllChangeTS")).isEmpty()
			 && ((ArrayList)modelAndView.getModel().get("AllLeaveOfAb")).isEmpty()) {
			noneFound = true;
		}
		modelAndView.addObject("noneFound", noneFound);
		return modelAndView;
	}
}

