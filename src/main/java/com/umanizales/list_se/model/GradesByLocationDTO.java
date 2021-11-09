package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**Clase implementada para manejar la locación, el género de cada grado de los niños en un solo objeto y dar respuesta al requerimiento del informe solicitado.
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class GradesByLocationDTO {
    private Location location;
    private List<GendersByGradeDTO> grade;
}
