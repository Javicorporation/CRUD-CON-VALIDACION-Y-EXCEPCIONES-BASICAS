package com.repasos.aplicacionR.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "curso")
public class Curso {

    public Curso() {
    }

    public Curso(long id, String titulo, String descripcion, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    
    private long id;
    @Column
    @NotNull(message = "el titulo no puede ser nulo")
    @NotBlank(message = "el titulo no puede ser un espacio en blanco")


    private String titulo;
    @Column
    @NotNull(message = "la descripcion no puede ser nula")
    @NotBlank(message = "la descripcion no puede ser un espacio en blanco")
    private String descripcion;

    @Column
    @NotNull(message = "la duracion no puede ser nula")
    private int duracion;

    

}
