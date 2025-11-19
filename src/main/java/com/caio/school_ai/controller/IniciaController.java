package com.caio.school_ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IniciaController {

    @GetMapping
    public String inicial(){
        return "index";
    }

}
