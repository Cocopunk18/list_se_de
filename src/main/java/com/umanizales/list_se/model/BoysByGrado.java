package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**Clase implementada para manejar un contador de niños por grado.
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class BoysByGrado {
    private byte grade;
    private int count;
}
