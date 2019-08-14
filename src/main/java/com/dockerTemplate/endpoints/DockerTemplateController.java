package com.dockerTemplate.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerTemplateController {
    @RequestMapping("/hello")
    String home() {
        return "Hello from Spring-Boot <b><i>running an a Docker image!\n";
    }

}
