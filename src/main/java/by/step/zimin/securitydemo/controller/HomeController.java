package by.step.zimin.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Hello  home page!";
    }

    @GetMapping("/user")
    public String user(){
        return "Hello, user!";
    }


    @GetMapping("/admin")
    public String admin(){
        return "Hello, Admin!";
    }
}
