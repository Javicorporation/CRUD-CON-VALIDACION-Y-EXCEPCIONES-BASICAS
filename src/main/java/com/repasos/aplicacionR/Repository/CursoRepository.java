package com.repasos.aplicacionR.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repasos.aplicacionR.Model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByDuracionBetween(Integer duracionMin, Integer duracionMax);
}
