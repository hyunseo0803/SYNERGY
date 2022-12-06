package jina.hyunseo.synergy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class indexController{
    // private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        
        return "index";
    }
    
}
