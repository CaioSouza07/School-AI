package com.caio.school_ai.controller;

import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.service.PastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estudos")
public class SchoolController {

    @Autowired
    private PastaService pastaService;

    @GetMapping
    public String index(@AuthenticationPrincipal Usuario usuario){
//        var mv = new ModelAndView("estudos/index");
//        var listaPastas = pastaService.findAllPastasHierarquiaByUsuario(usuario.getId());
//        mv.addObject("pastas", listaPastas);
        return "estudos/index";
    }

}
