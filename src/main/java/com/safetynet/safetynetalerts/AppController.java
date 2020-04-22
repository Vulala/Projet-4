package com.safetynet.safetynetalerts;

import org.tinylog.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        Logger.info("Request of : /");
        return "index";
    }
    
    @RequestMapping("/actuator/health") // Mapping needed for Cucumber
    public String showHealthActuator(Model model) {
        Logger.warn("Request of : actuator/health");
        return "actuator/health";
    }
    
    @RequestMapping("/actuator/info") // Mapping needed for Cucumber
    public String showInfoActuator(Model model) {
        Logger.warn("Request of : info");
        return "actuator/info";
    }
    
    @RequestMapping("/actuator/metrics") // Mapping needed for Cucumber
    public String showMetricsActuator(Model model) {
        Logger.warn("Request of : metrics");
        return "actuator/metrics";
    }
    
    @RequestMapping("/actuator/httptrace") // Mapping needed for Cucumber
    public String showHttptraceActuator(Model model) {
        Logger.warn("Request of : httptrace");
        return "actuator/httptrace";
    }
    
    @RequestMapping("actuator/release-notes") // Mapping needed for Cucumber
    public String showReleaseNotesActuator(Model model) {
        Logger.warn("Request of : release-notes");
        return "actuator/release-notes";
    }
}
