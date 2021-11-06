package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoysByDegree {
    private Degree degree;
    private int count;
}
