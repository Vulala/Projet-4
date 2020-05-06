package com.safetynet.safetynetalerts.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-notes")
public class ReleaseNotesEndpoint {
    
    String version1_0 = "Version 1.0 \n\n"
            + "The application have been set up:\n -pom.xml configured\n -BDD with cucumber\n -Controller class and index page\n\n ";
    
    String version1_1 = "Version 1.1 \n\n" + "-Requested Actuators enabled\n\n";
    String version1_1_1 = "Version 1.1.1 \n\n" + "-Add of this Release notes endpoint\n\n";
    String version1_2 = "Version 1.2 \n\n" + "-Add of a Custom ErrorController\n"
            + "-Logger implementation with tinylog\n\n";
    String version1_3 = "Version 1.3 \n\n" + "-Endpoints CRUD added\n" + "So are the tests related to it\n\n";
    
    @ReadOperation
    public String releaseNote() {
        return version1_2 + version1_1_1 + version1_1 + version1_0;
    }
    
    @ReadOperation
    public String SelectReleaseNotes(@Selector String selector) {
        if ("1.0".equals(selector))
            return version1_0;
        else if ("1.1".equals(selector))
            return version1_1;
        else if ("1.1.1".equals(selector))
            return version1_1_1;
        else if ("1.2".equals(selector))
            return version1_2;
        else if ("1.3".equals(selector))
            return version1_3;
        else
            return releaseNote();
    }
    
}
