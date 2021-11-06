package com.umanizales.list_se.controller;

import com.umanizales.list_se.controller.dto.ErrorDTO;
import com.umanizales.list_se.controller.dto.ResponseDOT;
import com.umanizales.list_se.exception.ListaDeException;
import com.umanizales.list_se.exception.ListaSeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que me valida las excepciones de cada uno de los métodos, que se reciban los datos que se necesitan y correctamente.
 */
@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(ListaSeException.class)
    protected ResponseEntity<?> handle(ListaSeException ex){

        String message = ex.getMessage();
        ResponseDOT response = new ResponseDOT(message,null,null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ListaDeException.class)
    protected ResponseEntity<?> handle(ListaDeException ex){
        String message = ex.getMessage();
        ResponseDOT response = new ResponseDOT(message,null,null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex){
        List<ErrorDTO> listErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            listErrors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName +" "+ errorMessage));
        });
        String message = "Algunos campos son inválidos o faltantes, por favor corrija los errores y vuelva a intentarlo";
        ResponseDOT response = new ResponseDOT( message,null , listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
