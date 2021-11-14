package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Clase para almacenar la información referente a un niño
 * @author Viviana Restrepo Quintero
 * @version 1.0 - 2-11-2021
 */

@Data
@AllArgsConstructor
public class Boy {
    /**La identificación maneja validaciones de campos obligatorios, no dene ser nulo y con un mínimo de 8 caracteres,*/
    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String id;
    /**El nombre maneja validaciones de campos obligatorios, no debe ser nulo y con un mínimo de 2 caracteres y con un máximo de 50 caracteres.*/
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;
    /**La edad tiene la validación que debe ser positiva*/
    @Positive
    private byte age;
    //private String gender;
    /**El género maneja la validación de no debe ser nulo*/
    @NotNull
    private Gender gender;
    /**La localidad del niño debe ser validad (existir) y no puede ser nulo*/
    @Valid
    @NotNull
    private Location location;
    /**El grado del niño tiene validaciones para que no sea nulo*/
    @NotNull
    @Max(value = 5,message = "No existe el grado")
    @Positive
    private byte grade;
    @NotNull
    private boolean orphans;
    private String rh;
}
