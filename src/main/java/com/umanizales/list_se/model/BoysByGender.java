package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**Clase implementada para manejar un contador de niños por género.
 * @author Viviana Restrepo Quintero
 * **/
@Data
@AllArgsConstructor
public class BoysByGender {
        private Gender gender;
        private int count;
}

