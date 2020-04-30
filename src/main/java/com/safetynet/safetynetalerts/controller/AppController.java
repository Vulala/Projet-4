package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tinylog.Logger;

import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;

@RestController
public class AppController {
    
    @Autowired
    private PersonDAO personDao;
    
    @RequestMapping("/")
    public String viewHomePage() {
        Logger.info("Request of : /");
        return "index";
    }
    
    @RequestMapping("/actuator/health") // Mapping needed for Cucumber
    public String showHealthActuator(Model model) {
        Logger.info("Request of : actuator/health");
        return "actuator/health";
    }
    
    @RequestMapping("/actuator/info") // Mapping needed for Cucumber
    public String showInfoActuator(Model model) {
        Logger.info("Request of : info");
        return "actuator/info";
    }
    
    @RequestMapping("/actuator/metrics") // Mapping needed for Cucumber
    public String showMetricsActuator(Model model) {
        Logger.info("Request of : metrics");
        return "actuator/metrics";
    }
    
    @RequestMapping("/actuator/httptrace") // Mapping needed for Cucumber
    public String showHttptraceActuator(Model model) {
        Logger.info("Request of : httptrace");
        return "actuator/httptrace";
    }
    
    @RequestMapping("actuator/release-notes") // Mapping needed for Cucumber
    public String showReleaseNotesActuator(Model model) {
        Logger.info("Request of : release-notes");
        return "actuator/release-notes";
    }
    
    @GetMapping(value = "/person")
    public Person listPerson() {
        Logger.info("GET request of : /person");
        return personDao.findAll();
    }
    
    @GetMapping(value = "/person/{firstName}") // /!\
    public Person showPersonById(@PathVariable String firstName) {
        Logger.info("GET request of : /person/{firstName}");
        return personDao.findById(firstName);
    }
    
    @PostMapping(value = "/person")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        Logger.info("POST request on : /person");
        
        List<Person> person1 = personDao.save(person);
        if (person == null) {
            return ResponseEntity.noContent().build();
            
        }
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
                // /!\ Need 2 String parameters
                .buildAndExpand(person1.get(1)).toUri();
        return ResponseEntity.created(location).build();
        // need to add a save method replacing data.json?
    }
    
    @DeleteMapping(value = "/person/{firstName}")
    public void deletePerson(@PathVariable String firstName) {
        Logger.info("DELETE request of : /person/{firstName}");
        personDao.deleteById(firstName);
    }
    
}
