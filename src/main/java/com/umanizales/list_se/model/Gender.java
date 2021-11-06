package com.umanizales.list_se.model;

/*public enum Gender {
    MASCULINO,FEMENINO;
}*/

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**Clase implementada para manejar los géneros de los niños en un solo objeto
 * Ejemplo Descripción: FEMENINO
 * Ejemplo Descripción: MASCULINO
 * @author Viviana Restrepo Quintero
 * **/

@Data
@AllArgsConstructor
public class Gender {
    @NotNull
    @NotEmpty
    private String description;
}
