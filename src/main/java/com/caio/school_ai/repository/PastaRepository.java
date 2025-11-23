package com.caio.school_ai.repository;

import com.caio.school_ai.model.entity.Pasta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastaRepository extends JpaRepository<Pasta, Long> {
}
