package workflow.controller;

import workflow.util.CurrentState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/findDashboard";
        }
        return "/login";
    }
    
    @GetMapping("/findDashboard")
    public String findDashboard() {
    	if (CurrentState.getCurrentUsername().equals("admin")) return "redirect:/admin/dashboard";
    	return "redirect:/userDashboard";
    }
    
}
