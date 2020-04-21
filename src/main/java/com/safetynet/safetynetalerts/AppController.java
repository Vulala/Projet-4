package com.safetynet.safetynetalerts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        
        return "index";
    }
    
    @RequestMapping("/actuator/health") // Mapping needed for Cucumber
    public String showHealthActuator(Model model) {
        
        return "actuator/health";
    }
    
    @RequestMapping("/actuator/info") // Mapping needed for Cucumber
    public String showInfoActuator(Model model) {
        
        return "actuator/info";
    }
    
    @RequestMapping("/actuator/metrics") // Mapping needed for Cucumber
    public String showMetricsActuator(Model model) {
        
        return "actuator/metrics";
    }
    
    @RequestMapping("/actuator/httptrace") // Mapping needed for Cucumber
    public String showHttptraceActuator(Model model) {
        
        return "actuator/httptrace";
    }
}
