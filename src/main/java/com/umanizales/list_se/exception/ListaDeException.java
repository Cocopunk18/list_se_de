package com.umanizales.list_se.exception;

/**
 * Clase que permite disparar un mensaje en el sistema cuando pasa algo.
 * Ejemplo 1: "El niño que estamos intentando ingresar ya existe."
 * Ejemplo 2: "La lista se encuentra vacía".
 */
public class ListaDeException extends Exception{

    public ListaDeException(String message){
        super(message);
    }
}
