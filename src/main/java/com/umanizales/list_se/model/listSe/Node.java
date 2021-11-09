package com.umanizales.list_se.model.listSe;

import com.umanizales.list_se.model.Boy;
import lombok.Data;
/**Constructor para crear un nodo de listas simplemente enlazadas.
 *Exige el niño como entrada, no puede tener un nodo vacío.
 * No se inicializa el siguiente porque cuando se crea el nodo el siguiente apunta a nulo.
 * @param "data" Parámetro el cuál se obtiene todos los datos del niño.
 * @author Viviana Restrepo Quintero
 * **/
@Data
public class Node {
    private Boy data;
    private Node next;

    public Node(Boy data){
        this.data= data;
    }
}
