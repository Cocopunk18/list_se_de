package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**Clase implemtada para manejar paises, departamentos y ciudades en un solo objeto
 * Ejemplo code: 169, Descrption: Colombia
 * Ejemplo code: 17, Descrption: Caldas
 * Ejemplo code: 001, Descrption: Manizales
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class Location {
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String description;

}
