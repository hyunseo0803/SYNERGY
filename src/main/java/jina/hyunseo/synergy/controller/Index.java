package jina.hyunseo.synergy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
    @GetMapping(value = "/index")
    public String Start() {
        return "index2";
    }

    @GetMapping(value = "/login")
    public String Login() {
        return "login";
    }

    @GetMapping(value = "/signup")
    public String Signup() {
        return "signup";
    }
}
// controller는 경로설정 위주 (기본이 templete! fragment면 앞에 써주기)
