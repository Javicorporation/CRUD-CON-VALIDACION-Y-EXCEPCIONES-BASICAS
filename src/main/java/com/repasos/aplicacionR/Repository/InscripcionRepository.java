package com.repasos.aplicacionR.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repasos.aplicacionR.Model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{
    List<Inscripcion> findByCursoId(long cursoId);

}
