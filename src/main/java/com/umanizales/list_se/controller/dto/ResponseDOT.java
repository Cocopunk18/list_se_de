package com.umanizales.list_se.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Clase que se definió para tener respuestas estandarizadas y siempre responder de la misma manera.
 * Respondemos con ub mensaje de lo que paso.
 * En la data enviamos el contenido de la repuesta.
 * En lista de errores es una estructura para tener un estándar de codificación de los errores por código y descripción que nos ayude a facilitar su interpretación
 */
@Data
@AllArgsConstructor
public class ResponseDOT {
    private String message;
    private Object data;
    private List<ErrorDTO> errors;

}
