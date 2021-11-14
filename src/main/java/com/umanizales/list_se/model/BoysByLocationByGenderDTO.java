package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoysByLocationByGenderDTO {
    private Location location;
    private List<BoysByGenderByGradeDTO> gender;
    private int count;

}
