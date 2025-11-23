package com.caio.school_ai.service;

import com.caio.school_ai.model.dto.CadastroDTO;
import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CadastroService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean validarCadastro(CadastroDTO dados){
        return this.usuarioRepository.findByEmail(dados.email()) != null;
    }

    public boolean validarSenha(CadastroDTO dados){
        System.out.println(dados.senha());
        System.out.println(dados.confirmacaoSenha());
        System.out.println(Objects.equals(dados.senha(), dados.confirmacaoSenha()));
        return !Objects.equals(dados.senha(), dados.confirmacaoSenha());
    }

    public void salvar(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }

}
