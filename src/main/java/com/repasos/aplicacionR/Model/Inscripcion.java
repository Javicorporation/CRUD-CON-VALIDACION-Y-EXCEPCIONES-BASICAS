package com.repasos.aplicacionR.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "inscripcion")
public class Inscripcion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "estudianteId")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cursoId")
    private Curso curso;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "la fecha de inscripcion no puede ser nula")
    private Date fechaInscripcion;

}
