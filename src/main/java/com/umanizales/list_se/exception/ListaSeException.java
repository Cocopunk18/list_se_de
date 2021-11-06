package com.umanizales.list_se.exception;

/**
 * Clase que permite disparar un mensaje en el sistema cuando pasa algo.
 * Ejemplo 1: "El niño que estamos intentando ingresar ya existe."
 * Ejemplo 2: "La lista se encuentra vacía".
 */
public class ListaSeException extends Exception {

    public ListaSeException(String message){
        super(message);
    }
}
