package com.caio.school_ai.model.dto;

import com.caio.school_ai.model.entity.Agrupamento;

import java.util.List;

public record PastaDTO(Long id, String nome, Long usuarioId, List<AgrupamentoDTO> listaAgrupamentos) {
}
