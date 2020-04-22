package com.safetynet.safetynetalerts;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.tinylog.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Logger.error("!!! Error " + status + " happened !!!");
        
        return new ModelAndView("error");
        
    }
    
}
