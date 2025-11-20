package com.caio.school_ai.service;

import com.caio.school_ai.model.dto.CadastroDTO;
import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean validarCadastro(CadastroDTO dados){
        if(this.usuarioRepository.findByEmail(dados.email()) != null){
            return true;
        }else{
            return false;
        }
    }

    public void salvar(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }

}
