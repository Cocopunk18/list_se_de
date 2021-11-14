package com.umanizales.list_se.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoysByGradeRhDTO {
    private byte grade;
    private String RH;
    private int count;
}
