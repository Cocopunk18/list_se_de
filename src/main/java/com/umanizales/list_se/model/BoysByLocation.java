package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**Clase implemtada para manejar un contador de niños por locación
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class BoysByLocation {
    private Location location;
    private int count;
}
