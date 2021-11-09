package com.umanizales.list_se.model.listSe;
/**
 *Clase que me permite gestionar una lista simplemente enlazada
 * Solo cuenta con los atributos cabeza (head) que representa el primer niño
 * Como la cabeza solo puede ver el primero tenemos un atributo contador para saber cuantos niños tenemos en la lista.
 * Teniendo la referencia de la cabeza de la lista podemos acceder a todos los elementos recorriendo la lista.
 * @author Viviana Restrepo Quintero
 */

import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.Boy;
import com.umanizales.list_se.model.BoysByLocation;
import com.umanizales.list_se.model.Location;
import com.umanizales.list_se.model.listDe.ListDE;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    /**
     * Atributo que representa el inicio de la lista y es el único que podemos ver.*/
    private Node head;
    /** atributo contador para saber cuantos niños tenemos en la lista.*/
    private int count;

    /**
     * Método que adiciona un niño al final de la lista.
     * @param boy parámetro donde llegan todos los datos del niño.
     * @throws ListaSeException Excepción que puede generar el método cuando el nño que se está ingresando ya existe.
     */
    public boolean add(Boy boy) throws ListaSeException {
        /**
         * Se invoca el método encontrar identificación para verificar si el niño que está ingresando ya existe.
         * */
        Boy boyExist = finById(boy.getId());
        if (boyExist != null) {
            /**
             * Si el niño ya existe se lanza una excepción para informar al usuario final.
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Validamos si la cabeza esta vacía, de ser asi le decimos el nuevo niño que sea la cabeza.
         */
        if (this.head == null) {
            this.head = new Node(boy);
        } else {
            /**
             * Si la cabeza no está vacía, llamamos un ayudante llamado temp y le damos la indicación de que se ubique ne la cabeza o inicio de la lista.
             * Recorremos la lista mientras el siguiente del ayudante no sea nulo.
             */
            Node temp = this.head;
            while (temp.getNext() != null) {
                /**
                 * Comparamos si la identificación del niño que nos está llegado ya existe, si es asi retornamos false para que se lance la excepción
                 */
                if (boy.getId().equals(temp.getData().getId())) {
                    return false;
                }
                temp = temp.getNext();
            }
            /**
             * Si el niño no existe el ayudante queda ubicado en el último y le decimos que su siguiente es el nuevo niño
             */
            temp.setNext(new Node(boy));
        }
        /**
         * Aumentamos el contador cuando se adicione el nuevo niño
         */
        count++;
        return true;
    }

    /**
     * Método que adiciona el niño nuevo al inicio de la lista.
     * @param boy parámetro donde llegan todos los datos del niño.
     * @throws ListaSeException Excepción que pude generar el método cuando el niño que se está ingresando ya existe.
     */
    public void addToStart(Boy boy) throws ListaSeException {
        /**
         * Se invoca el método encontrar identificación para verificar si el niño que está ingresando ya existe.
         * */
        Boy boyExist = finById(boy.getId());
        if (boyExist != null) {
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Validamos si la cabeza esta vacía, de ser asi le decimos el nuevo niño que sea la cabeza.
         */
        if (this.head == null) {
            this.head = new Node(boy);
        } else {
            /**
             * Si la cabeza no está vacía, creamos un nodo con el niño nuevo, le decimos al niño nuevo que sea la cabeza.
             */
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head = temp;
        }
        /**
         * Aumentamos el contador con el niño nuevo.
         */
        count++;
    }

    /**
     * Método que me permite insertar un niño en una posición dada.
     * @param boy parámetro donde llegan todos los datos del niño.
     * @param position Posición dada en la que se debe adicionar el niño.
     * @throws ListaSeException Excepción que puede generar el método cuando el niño que se está ingresando ya existe.
     */
    public void addByPosition(Boy boy, int position) throws ListaSeException {
        /**
         * Se invoca el método encontrar identificación para verificar si el niño que está ingresando ya existe.
         * */
        Boy boyExist = finById(boy.getId());
        if (boyExist != null) {
            throw new ListaSeException("La identificación ingresada ya existe");
        }

        /**
         * inicialmente validación de la posición, si la posición dada es mayor al número de niños existentes llamamos el método para que adicione el niño al final de la lista.
         */

        if (position > count) {
            this.add(boy);
            return;
        }
        /**
         * si la posición dada es igual al primero llamamos el método para que adicione el niño al inicio de la lista.
         */
        if (position == 1) {
            addToStart(boy);
        } else {
            /**
             * Si la posición es diferente del primero, iniciamos el contador en 1, llamamos un ayudante y le decimos que se ubique en la cabeza.
             * Recorremos la lista mientras el ayudante sea diferente de nulo.
             * Validamos que el contador sea igual a la posición menos 1 para que el ayudante quede posicionado en la posición anterior de donde tengo qe adicionar el niño nuevo.
             */
            int count = 1;
            Node temp = this.head;
            while (temp != null) {
                if (count == position - 1) {
                    break;
                }
                temp = temp.getNext();
                count++;
            }
            //Ayudante va a estar posicionado en la posición anterior
            /**
             * Cuando el ayudante quede posicionado en la posición anterior de donde tengo que agregar el niño, creamos un nodo con el niño nuevo
             * le decimos al nuevo nodo que su siguiente es el siguiente del ayudante.
             * Le decimos al ayudante que si siguiente es el nuevo niño.
             */
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
        }

    }


    /**
     * Método que me invierte los niños de la lista.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void invert() throws ListaSeException {
        /**
         * Validamos que la cabeza tenga datos
         */
        if (this.head != null) {
            /**
             * Si la cabeza tiene datos creamos una lista temporal.(Lista que me permite llamar otros métodos del modelo.)
             */
            ListSE listTemp = new ListSE();
            /**
             * Llamamos un ayudante llamamos temp y le decimos que se ubique en la cabeza o al inicio de la lista.
             * Recorremos la lista de principio a fin
             */
            Node temp = this.head;
            while (temp != null) {
                /**
                 * Damos la indicación para que se adicione al inicio de la lista temporal el dato del niño.
                 */
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            /**
             * Le decimos a la cabeza que su nueva lista es la lista temporal que creamos nueva, te esta manera cuando listemos los niños nos va a mostrar la nueva lista con el cambio.
             */
            this.head = listTemp.getHead();
        }

    }

    /**
     * Método que me cuenta los niños de la lista.
     * @return Retorna el contador con el número de niños que hay en lista.
     */
    public int count() {
        /**
         * Iniciamos el contador en cero.
         * Si la cabeza no esta vacía llamamos un ayudante y lo ubicamos en la cabeza o al inicio de la lista.
         */
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            /**
             * Recorremos la lista hasta llegar al final de la lista
             */
            while (temp != null) {
                /**
                 * Mientras recorremos la lista aumentamos el contador
                 */
                count++;
                temp = temp.getNext();
            }
        }
        /**
         * Retornamos el contador con el número de niños que hay en lista.
         */
        return count;
    }

    /**
     * Método que al llamarlo me lista los niños existentes.
     * @return me retorna los niños enlazados de la lista.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public List<Boy> list() throws ListaSeException {
        /**
         * Si la cabeza no esta vacía es decir hay datos en la lista, llamamos un ayudante y lo ubicamos en la cabeza.
         * Inicializamos una lista temporal de tipo niño (BOY).
         */
        if (this.head != null) {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            /**
             * Recorremos la lista hasta el final mientras el ayudante tenga información.
             */
            while (temp != null) {
                /**
                 * Adicionamos cada niño que encontramos en el recorrido a la lista temporal inicializada anteriormente.
                 */
                list.add(temp.getData());
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista con los niños.
             */
            return list;
        }
        //return null;
        /**
         * Si la lista está vacía enviamos un mensaje al usuario final.
         */
        throw new ListaSeException("No hay datos en la lista");
    }

    /**
     * Método que me invierte la lista, me ubica el primero que tengo inicialmente como último y le que tenía como último como el primero de la lista
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void changeXtremes() throws ListaSeException {
        /**
         * Validamos que la lista tenga información y adicionalmente que su siguiente tenga no este nulo para garantizar que al menos la lista tenga 2 nodos.
         */
        if (this.head != null && this.head.getNext() != null) {
            /**
             * En esta línea lo que hacemos es sacar el niño de la cabeza.
             * Creamos un ayudante y le decimos que se ubique en la cabeza de la lista.
             */
            Boy start = this.head.getData();
            Node temp = head;
            /**
             * Recorremos la lista de principio a fin.
             */
            while (temp.getNext() != null) {
                /**
                 * Mientas el siguiente del ayudante no sea null recorremos cada nulo hasta llegar al último, cuando estemos en el último termina el ciclo para quedar ubicado en el último niño
                 */
                temp = temp.getNext();
            }
            //Temp está ubicado en el último niño
            /**
             * Le decimos al último niño que sea la cabeza de la lista
             * y al primer niño que sacamos de la cabeza lo ubicamos de último
             */
            this.head.setData(temp.getData());
            temp.setData(start);
        } else {
            /**
             * Si la lista está vacía enviamos un mensaje al usuario final.
             */
            throw new ListaSeException("No es posible ejecutar el cambio de extremos");
        }
    }

    /**
     * Método que me filtra los niños según genero dado.
     * @param gender parámetro donde llega el género de los niños solicitado.
     * @return retornamos la lista nueva con el filtro de los niños según su género solicitado.
     */
    public List<Boy> filtGender(String gender) throws ListaSeException {
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Mientras la cabeza no este vacía, llamamos un ayudante y lo ubicamos en la cabeza.
         * Adicionalmente inicializamos una lista temporal de tipo BOY, donde voy a almacenar los niños que cumplen con la condición.
         */
        if (this.head != null) {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            /**
             * Recorremos la lista de principio a fin, hasta llegar al último.
             */
            while (temp != null) {
                /**
                 * Comparamos el niño donde está ubicado el ayudante(temp) con el género solicitado.
                 */
                if (temp.getData().getGender().name().equals(gender)) {
                    /**
                     * Si cumple la condición adicionamos el niño a la lista, de lo contrario le decimos al ayudante que siga con el siguiente nodo.
                     */
                    list.add(temp.getData());

                }
                /**
                 * Le decimos al ayudante que continue hasta llegar al dato que se requiere.
                 */
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista con los niños del género solicitado.
             */
            return list;
        }
        return null;
    }

    /**
     * Método que me permite obtener los niños de un grado de escuela dado (1,2,3,4,5)
     * @param grade parámetro donde llega el grado solicitado.
     * @return Retornamos la lista con los niños que tienen el grado solicitado por el usuario.
     */
    public List<Boy> listBoyByGrade(byte grade) throws  ListaSeException{
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();

        /**
         * Mientras la cabeza no este vacía, llamamos un ayudante y lo ubicamos en la cabeza.
         * Adicionalmente inicializamos una lista temporal de tipo BOY, donde voy a almacenar los niños que cumplen con la condición
         */
        if (this.head != null) {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            /**
             * Recorremos la lista de principio a fin, hasta llegar al último
             */
            while (temp != null) {
                /**
                 * Comparamos el niño donde está ubicado el ayudante(temp) con el grado de escuela solicitado.
                 */
                if (temp.getData().getGrade() == grade){
                    /**
                     * Si cumple la condición adicionamos el niño a la lista, de lo contrario le decimos al ayudante que siga con el siguiente nodo.
                     */
                    list.add(temp.getData());
                }
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista con los niños del género solicitado.
             */
            return list;
        }
        return null;
    }

    /**
     * Método que, dado un género y una edad, me deje al inicio de la lista los niños del género dado y con la edad menor o igual a la entregada.
     * @param gender parámetro donde llega el género del niño dado.
     * @param age parámetro donde llega el género del niño dada.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void invertBoyByGenderAge(String gender, byte age) throws ListaSeException {
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Llamamos un ayudante y lo ubicamos en la cabeza.
         * Adicionalmente inicializamos una lista temporal, donde voy a almacenar los niños que cumplen con la condición.
         */
        Node temp = this.head;
        ListSE listTemp = new ListSE();
        /**
         * Recorremos la lista de principio a fin, hasta llegar al último
         */
        while (temp != null) {
            /**
             * Comparamos el niño donde está ubicado el ayudante(temp) con el género solicitado y adicionalmente que sea meno o igual a la edad dada.
             */
            if (temp.getData().getGender().name().equals(gender) && temp.getData().getAge() <= age) {
                /**
                 * Si el género cumple y es menor o igual a la edad que nos piden adicionamos el niño al inicio de la lista
                 */
                listTemp.addToStart(temp.getData());
            } else {
                /**
                 * de lo contrario adicionamos ese niño al final de la lista.
                 */
                listTemp.add(temp.getData());
            }
            temp = temp.getNext();

        }
        /**
         * Reemplazamos la lista por la nueva.
         */
        this.head = listTemp.getHead();
    }

    /**
     * Método que me invierte los niños por género, me deja los niños el género dado al inicio de la lista
     * @param gender parámetro donde llega el género del niño dado.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void invertBoyByGender(String gender) throws ListaSeException {
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Llamamos un ayudante y lo ubicamos en la cabeza.
         * Adicionalmente inicializamos una lista temporal, donde voy a almacenar los niños que cumplen con la condición.
         */
        Node temp = this.head;
        ListSE listTemp = new ListSE();
        /**
         * Recorremos la lista de principio a fin, hasta llegar al último
         */
        while (temp != null) {
            /**
             * Comparamos el niño donde está ubicado el ayudante(temp) con el género dado.
             */
            if (temp.getData().getGender().name().equals(gender)){
                /**
                 * Si el niño cumple con la condición lo adicionamos al inicio dela lista.
                 */
                listTemp.addToStart(temp.getData());
            } else {
                /**
                 * de lo contrario adicionamos el niño al final de la lista.
                 */
                listTemp.add(temp.getData());
            }
            temp = temp.getNext();
        }
        /**
         * Reemplazamos la lista por la nueva.
         */
        this.head = listTemp.getHead();
    }

    /**
     * Método que dada una edad y un municipio me permita obtener el listado de los niños pertenecientes a ese municipio y de edad menor o igual a la dada.
     * @param age parámetro donde llega la edad del niño que se requiere listar es menor o igual.
     * @param location parámetro donde llega la localidad del niño que se requiere listar.
     * @return Retorna la lista con los niños iguales al municipio solicitado y menor o igual a la edad dada.
     */
   public List<Boy> listByLocationAge(byte age, String location) throws ListaSeException{
       /**
        * Llamamos el método que me válida si la lista se encuentra vacía.
        */
       validateListEmty();
        if (this.head != null) {
            /**
             * Mientras la cabeza no esté vacía, Llamamos un ayudante y lo ubicamos en la cabeza.
             * Adicionalmente inicializamos una lista temporal, donde voy a almacenar los niños que cumplen con la condición.
             */
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            /**
             * Recorremos la lista de principio a fin, hasta llegar al último
             */
            while (temp != null) {
                /**
                 * Inicialmente realiza el filtro donde comparamos si la edad del niño que tiene el ayudante es menor o igual a la edad dada, pase.
                 */
                if (temp.getData().getAge()<=age) {
                    /**
                     * Si cumple con la condición inicial, realizamos una segunda condición donde comparamos la localidad del que tiene el ayudante con la dada.
                     * Si se cumple adicionamos el niño a la lista temporal.
                     */
                    if (temp.getData().getLocation().getDescription().equals(location)){
                        list.add(temp.getData());
                    }
                    /**
                     * Si no se cumple alguna de las condiciones anteriores le decimos al temporal que continue con el siguiente nodo.
                     */
                }
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista con el listado de niños que cumplen la condición solicitada
             */
            return list;
        }
        return null;
    }



    /**
     * Método que me elimina el niño por identificación
     * @param id parámetro donde llega la identificación del niño que se requiere eliminar.
     * @throws ListaSeException Excepción que puede generar el método cuando el niño no existe o la lista está vacía.
     */
    public void delete(String id) throws ListaSeException {

        /**
         * Primero validamos si la cabeza tiene información, si la cabeza es el niño que estamos buscando, es igual a la identificación que nos piden, eliminamos.
         */
        if (this.head != null) {
            if (this.head.getData().getId().equals(id)) {
                /**
                 * Le decimos ala cabeza que sea el que sigue.
                 */
                this.head = this.head.getNext();
            } else {
                /**
                 * Si el que tenemos que eliminar no es la cabeza creamos un ayudante(temp) y le decimos que se ubique en la cabeza o al inicio de la lista.
                 */
                Node temp = this.head;
                /**
                 * Recorremos la lista de principio a fin, hasta llegar al último
                 */
                while (temp != null) {
                    /**
                     * Validamos que el siguiente del niño que tiene el ayudante no sea nulo y que el niño sea la identificación que estamos buscando.
                     * Si es asi rompemos el ciclo para que el ayudante quede ubicado en niño o nodo anterior del que tengo que eliminar.
                     */
                    if (temp.getNext() != null && temp.getNext().getData().getId().equals(id)) {
                        break;
                    }
                    /**
                     * Si no es el niño que estamos buscando le decimos al ayudante que continue con el siguiente niño.
                     */
                    temp = temp.getNext();

                }
                /**
                 * en esta parte el temp o ayudante va a estar ubicado en el anterior al que debo eliminar o va a ser null
                 */
                if (temp != null) {
                    /**
                     * Si el ayudante no está vacío quiere decir que estamos en el nodo anterior del que tenemos que eliminar.
                     * Le decimos al ayudante que tome el siguiente de su siguiente.
                     */
                    temp.setNext(temp.getNext().getNext());
                    /**
                     * Restamos el niño del contador.
                     */
                    count--;
                } else {
                    /**
                     * Si llegamos al final de la lista y no encontramos el niño enviamos un mensaje al usuario final indicando que el niño no existe
                     */
                    throw new ListaSeException("La identificación " + id + " No existe");
                }
            }
        }
    }

    /**
     * Método que me permite eliminar un niño dada una posición.
     * @param position parámetro donde llega la posición del niño que se requiere eliminar.
     * @throws ListaSeException Excepción que puede generar el método cuando la posición no existe o la lista está vacía.
     */
    public void deleteByPosition(int position) throws ListaSeException {
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();

        //Validación de la posición
        /**
         * Validamos si la posición dada es mayor al # de niños que existen en la lista.
         * Si es mayor enviamos un mensaje al usuario final que la posición es válida.
         */
        if (position > count) {
            throw new ListaSeException("La posición " + position + " No es valida");
        }
        /**
         * Si la posición es igual a 1 quiere decir que debemos eliminar el primero dela lista, en ese caso le decimos a la cabeza que sea el que sigue en la lista.
         */
        if (position == 1) {
            this.head = this.head.getNext();
        } else {
            /**
             * Si el que tenemos qe eliminar no es el primero en la lista iniciamos un contador en 1.
             * Llamamos un ayudante y le decimos que se ubique en la cabeza o al inicio de la lista.
             */
            int count = 1;
            Node temp = this.head;
            /**
             * Recorremos la lista de principio a fina.
             */
            while (temp != null) {
                /**
                 * Si el contador es igual a la posición que estamos buscando menos - 1 rompemos el ciclo para quedar ubicamos en el niño o no anterior al que tenemos que eliminar.
                 */
                if (count == position - 1) {
                    break;
                }
                /**
                 * Si no es la posición con el niño que tenemos que eliminar le decimos al ayudante que continue con el siguiente nodo y aumente el contador de ese niño.
                 */
                temp = temp.getNext();
                count++;
            }
            /**
             * En esta parte el ayudante va a estar posicionado en la posición anterior del nodo que debemos eliminar
             * le decimos al ayudante que tome el siguiente de su siguiente.
             */
            temp.setNext(temp.getNext().getNext());
            /**
             * Restamos en el contador el niño en la posición que se elimina.
             */
            count--;
        }

    }

    /**
     * Método que me busca en la lista simplemente enlazada un niño a partir de una identificación.
     * Si no encuentra el niño retorna vacío.
     * @param id Cédula, TI, CE, Sisben, que identifica el niño que voy a buscar.
     * @return retorna el niño que encontramos con todos los datos.
     */
    public Boy finById(String id){

        /**
         * Como no nos podemos mover de la cabeza porque se pierde la lista de los niños.
         * Llamamos un ayudante temporal y le decimos que se ubique en la cabeza o al inicio.
         */
        Node temp = this.head;
        /**
         * Creamos un ciclo para recorrer la lista simplemente enlazada de principio a fin.
         * Llegamos al final cuando el ayudante quede ubicado en vacío (null).
         */
        while (temp != null) {
            /**
             * Preguntamos si el niño en el cual estoy ubicado es igual a la identificación
             */
            if (temp.getData().getId().equals(id)) {
                return temp.getData();
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * Método que válida si hay datos en la lista
     * @throws ListaSeException Excepción que genera el método cuando la lista está vacía.
     */
    public void validateListEmty() throws ListaSeException {
        if (this.head == null) {
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños que hay de esa ciudad.
     * @param code parámetro que recibe el codio de la ciudad que se requiere contar.
     * @return retorna la cantidad que se tiene de la ciudad solicitada.
     */

    public int getCountBoysByLocation(String code) throws ListaSeException {
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Creamos un nodo nuevo y lo ubicamos en el inicio de la lista e iniciamos un contador en cero.
         */
        Node temp = this.getHead();
        int count = 0;
        /**
         * Recorremos la lista para contar cada los niños de cada localidad-
         */
        while (temp != null) {
            if (temp.getData().getLocation().getCode().equals(code)) {
                /**
                 * aumentamos el contador de cada localidad (código) por cada niño.
                 */
                count++;
            }
            temp = temp.getNext();
        }
        /**
         * Retornamos el contador para utilizarlo en algún otro método.
         */
        return count;
    }

    /**
     * Método que me cuenta los niños que se tiene en la lista por género.
     * @param gender parámetro que recibe la descripción del género que se desea contar.
     * @return retorna la cantidad que se tiene de niños del género solicitado.
     */
    public int getCountBoysByGender(String gender)throws ListaSeException {
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Creamos un nodo nuevo y lo ubicamos en el inicio de la lista e iniciamos un contador en cero.
         */
        Node temp = this.getHead();
        int count = 0;
        /**
         * Recorremos la lista para contar cada los niños de cada localidad-
         */
        while (temp != null) {
            if (temp.getData().getGender().name().equals(gender)) {
                /**
                 * aumentamos el contador de cada género por cada niño.
                 */
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * Método que me lista los niños por género.
     * @param gender parámetro que recibe el género que se requiere listar.
     * @return retorna la lista con el género solicitado.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public ListSE getListSEBoyGender(String gender) throws ListaSeException {
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Si la lista no está vacía llamamos un ayudante y lo ubicamos en la cabeza o inicio de la lista.
         * Creamos una lista temporal donde vamos a adicionar los niños que cumplan con la condición solicitada.
         */
        Node temp = this.head;
        ListSE listTemp = new ListSE();
        /**
         * Recorremos la lista de principio a fin.
         */
        while (temp != null) {
            /**
             * Validamos que el niño que tiene el ayudante sea igual al género solicitado.
             */
            if (temp.getData().getGender().name().equals(gender)) {
                /**
                 * Si cumple la condición anterior adicionamos el niño al final de lista temporal.
                 */
                listTemp.add(temp.getData());
            }
            /**
             * Si no cumple con la condición anterior le decimos la ayudante que continue con el siguiente niño hasta legar al final de la lista.
             */
            temp = temp.getNext();
        }
        /**
         * Retornamos la lista temporal con los datos solicitados.
         */
        return listTemp;
    }

    /**
     * Método que me intercala los niños según su género en la lista.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void variantBoys() throws ListaSeException{
        /**
         * Llamamos el método que me válida si la lista se encuentra vacía.
         */
        validateListEmty();
        /**
         * Inicialmente separamos los niños y las niñas en 2 listas diferentes.
         * Inicializamos otras 2 listas temporales que nos va a servir para diferenciar cuál de las listas tiene más niños que la otra.
         */
       ListSE kids = this.getListSEBoyGender("MASCULINO");
       ListSE girls = this.getListSEBoyGender("FEMENINO");
       ListSE minList = null;
       ListSE maxlist= null;
        /**
         * Validamos si la cantidad de niños en la lista de los niños es mayor a la lista de las niñas.
         * Si es asi le indicamos la lista que tiene menos niños es la lista de las niñas.
         * Y la que tiene más niños es la lista de los niños.
         */
       if (kids.getCount() > girls.getCount()){
           minList = girls;
           maxlist= kids;
       }
       /**
        * De lo contrario le decimos que la lista menor es la de los niños y la mayor la de las niñas
        */
       else {
           minList = kids;
           maxlist= girls;
       }
        /**
         * Después de tener la lista menor y mayor llamamos un ayudante que se ubique en la cabeza de lista menor.
         *  Iniciamos un ayudante tipo número en 2 que nos va a servir para agregar la los niños de la lista menor en la mayor en las posiciones pares.
         */
       Node temp= minList.getHead();
       int pos=2;
       while (temp!=null){
           /**
            * Recorremos la lista y adicionamos con el ayudante con la lista menor en la lista mayor en las posiciones pares.
            */
           maxlist.addByPosition(temp.getData(), pos);
           pos = pos +2;
           temp = temp.getNext();
       }
        /**
         * Reemplazamos la nueva lista en la lista principal del programa.
         */
       this.head = maxlist.getHead();
    }

    /**
     * Método que me permite retirar a todos los niños de un género de la lista
     * @param gender parámetro que recibe el género que se requiere retirar de la lista.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void removeBoyByGender(String gender) throws ListaSeException{
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Si la lista tiene datos, llamamos un ayudante y le decimos que se ubique en la cabeza.
         * Inicializamos dos listas temporales donde voy a almacenar los datos del niño que me piden retirar y los datos del niño que no hay que retirar.
         */
        Node temp = this.head;
        ListSE listTemp = new ListSE();
        ListSE listTemp2 = new ListSE();
        /**
         * Recorremos la lista de principio a fin.
         */
        while (temp != null) {
            /**
             * Validamos si el niño que tiene el ayudante es igual al género que nos piden retirar de ls lista.
             * Si es asi adicionamos ese niño en la lista una de las listas temporal que inicialice (listTemp).
             */
            if (temp.getData().getGender().name().equals(gender)) {
                listTemp.add(temp.getData());
                /**
                 * Si no cumple con la condición inicial adiciono ese niño en la otra lista temporal (listTemp2)
                  */
            }else{
                listTemp2.add(temp.getData());
            }
            temp = temp.getNext();
        }
        /**
         * Finalmente reemplazo la lista principal con la lista temporal que no tiene los niños que no se tenían que retirar.
         */
        this.head = listTemp2.getHead();
    }

    /**
     * Método que me permita eliminar los niños con una edad mayor a la suministrada.
     * @param age parámetro donde llega la edad que se requiere para eliminar los niños mayores a la edad dada.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void deleteMayAge(byte age) throws ListaSeException{
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Si la lista tiene datos, llamamos un ayudante y le decimos que se ubique en la cabeza.
         * Inicializamos dos listas temporales donde voy a almacenar los datos del niño que me piden eliminar y otra lista para almacenar los datos del niño que no hay que eliminar.
         */
        Node temp = this.head;
        ListSE listTempmay = new ListSE();
        ListSE listTempmen = new ListSE();
        /**
         * Recorremos la lista de principio a fin.
         */
        while (temp != null) {
            /**
             * Validamos si el niño que tiene el ayudante es mayor a la edad dada.
             * Si es asi adiciono ese niño en la lista listTempmay.
             */
            if (temp.getData().getAge() >age) {
                listTempmay.add(temp.getData());
            }else{
                /**
                 * Si no cumple con la condición anterior adiciono ese niño en la lista temporal listTempmen.
                 */
                listTempmen.add(temp.getData());
            }
            temp = temp.getNext();
        }
        /**
         * Como me piden eliminar los niños mayores a una edad dada, reemplazo la lista principal por la lista donde tengo los niños menores.
         */
        this.head = listTempmen.getHead();
    }

    /**
     * Método que me retorna los datos del niño y la niña mayores.
     * @return Retorno la lista con la niña y el niño mayor.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
   public ListSE boysGirlsGreater() throws ListaSeException {
       /**
        * Llamamos inicialmente el método que me valida si la lista está vacía
        */
       validateListEmty();

       /**
        * Si la lista tiene datos, llamamos el método que me lista por género, declaro un ayudante que me recibe la lista de las niñas y otro que me recibe la lista de los niños-
        */
       ListSE kids = this.getListSEBoyGender("MASCULINO");
       ListSE girl = this.getListSEBoyGender("FEMENINO");
       /**
        * Inicializa una lista temporal donde voy a almacenar la niña y el niño mayor para finalmente mostrarlos al usuario final.
        */
       ListSE listTempmay = new ListSE();

       /**
        * llamamos 3 ayudantes
        * 1. Ayudante que se ubica en la cabeza inicio de la lista de los niños.
        * 2. Ayudante auxiliar que me ayuda a comparar respecto al ayudante principal los datos.
        * 3. Ayudante donde voy a almacenar el niño mayor y me sirve para una segunda comparación.
        */
       Node tempkids = kids.head;
       Node auxkids = tempkids;
       Node mayorkids = kids.head;

       /**
        * llamamos 3 ayudantes
        * 1. Ayudante que se ubica en la cabeza inicio de la lista de los niñas.
        * 2. Ayudante auxiliar que me ayuda a comparar respecto al ayudante principal los datos.
        * 3. Ayudante donde voy a almacenar el niña mayor y me sirve para una segunda comparación.
        */
       Node tempgirl = girl.head;
       Node auxgirl = tempgirl;
       Node mayorgirl = girl.head;

       /**
        * Realiza un primer recorrido en la lista de los niños.
        */
       while (tempkids != null) {
           /**
            * Comparo la edad del niño que tiene el ayudante 1 con la edad que tiene el niño del ayudante 2, adicionalmente comparo la edad que tiene el niño en el ayudante 3.
            * Si cumple la condición le digo a ese nuevo niño que sea el mayor de la lista.
            */
           if (tempkids.getData().getAge() > auxkids.getData().getAge() && tempkids.getData().getAge() >= mayorkids.getData().getAge()) {
               /**
                * Le digo al ayudante 3 donde almaceno el mayor que su mayor es el ayudante 1.
                */
               mayorkids = tempkids;
           }
           /**
            * Aquí le digo al auxiliar que es igual al ayudante 1 para poder comparar con el siguiente nodo
            */
           auxkids=tempkids;
           /**
            * Le digo al ayudante 1 que continue con el siguiente niño.
            */
           tempkids = tempkids.getNext();

       }
       /**
        * Cuando termina el ciclo anterior continuo recorriendo la lista, pero de las niñas y aplico el mismo procedimiento.
        */
       while (tempgirl != null) {
           if (tempgirl.getData().getAge() > auxgirl.getData().getAge() && tempgirl.getData().getAge() >= mayorgirl.getData().getAge()) {
               mayorgirl = tempgirl;
           }
           auxgirl=tempgirl;
           tempgirl = tempgirl.getNext();

       }
       /**
        * Finalmente adiciono el niño y la niña mayor capturada en la lista temporal.
        */
       listTempmay.add(mayorkids.getData());
       listTempmay.add(mayorgirl.getData());
       /**
        * Retorno la lista con el niño y la niña mayor
        */
       return listTempmay;
        }

    /**
     * Método que me indica en que posición se encuentra el niño según si identificación
     * @param id Identificación que se pide para indicar en que posición se encuentra.
     * @return retornamos la posición del niño.
     */

    public int positionBoy(String id)throws ListaSeException {
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Llamamos un ayudante y lo ubicamos en el inicio de la lista.
         */
        Node temp = this.head;
        /**
         * Iniciamos un contador en cero.
         */
        int cont = 0;
        /**
         * Recorremos la lista hasta el final.
         */

        while (temp != null) {
            /**
             * Validamos que la identificación de los niños que tiene el temporal sea la misma que nos dan.
             */
            if (temp.getData().getId().equals(id)) {
                /**
                 * Retornamos el contador con el número de posición en la que se encuentra el niño que nos poden.
                 */
                return cont;
            }
            /**
             * Le decimos al ayudante que continue con el siguiente y aumente el contador.
             */
            temp = temp.getNext();
            cont++;

        }

        return -1;
    }

    /**
     * Método qye me permite decirle al niño que adelante n posiciones.
     * @param id identificación que nos dan para que adelante las posiciones.
     * @param position número de posiciones que se requiere que el niño adelante.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void advanceByPosition(String id, int position) throws ListaSeException {
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Llamamos un ayudante y le decimos que se ubique en el inicio de la lista.
         */
        Node temp=this.head;
        /**
         * Iniciamos un ayudante que nos va a capturar la posición en la que se encuentra el niño con el método que tenemos que nos dice la posición según la identificación del niño.
         * Le sumamos a la posición 1, porque la lista inicia en la posición cero y necesito que la cabeza inicie en la posición 1
         */
        int pos=(positionBoy(id)+1);
        /**
         * creo otro ayudante que me reste la posición en la que se encuentra el niño menos la posición que dada para que el niño adelante posiciones en la lista.
         */
        int aux= pos-position;
        /**
         * Recorremos la lista de principio a fin.
         */
        while (temp != null) {
            /**
             * Comparamos que el ayudante sea igual al niño dado para que adelante posiciones en la lista.
             * Adicionalmente comparamos si la posición dada es igual a cero, si es asi lanzamos un mensaje al usuario que la posición no es válida.
             */
            if (temp.getData().getId().equals(id)&& position == 0) {
                throw new ListaSeException("La posición no es valida");

            }
            /**
             * Si no se cumple la condición anterior realizamos otra condición.
             * Comparamos que el ayudante sea igual al niño dado para que adelante posiciones en la lista.
             * Si el aux (que es la posición del niño menos la posición que necesitamos que adelante) es menor a cero quiere decir que la posición que nos dieron es mayor al número de niños que tengo en la lista, en ese caso elimino el niño de la lista en la posición que se encuentra.
             * Después de eliminar, como la posición supera el número de niños de la lista lo adiciono al inicio de la lista para que me quede de primero.
             */
            if (temp.getData().getId().equals(id)&& aux< 0) {
                deleteByPosition(pos);
                addToStart(temp.getData());;
                break;
            }
            /**
             * Si no se cumple la condición anterior realizamos otra condición
             * Comparamos que el ayudante sea igual al niño dado para que adelante posiciones en la lista.
             * Si el aux (que es la posición del niño menos la posición que necesitamos que adelante) es igual a cero, elimino el niño de la lista en la posición que se encuentra.
             * Después de eliminar lo adiciono al inicio de la lista para que me quede de primero en la lista
             */
            if (temp.getData().getId().equals(id) && aux==0) {
                deleteByPosition(pos);
                addToStart(temp.getData());
                break;
            }
            /**
             * Si no se cumple la condición anterior realizamos otra condición.
             * Comparamos que el ayudante sea igual al niño dado para que adelante posiciones en la lista, si no se cumple las condiciones anteriores quiere decir que tengo que mover los niños entre la mitad de la lista.
             * Primero elimino el niño en la posición que se encuentra.
             * Luego lo adiciono en la posición que calculo con el ayudante aux (que es la posición del niño menos la posición dada que necesitamos que adelante)
             */
            if (temp.getData().getId().equals(id)) {
                deleteByPosition(pos);
                addByPosition(temp.getData(), aux);
                break;
            }
            /**
             * Le digo al temporal que continue hasta encontrar el niño dado.
             */
            temp=temp.getNext();
        }


    }

    /**
     * Método qye me permite decirle al niño que pierda n posiciones.
     * @param id identificación que nos dan para que adelante las posiciones.
     * @param position número de posiciones que se requiere que el niño adelante.
     * @throws ListaSeException Excepción que puede generar el método cuando la lista está vacía.
     */
    public void loseByPosition(String id, int position) throws ListaSeException {
        /**
         * Llamamos inicialmente el método que me valida si la lista está vacía
         */
        validateListEmty();
        /**
         * Llamamos un ayudante y le decimos que se ubique en el inicio de la lista.
         */
        Node temp=this.head;
        /**
         * Iniciamos un ayudante que nos va a capturar la posición en la que se encuentra el niño con el método que tenemos que nos dice la posición según la identificación del niño.
         * Le sumamos a la posición 1, porque la lista inicia en la posición cero y necesito que la cabeza inicie en la posición 1
         */
        int pos=(positionBoy(id)+1);
        /**
         * creo otro ayudante que me reste la posición en la que se encuentra el niño menos la posición que dada para que el niño pierda posiciones en la lista.
         */
        int aux= pos+position;
        /**
         * Recorremos la lista de principio a fin.
         */
        while (temp != null) {
            /**
             * Comparamos que el ayudante sea igual al niño dado para que pierda posiciones en la lista.
             * Adicionalmente comparamos si la posición dada es igual a cero, si es asi lanzamos un mensaje al usuario que la posición no es válida.
             */
            if (temp.getData().getId().equals(id)&& position == 0) {
                throw new ListaSeException("La posición no es valida");
            }
            /**
             * Si no se cumple la condición anterior realizamos otra condición.
             * Comparamos que el ayudante sea igual al niño dado para que pierda posiciones en la lista.
             * Si el aux (que es la posición del niño menos la posición que necesitamos que adelante) es mayor al contador de niños, quiere decir que la posición que nos dieron es mayor al número de niños que tengo en la lista, en ese caso elimino el niño de la lista en la posición que se encuentra.
             * Después de eliminar, como la posición supera el número de niños de la lista lo adiciono al final de la lista para que me quede de último.
             */
        if (temp.getData().getId().equals(id)&& aux> count) {
            deleteByPosition(pos);
            add(temp.getData());
           break;
        }
            /**
             * Si no se cumple la condición anterior realizamos otra condición
             * Comparamos que el ayudante sea igual al niño dado para que pierda posiciones en la lista.
             * Si el aux (que es la posición del niño menos la posición que necesitamos que pierda) es igual a 1, elimino el niño de la lista en la posición que se encuentra.
             * Después de eliminar lo adiciono al inicio de la lista para que me quede de primero en la lista
             */
        if (temp.getData().getId().equals(id) && aux == 1) {
            deleteByPosition(pos);
            addToStart(temp.getData());
            break;
        }
            /**
             * Si no se cumple la condición anterior realizamos otra condición.
             * Comparamos que el ayudante sea igual al niño dado para que pierda posiciones en la lista, si no se cumple las condiciones anteriores quiere decir que tengo que mover los niños entre la mitad de la lista.
             * Primero elimino el niño en la posición que se encuentra.
             * Luego lo adiciono en la posición que calculo con el ayudante aux (que es la posición del niño menos la posición dada y que necesitamos que pierda)
             */
            if (temp.getData().getId().equals(id)) {
                deleteByPosition(pos);
                addByPosition(temp.getData(), aux);
                break;
            }
            temp=temp.getNext();
        }
        /**
         * Le digo al temporal que continue hasta encontrar el niño dado.
         */

        }

}




