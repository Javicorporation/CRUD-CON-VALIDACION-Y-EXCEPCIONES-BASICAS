package com.repasos.aplicacionR.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {
    
    public Estudiante() {
    }

    public Estudiante(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    @NotBlank(message = "el nombre no puede ser un espacio vacio")
    @NotNull(message = "el nombre no puede ser nulo")
    private String name;

    @Column
    @NotBlank(message = "el email no puede ser un espacio vacio")
    @NotNull(message = "el email no puede ser nulo")
    @Email(message = "el email no tiene la estructura correcta")
    private String email;


}
