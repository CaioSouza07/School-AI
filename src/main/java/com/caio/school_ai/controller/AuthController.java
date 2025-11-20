package com.caio.school_ai.controller;

import com.caio.school_ai.model.dto.CadastroDTO;
import com.caio.school_ai.model.dto.LoginDTO;
import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.model.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public String auth(){
        return "auth/index";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "auth/cadastro";
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid CadastroDTO dados){
        if(this.cadastroService.validarCadastro(dados)) return ResponseEntity.badRequest().build();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());

        Usuario usuario = new Usuario(dados.nome(), dados.email(), senhaCriptografada);

        this.cadastroService.salvar(usuario);

        return ResponseEntity.ok().build();

    }

}
