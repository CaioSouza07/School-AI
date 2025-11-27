package com.caio.school_ai.controller;

import com.caio.school_ai.model.dto.CadastroDTO;
import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest; // Importante
import jakarta.servlet.http.HttpServletResponse; // Importante
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext; // Importante
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository; // Importante
import org.springframework.security.web.context.SecurityContextRepository; // Importante
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @GetMapping
    public String auth() {
        return "auth/index";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "auth/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastro(@Valid CadastroDTO dados, HttpServletRequest request, HttpServletResponse response) {
        if (cadastroService.validarCadastro(dados)) {
            return "redirect:/auth/cadastro?erroEmail=true";
        }
        if (cadastroService.validarSenha(dados)){
            return "redirect:/auth/cadastro?erroSenha=true";
        }

        Usuario usuario = new Usuario(
                dados.nome(),
                dados.email(),
                passwordEncoder.encode(dados.senha())
        );

        cadastroService.salvar(usuario);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                usuario.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return "redirect:/";
    }
}