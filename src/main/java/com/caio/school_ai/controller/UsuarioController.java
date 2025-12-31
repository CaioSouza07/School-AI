package com.caio.school_ai.controller;

import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ModelAndView perfil(){
        var mv = new ModelAndView("usuarios/perfil");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv.addObject("usuario", usuario);
        return mv;
    }

    @PostMapping("/avatar")
    public String uploadAvatar(
            @RequestParam("file")MultipartFile file
            ){
        usuarioService.atualizarAvatar(file);
        return "redirect:/perfil";
    }
}
