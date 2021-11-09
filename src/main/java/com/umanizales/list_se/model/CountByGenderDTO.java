package com.umanizales.list_se.model;


import lombok.AllArgsConstructor;
import lombok.Data;
/**Clase implementada para manejar un contador por género**/
@Data
@AllArgsConstructor
public class CountByGenderDTO {
    private Gender gender;
    private int count;

}
