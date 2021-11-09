package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**Clase implementada para manejar países, departamentos y ciudades en un solo objeto
 * Ejemplo code: 169, Description: Colombia
 * Ejemplo code: 17, Description: Caldas
 * Ejemplo code: 001, Description: Manizales
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
