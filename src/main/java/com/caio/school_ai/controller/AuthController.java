package com.caio.school_ai.controller;

import com.caio.school_ai.model.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/cadastro")
    public String cadastro(){
        return "auth/cadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastro(@Valid Usuario usuario, BindingResult resultado){
        if(resultado.hasErrors()){
            return "redirect:/cadastro";
        }
        return "";
    }

}
