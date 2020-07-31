package Workflow.controller;

import Workflow.config.GlobalSettings;
import Workflow.model.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SettingsController {

	private GlobalSettings globalSettings;
	
    @Autowired
    public SettingsController(GlobalSettings globalSettings) {
    	this.globalSettings = globalSettings;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView modelAndView = new ModelAndView();
        Settings settings = new Settings(globalSettings);
        modelAndView.addObject("settings", settings);
        modelAndView.setViewName("/settings");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.POST)
    public ModelAndView createNewSettings(@Valid Settings settings, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/settings");
        } else {
            updateSettings(settings);
        	modelAndView.addObject("successMessage", "Settings saved successfully");
            modelAndView.addObject("settings", new Settings(globalSettings));
            modelAndView.addObject("globalSettings", globalSettings);            
            modelAndView.setViewName("/settings");
        }
        return modelAndView;
    }
    
    private void updateSettings(Settings settings) {
    	globalSettings.setAllowStudentReminders(settings.isAllowStudentReminders());
        globalSettings.setDaysBeforeReminder(settings.getDaysBeforeReminder());
        globalSettings.setRegistrarEmail(settings.getRegistrarEmail());
        globalSettings.setAccessibleWebsiteUrl(settings.getAccessibleWebsiteUrl());     
        
        globalSettings.majorToApproverMap.put("COM", settings.getCom_dean());
        globalSettings.majorToApproverMap.put("ART", settings.getArt_dean());
        globalSettings.majorToApproverMap.put("MAT", settings.getMat_dean());
        globalSettings.majorToApproverMap.put("JST", settings.getJst_dean());
        globalSettings.majorToApproverMap.put("HIS", settings.getHis_dean());
        globalSettings.majorToApproverMap.put("LAW", settings.getLaw_dean());
        globalSettings.majorToApproverMap.put("GEM", settings.getGem_dean());
        
        globalSettings.torahStudiesToApproverMap.put("IBC", settings.getIbc_dean());
        globalSettings.torahStudiesToApproverMap.put("Mechinah/JSS", settings.getJss_dean());
        globalSettings.torahStudiesToApproverMap.put("MYP", settings.getMyp_dean());
        globalSettings.torahStudiesToApproverMap.put("SBMP", settings.getSbmp_dean());

        globalSettings.schoolToDeanMap.put("KATZ", settings.getKatz_dean());
        globalSettings.schoolToDeanMap.put("RIETS", settings.getRiets_dean());

    }
}
