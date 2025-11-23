package com.caio.school_ai.model.dto;

import java.util.List;

public record AgrupamentoDTO(Long id, String nome, Long pastaId, List<Long> listaSecoesId){
}
