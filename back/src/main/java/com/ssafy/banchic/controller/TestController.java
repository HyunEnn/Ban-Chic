package com.ssafy.banchic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/auth/test")
    public String index() {
        return "loginForm";
    }

    @GetMapping("/auth/test/success")
    public String success() {
        return "loginSuccess";
    }

}
