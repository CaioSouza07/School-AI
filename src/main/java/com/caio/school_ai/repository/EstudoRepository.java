package com.caio.school_ai.repository;

import com.caio.school_ai.model.entity.Estudo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudoRepository extends JpaRepository<Estudo, Long> {
}
