package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**Clase implemtada para manejar los grados de escuela de los niños en un solo objeto
 * Ejemplo grado (1,2,3,4,5)
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class Degree {
    @NotNull
    @NotEmpty
    private Integer degree;
}
