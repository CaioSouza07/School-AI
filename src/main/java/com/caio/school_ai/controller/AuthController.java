package com.caio.school_ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public String auth(){
        return "auth/index";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

}
