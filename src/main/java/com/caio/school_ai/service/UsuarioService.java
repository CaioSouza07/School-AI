package com.caio.school_ai.service;

import com.caio.school_ai.model.entity.Usuario;
import com.caio.school_ai.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Path pastaAvatares = Paths.get("uploads/avatars");

    public void atualizarAvatar(MultipartFile file){
        if (file.isEmpty() || !file.getContentType().startsWith("image")) {
            throw new RuntimeException("Arquivo inválido");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = (Usuario) auth.getPrincipal();

        try{
            Files.createDirectories(pastaAvatares);

            String nomeArquivo = UUID.randomUUID() + ".png";
            Path caminho = pastaAvatares.resolve(nomeArquivo);

            Files.write(caminho, file.getBytes());

            usuario.setAvatarUrl("uploads/avatars/" + nomeArquivo);

            usuarioRepository.save(usuario);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar usuario: " + e);
        }

    }

}
