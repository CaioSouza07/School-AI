package com.caio.school_ai.service;

import com.caio.school_ai.model.dto.AgrupamentoDTO;
import com.caio.school_ai.model.dto.EstudoDTO;
import com.caio.school_ai.model.dto.PastaDTO;
import com.caio.school_ai.model.dto.SecaoDTO;
import com.caio.school_ai.repository.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastaService {

    @Autowired
    private PastaRepository pastaRepository;

    public List<PastaDTO> findAllPastasHierarquiaByUsuario(Long usuarioId){
        return pastaRepository.findAllHierarquiaByUsuario_Id(usuarioId).stream()
                .map(pasta -> new PastaDTO(
                        pasta.getId(),
                        pasta.getNome(),
                        pasta.getUsuario().getId(),
                        pasta.getListaAgrupamentos().stream()
                                .map(agrupamento -> new AgrupamentoDTO(
                                        agrupamento.getId(),
                                        agrupamento.getNome(),
                                        pasta.getId(),
                                        agrupamento.getListaSecoes().stream()
                                                .map(secao -> new SecaoDTO(
                                                        secao.getId(),
                                                        secao.getNome(),
                                                        secao.getCor(),
                                                        agrupamento.getId(),
                                                        secao.getListaEstudos().stream()
                                                                .map(estudo -> new EstudoDTO(
                                                                        estudo.getId(),
                                                                        estudo.getTitulo(),
                                                                        estudo.getResumo(),
                                                                        estudo.getMapaMental(),
                                                                        estudo.getQuestoes(),
                                                                        estudo.getSugestoes(),
                                                                        secao.getId()
                                                                )).toList()
                                                )).toList()
                                )).toList()
                )).toList();
    }

}
