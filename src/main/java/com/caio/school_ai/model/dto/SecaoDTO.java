package com.caio.school_ai.model.dto;

import java.util.List;

public record SecaoDTO(Long id, String nome, String cor, Long agrupamentoId, List<Long> listaEstudosId) {
}
