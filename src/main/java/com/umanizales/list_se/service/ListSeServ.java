package com.umanizales.list_se.service;


import com.umanizales.list_se.controller.dto.ResponseDOT;
import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.*;
import com.umanizales.list_se.model.listSe.ListSE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *Clase que se encarga de representar los datos que contiene mi modelo, me permite indicar al programa como quiero mostrar la información al usuario final.
 */

@Service
public class ListSeServ {
    private ListSE listBoys;
    private List<Location> locations;
    private List<Gender> gender;


     public ListSeServ(){
         listBoys= new ListSE();
         initializalocation();
         inicializagender();
     }

     public void initializalocation(){
         locations = new ArrayList<>();
         locations.add(new Location("16917001","Manizales"));
         locations.add(new  Location("16917002","Chinchina"));
     }
     public void inicializagender(){
         gender = new ArrayList<>();
         gender.add(Gender.FEMENINO);
         gender.add(Gender.MASCULINO);
     }


     public boolean validatelocation (Location location){
         for (Location loc: locations) {
             if (loc.getCode().equals(location.getCode()) && loc.getDescription().equals(location.getDescription())) {
                 return true;
             }
         }
             return false;
    }
     public ResponseEntity<ResponseDOT> addBoy(Boy boy) throws ListaSeException{
         if (!validatelocation(boy.getLocation())){
             throw new ListaSeException("La ubicación ingresada no es valida");
         }
         if(listBoys.add(boy)) {
             return new ResponseEntity<>(new ResponseDOT("Niño adicionado", true, null), HttpStatus.OK);
         }
         return new ResponseEntity<>(new ResponseDOT("Niño ya existe", true, null), HttpStatus.OK);
     }
    public ResponseEntity<ResponseDOT> addBoyToStart(Boy boy) throws ListaSeException{
        listBoys.addToStart(boy);
        return new ResponseEntity<>(new ResponseDOT("Niño adicionado",false,null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> listBoys() throws ListaSeException
    {
        if (listBoys.getHead()==null){
            throw new ListaSeException("No hay datos en la lista");
        }
        return new ResponseEntity<>(
                new ResponseDOT("Satisfactorio", listBoys.getHead(),null),
                HttpStatus.OK);

    }
    public ResponseEntity<ResponseDOT> listBoysFrees() throws ListaSeException
    {
        return new ResponseEntity<>(
                new ResponseDOT("Satisfactorio", listBoys.list(),null),
                HttpStatus.OK);

    }
     public ResponseEntity<ResponseDOT> invertLis() throws ListaSeException{
         listBoys.invert();
         return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.getHead(),null),HttpStatus.OK);
     }
    public ResponseEntity<ResponseDOT> getCount(){
         return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.getCount(),null),HttpStatus.OK);
}
    public ResponseEntity<ResponseDOT> count(){
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.count(),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> chageXtreme() throws ListaSeException{
         listBoys.changeXtremes();
         return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null), HttpStatus.OK);
    }

      public ResponseEntity<ResponseDOT> filtGender(String gender) throws ListaSeException{
         return  new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.filtGender(gender),null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> listBoyByGrade(byte grade)throws  ListaSeException {
        return  new ResponseEntity<>(new ResponseDOT("Satisfactorio",listBoys.listBoyByGrade(grade),null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> listByLocationAge(byte age, String location) throws ListaSeException{

         return  new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.listByLocationAge(age,location),null), HttpStatus.OK);
     }

    public ResponseEntity<ResponseDOT> delete(String id)throws ListaSeException{
         listBoys.delete(id);
         return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> deleteMayAge(byte age)throws ListaSeException{
        listBoys.deleteMayAge(age);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> removeBoyByGender(String gender)throws ListaSeException{
        listBoys.removeBoyByGender(gender);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> deleteByPostion(int position)throws ListaSeException{
        listBoys.deleteByPosition(position);
        return new ResponseEntity<>(new ResponseDOT("Niño eliminado satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> addBoyPosition(Boy boy, int position) throws ListaSeException{
      listBoys.addByPosition(boy,position);
            return new ResponseEntity<>(new ResponseDOT("Niño adicionado", true, null), HttpStatus.OK);
     }

    public ResponseEntity<ResponseDOT> variantList() throws ListaSeException{
        listBoys.variantBoys();
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.getHead(),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> getBoysByLocation()throws ListaSeException{
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for (Location loc: locations){
            /** capturamos en el contador lo que nos diga la lista de niños por location pot cada código de localidad*/
            int count= listBoys.getCountBoysByLocation(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc, count));
        }
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",boysByLocations,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> getBoysByGender() throws ListaSeException{
        List<BoysByGender> boysByGender = new ArrayList<>();
        for (Gender gen: gender){
         int count= listBoys.getCountBoysByGender(gen.name());
         boysByGender.add(new BoysByGender(gen,count));
        }
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",boysByGender,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDOT> invertBoyByGenderAge(String gender, byte age) throws ListaSeException{
        listBoys.invertBoyByGenderAge(gender, age);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> invertBoyByGender(String gender) throws ListaSeException{
        listBoys.invertBoyByGender(gender);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT> boysGirlsGreater() throws ListaSeException {

        return new ResponseEntity<>(new ResponseDOT("Satisfactorio", listBoys.boysGirlsGreater(), null), HttpStatus.OK);

    }
    public ResponseEntity<ResponseDOT>advanceByPosition(String id, int position) throws ListaSeException {
        listBoys.advanceByPosition(id,position);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDOT>loseByPosition(String id, int position) throws ListaSeException {
        listBoys.loseByPosition(id,position);
        return new ResponseEntity<>(new ResponseDOT("Satisfactorio",true,null),HttpStatus.OK);
    }

}
