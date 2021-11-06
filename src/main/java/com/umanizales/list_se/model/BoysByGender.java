package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoysByGender {
        private Gender gender;
        private int count;
}

