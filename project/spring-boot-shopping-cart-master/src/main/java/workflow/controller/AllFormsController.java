package workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllFormsController {

	@GetMapping("/allforms")
	public String allforms() {
		return "/allforms";
	}

}
