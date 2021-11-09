package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**Clase implementada para manejar el grado, el contador por género y el total de los niños en un solo objeto y dar respuesta al requerimiento del informe solicitado.
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class GendersByGradeDTO {
    private byte grade;
    private List<CountByGenderDTO> gender;
    private int total;
}
