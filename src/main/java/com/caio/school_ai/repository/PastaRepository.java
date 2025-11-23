package com.caio.school_ai.repository;

import com.caio.school_ai.model.entity.Pasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PastaRepository extends JpaRepository<Pasta, Long> {

    @Query("""
        SELECT DISTINCT p 
        FROM Pasta p
        LEFT JOIN FETCH p.listaAgrupamentos a
        LEFT JOIN FETCH a.listaSecoes s
        LEFT JOIN FETCH s.listaEstudos e
        WHERE p.usuario.id = :usuarioId
    """)
    public List<Pasta> findAllHierarquiaByUsuario_Id(@Param("usuarioId") Long usuarioId);

}
