package com.umanizales.list_se.service;


import com.umanizales.list_se.controller.dto.ResponseDOT;
import com.umanizales.list_se.exception.ListaDeException;
import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.*;
import com.umanizales.list_se.model.listDe.ListDE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *Clase que se encarga de representar los datos que contiene mi modelo, me permite indicar al programa como quiero mostrar la información al usuario final.
 */

@Service
public class ListDeServ {
    private ListDE listBoys;
    private List<Location> locations;
    private List<Gender> gender;
    
    public ListDeServ(){
        listBoys = new ListDE();
        initializalicationDe();
        inicializagenderDe();
    }

    public void initializalicationDe(){
        locations = new ArrayList<>();
        locations.add(new Location("16917001","Manizales"));
        locations.add(new Location("16917002","Chinchina"));
        locations.add(new Location("16917003","La dorada"));
    }

    public void inicializagenderDe(){
        gender = new ArrayList<>();
        gender.add(Gender.MASCULINO);
        gender.add(Gender.FEMENINO);
    }

    public boolean validatelocationDe(Location location){
        for (Location loc:locations){
            if (loc.getCode().equals(location.getCode()) && loc.getDescription().equals(location.getDescription())){
                return  true;
            }
        }

        return false;
    }

   public ResponseEntity<ResponseDOT> addBoyDe(Boy boy)throws ListaDeException {
        if (!validatelocationDe(boy.getLocation())){
            throw new ListaDeException("La ubicación ingresada no es valida");
        }
        if (listBoys.addBoyDe(boy)){
            return new ResponseEntity<>(new ResponseDOT("Niño adicionado con exito",true,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDOT("Niño ya existe",false,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT>addBoyToStartDe(Boy boy)throws ListaDeException{
        listBoys.addToStartDe(boy);
        return new ResponseEntity<>(new ResponseDOT("Niño adicionado con exito",true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT>addByPositionDe(Boy boy, int position)throws ListaDeException{
        listBoys.addByPositionDe(boy,position);
        return new ResponseEntity<>(new ResponseDOT("Niño adcionado",true,null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> listBoysDe() throws ListaDeException{
        if (listBoys.getHeadDe()==null){
            throw new ListaDeException("No hay datos en la lista");
        }
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.listDe(),null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> deleteBoyDe(String id)throws ListaDeException{
        listBoys.deleteBoyDe(id);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado",true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> listBoysFreesDe() throws ListaDeException {
        return new ResponseEntity<>(
                new ResponseDOT("Satisfactorio", listBoys.listDe(),null),
                HttpStatus.OK);

    }
    public ResponseEntity<ResponseDOT> invertLisDe() throws ListaDeException {
        listBoys.invertDe();
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.listDe(),null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> getCountDe()throws ListaDeException{
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.getCountDe(),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> countDe()throws ListaDeException{
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.count(),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> chageXtremeDe() throws ListaDeException{
        listBoys.changeXtremesDe();
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> filtGenderDe(String gender) throws ListaDeException{
        listBoys.filtGenderDe(gender);
        return  new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.filtGenderDe(gender),null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> listBoyByGradeDe(byte grade) throws ListaDeException{
        listBoys.listBoyByGradeDe(grade);
        return  new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.listBoyByGradeDe(grade),null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> listByLocationAgeDe(byte age, String location) throws ListaDeException{
        listBoys.listByLocationAgeDe(age,location);
        return  new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.listByLocationAgeDe(age,location),null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> deleteMayAgeDe(byte age)throws ListaDeException{
        listBoys.deleteMayAgeDe(age);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> removeBoyByGenderDe(String gender)throws ListaDeException{
        listBoys.removeBoyByGenderDe(gender);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> deleteByPostionDe(int position)throws ListaDeException{
        listBoys.deleteByPositionDe(position);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> variantListDe() throws ListaDeException{
        listBoys.variantBoysDe();
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.listDe(),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> getBoysByLocationDe(){
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for (Location loc: locations){
            /** capturamos en el contador lo que nos diga la lista de niños por location por cada código de localidad*/
            int count= listBoys.getCountBoysByLocationDe(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc, count));
        }
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",boysByLocations,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> getBoysByGenderDe(){
        List<BoysByGender> boysByGender = new ArrayList<>();
        for (Gender gen: gender){
            int count= listBoys.getCountBoysByGenderDe(gen.name());
            boysByGender.add(new BoysByGender(gen,count));
        }
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",boysByGender,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> invertBoyByGenderAgeDe(String gender, byte age) throws ListaDeException{
        listBoys.invertBoyByGenderAgeDe(gender, age);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> invertBoyByGenderDe(String gender) throws ListaDeException{
        listBoys.invertBoyByGenderDe(gender);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> boysGirlsGreaterDe() throws ListaDeException {

        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.boysGirlsGreaterDe().listDe(), null), HttpStatus.OK);

    }
    public ResponseEntity<ResponseDOT>advanceByPositionDe(String id, int position) throws ListaDeException {
        listBoys.advanceByPositionDe(id,position);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT>loseByPositionDe(String id, int position) throws ListaDeException {
        listBoys.loseByPositionDe(id,position);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT>  getOrphansByGradesByLocation() throws ListaDeException {
        /**
         * Por último creamos una lista para tener los grados por localidad y enviar la respuesta.
         */
        List<GradesByLocationDTO> gradesByLocationDTOS = new ArrayList<>();
        /**
         *  Recorremos todas las locaciones llamando el método, y parado en cada location del método de la lista.
         */
        for (Location loc : locations) {
            /**
             * Agregamos a los grados cada location encontrada.
             */
            gradesByLocationDTOS.add(listBoys.getGradesByLocationDTO(loc));
        }
        /**
         * Retornamos la respuesta con el método final que ya contiene la información de los 3 métodos para informe completo.
         */
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", gradesByLocationDTOS, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> getOrderBoyAndGirl()throws ListaDeException{
        listBoys.getOrderBoyAndGirl();
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoysFreesDe(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> getBoysByLocationByGenderDTO() throws ListaDeException {
        /**
         * Por último creamos una lista para tener los grados por localidad y enviar la respuesta.
         */
        List<BoysByLocationByGenderDTO> boysByLocationByGenderDTO = new ArrayList<>();
        /**
         *  Recorremos todas las locaciones llamando el método, y parado en cada location del método de la lista.
         */
        for (Location loc : locations) {
            /**
             * Agregamos a los grados cada location encontrada.
             */
            boysByLocationByGenderDTO.add(listBoys.getBoysByLocationByGenderDTO(loc));
        }
        /**
         * Retornamos la respuesta con el método final que ya contiene la información de los 3 métodos para informe completo.
         */
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", boysByLocationByGenderDTO, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> getLocationOrderDe() throws ListaDeException {
        {
            /**
             * Creamos una lista temporal
             */
            ListDE listTemp = new ListDE();
            /**
             * Realizamos un ciclo que nos recorra todas las localizaciones
             */
            for (Location loc: locations)
            {
                /**
                 * Se crea una lista en donde se van a listar en orden las localizaciones
                 */
                ListDE getloc = this.listBoys.getLocationOrderDe(loc);
                /**
                 * Luego se realiza una condición donde indicamos que si la cabeza de ls lista tiene datos, nos agregue la información del nodo a la lista temporal
                 */
                if(getloc.getHeadDe()!=null){
                    listTemp.addNodenew(getloc.getHeadDe());
                }
            }
            /**
             * retornamos la lista con las localizaciones ordenadas.
             */
            return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listTemp.listDe(), null), HttpStatus.OK);
    }
    }

}
