package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Clase para almacenar la información referente a un niño
 * @author Viviana Restrepo Quintero
 * @version 1.0 - 2-11-2021
 */

@Data
@AllArgsConstructor
public class Boy {
    /**La identificaicón maneja validaciones de campos obligatorios, no dene ser nulo y con un mínimo de 8 caracteres,*/
    @NotNull
    @NotEmpty
    @Size(min = 8)
    private String id;
    /**El nombre maneja validaciones de campos obligatorios, no debe ser nulo y con un mínimo de 2 caracteres y con un máximo de 50 caracteres,*/
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;
    /**La edad del tiene la validación que debe ser positiva*/
    @Positive
    private byte age;
    //private String gender;
    /**El género maneja la validación de no debe ser nulo*/
    @NotNull
    private Gender gender;
    @Valid
    @NotNull
    /**La localidad del niño debe ser validad (existir) y no puede ser nulo*/
    private Location location;
    /**El grado del niño tiene validaciones para que no sea nulo*/
    @NotNull
    private Degree degree;

    //private boolean orphans;
}
