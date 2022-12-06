package jina.hyunseo.synergy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jina.hyunseo.synergy.dto.SessionUser;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class indexController{
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("name", user.getName());
            model.addAttribute("picture", user.getPicture());
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        
        return "home";
    }
    
}
