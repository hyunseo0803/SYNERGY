package jina.hyunseo.synergy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
    @GetMapping(value = "/index")
    public String Strart(){
        return "index";
    }
    @GetMapping(value = "/login")
    public String Login(){
        return "login";
    }
    @GetMapping(value = "/signup")
    public String Signup(){
        return "signup";
    }
}
