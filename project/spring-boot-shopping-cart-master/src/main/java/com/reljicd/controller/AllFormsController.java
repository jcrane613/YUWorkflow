package com.reljicd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
@Controller
public class AllFormsController {

	@GetMapping("/allforms")
	public String allforms() {
		return "/allforms";
	}

}
