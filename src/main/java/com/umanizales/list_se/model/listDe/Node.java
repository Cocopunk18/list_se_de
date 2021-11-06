package com.umanizales.list_se.model.listDe;

import com.umanizales.list_se.model.Boy;
import lombok.Data;
/**Constructor para crear un nodo de listas doblemente enlazadas.
 *Exige el niño como entrada, no puede tener un nodo vacío.
 * No se inicializa el siguiente ni el anterior porque cuando se crea el nodo el siguiente y anterior apunta a nulo.
 * @param "data" Parámetro el cuál se obtiene todos los datos del niño.
 * @author Viviana Restrepo Quintero
 */
@Data
public class Node {
    private Boy data;
    private Node next;
    private Node previous;

    public  Node(Boy data){
        this.data = data;
    }
}
