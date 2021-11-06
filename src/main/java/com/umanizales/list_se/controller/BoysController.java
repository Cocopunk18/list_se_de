package com.umanizales.list_se.controller;

import com.umanizales.list_se.controller.dto.ResponseDOT;
import com.umanizales.list_se.exception.ListaDeException;
import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.Boy;
import com.umanizales.list_se.service.ListDeServ;
import com.umanizales.list_se.service.ListSeServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Clase que se encarga de mantener un control del programa entre mi modelo y mi servicio, se encarga de atender los mensajes que manda el usuario.
 */

@RestController
@RequestMapping(path = "boys")
public class BoysController {
    @Autowired
    private ListSeServ listSeServ;
    @Autowired
    private ListDeServ listDeServ;

    @PostMapping
    public ResponseEntity<ResponseDOT> addBoy(@RequestBody @Valid Boy boy) throws ListaSeException {
        return listSeServ.addBoy(boy);
    }

    @GetMapping
    public ResponseEntity<ResponseDOT> listBoys() throws ListaSeException {
        return listSeServ.listBoys();
    }


    @GetMapping(path = "free")
    public ResponseEntity<ResponseDOT> listBoysFree() throws ListaSeException {
        return listSeServ.listBoysFrees();
    }

    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDOT> invertLis() throws ListaSeException {
        return listSeServ.invertLis();
    }
    @GetMapping(path = "invertboygender/{gender}/{age}")
    public ResponseEntity<ResponseDOT> invertBoyByGenderAge(@PathVariable String gender, @PathVariable byte age) throws ListaSeException {
        return listSeServ.invertBoyByGenderAge(gender, age);
    }
    @GetMapping(path = "invertboygender/{gender}")
    public ResponseEntity<ResponseDOT> invertBoyByGender(@PathVariable String gender) throws ListaSeException {
        return listSeServ.invertBoyByGender(gender);
    }
    @PostMapping(path = "addBoysToStart")
    public ResponseEntity<ResponseDOT> addBoyToStart(@RequestBody Boy boy) throws ListaSeException {
        return listSeServ.addBoyToStart(boy);
    }

    @PostMapping(path = "addboys")
    public ResponseEntity<ResponseDOT> addBoys(@RequestBody List<Boy> boys) throws ListaSeException {
        for (Boy boy : boys) {
            listSeServ.addBoy(boy);
        }
        return new ResponseEntity<>(new ResponseDOT("Niños adicionados con exito", listSeServ.listBoys(), null), HttpStatus.OK);
    }

    @GetMapping(path = "getcount")
    public ResponseEntity<ResponseDOT> getCount() {
        return listSeServ.getCount();
    }

    @GetMapping(path = "count")
    public ResponseEntity<ResponseDOT> count() {
        return listSeServ.count();
    }

    @GetMapping(path = "changextreme")
    public ResponseEntity<ResponseDOT> changeXtremes() throws ListaSeException {
        return listSeServ.chageXtreme();
    }

    /*@PostMapping(path = "removeboy")
    public ResponseEntity<ResponseDOT> remove(@RequestBody Boy boy) {
        return listSeServ.remove(boy);
    }*/

    @GetMapping(path = "listgender/{gender}")
    public ResponseEntity<ResponseDOT> filtGender(@PathVariable String gender) throws ListaSeException {
        return listSeServ.filtGender(gender);
    }
    @GetMapping(path = "listboybydegree/{degree}")
    public ResponseEntity<ResponseDOT> listBoyByDegree(@PathVariable Integer degree) throws ListaSeException {
        return listSeServ.listBoyByDegree(degree);
    }

    @GetMapping(path = "listbylocationage/{age}/{location}")
    public ResponseEntity<ResponseDOT> listByLocationAge(@PathVariable byte age, @PathVariable String location) throws ListaSeException {
        return listSeServ.listByLocationAge(age,location);
    }
    @GetMapping(path = "delete/{id}")
    public ResponseEntity<ResponseDOT> delete(@PathVariable String id) throws ListaSeException {
        return listSeServ.delete(id);
    }
    @GetMapping(path = "deletebyposition/{position}")
    public ResponseEntity<ResponseDOT> deleteByPosition(@PathVariable int position) throws ListaSeException {
        return listSeServ.deleteByPostion(position);
    }
    @GetMapping(path = "deletemayage/{age}")
    public ResponseEntity<ResponseDOT> deleteMayAge(@PathVariable byte age)throws ListaSeException{
        return listSeServ.deleteMayAge(age);
    }
    @GetMapping(path = "removeboybygender/{gender}")
    public ResponseEntity<ResponseDOT> removeBoyByGender(@PathVariable String gender)throws ListaSeException{
        return listSeServ.removeBoyByGender(gender);
    }
    @PostMapping(path = "addtoposition/{position}")
    public ResponseEntity<ResponseDOT> addBoyPosition(@PathVariable int position, @RequestBody Boy boy) throws ListaSeException {
        return listSeServ.addBoyPosition(boy, position);
    }

    @GetMapping(path = "boybylocation")
    public ResponseEntity<ResponseDOT> boysByLocation() throws ListaSeException{

        return listSeServ.getBoysByLocation();
    }
    @GetMapping(path = "boybygender")
    public ResponseEntity<ResponseDOT> boysByGender()throws ListaSeException{
        return listSeServ.getBoysByGender();
    }

    @GetMapping(path = "variant")
    public ResponseEntity<ResponseDOT> variantBoy() throws ListaSeException{
        return listSeServ.variantList();
    }
    @GetMapping(path = "boysGirlsGreater/")
    public ResponseEntity<ResponseDOT> boysGirlsGreater() throws ListaSeException{
        return listSeServ.boysGirlsGreater();
    }
    @GetMapping(path = "advanceposition/{id}/{position}")
    public ResponseEntity<ResponseDOT> advanceByPosition(@PathVariable String id, @PathVariable int position) throws ListaSeException{
        return listSeServ.advanceByPosition(id,position);
    }
    @GetMapping(path = "loseposition/{id}/{position}")
    public ResponseEntity<ResponseDOT> loseByPosition(@PathVariable String id, @PathVariable int position) throws ListaSeException{
        return listSeServ.loseByPosition(id,position);
    }


    //---------------------------------Lista DE---------------------------------------

    @PostMapping(path = "deaddboys")
    public ResponseEntity<ResponseDOT> AddBoysDe(@RequestBody @Valid Boy boy)throws ListaDeException{
       return listDeServ.addBoyDe(boy);
    }
    @PostMapping(path = "addboysde")
    public ResponseEntity<ResponseDOT> addBoysDe(@RequestBody List<Boy> boys) throws ListaDeException {
        for (Boy boy : boys) {
            listDeServ.addBoyDe(boy);
        }
        return new ResponseEntity<>(new ResponseDOT("Niños adicionados con exito", listDeServ.listBoysDe(), null), HttpStatus.OK);
    }
    @GetMapping(path = "listde")
    public ResponseEntity<ResponseDOT>listBoyDe() throws ListaDeException{
        return listDeServ.listBoysDe();
    }
    @GetMapping(path = "freede")
    public ResponseEntity<ResponseDOT> listBoysFreedE() throws ListaDeException {
        return listDeServ.listBoysFreesDe();
    }

    @PostMapping(path = "addtostartde")
    public ResponseEntity<ResponseDOT> addToStartDe(@RequestBody Boy boy)throws ListaDeException{
        return listDeServ.addBoyToStartDe(boy);
    }
    @PostMapping(path = "addtopositionde/{position}")
    public ResponseEntity<ResponseDOT> addBoyPositionDe(@PathVariable int position, @RequestBody Boy boy) throws ListaDeException {
        return listDeServ.addByPositionDe(boy,position);
    }

    @GetMapping(path = "deletede/{id}")
    public ResponseEntity<ResponseDOT>deleteBoyDe(@PathVariable String id)throws ListaDeException{
        return listDeServ.deleteBoyDe(id);
    }

    @GetMapping(path = "invertde")
    public ResponseEntity<ResponseDOT> invertLisDe() throws ListaDeException {
        return listDeServ.invertLisDe();
    }
    @GetMapping(path = "invertboygenderde/{gender}/{age}")
    public ResponseEntity<ResponseDOT> invertBoyByGenderAgeDe(@PathVariable String gender, @PathVariable byte age) throws ListaDeException {
        return listDeServ.invertBoyByGenderAgeDe(gender, age);
    }
    @GetMapping(path = "invertboygenderde/{gender}")
    public ResponseEntity<ResponseDOT> invertBoyByGenderDe(@PathVariable String gender) throws ListaDeException {
        return listDeServ.invertBoyByGenderDe(gender);
    }

    @GetMapping(path = "getcountde")
    public ResponseEntity<ResponseDOT> getCountDe()throws ListaDeException {
        return listDeServ.getCountDe();
    }

    @GetMapping(path = "countde")
    public ResponseEntity<ResponseDOT> countDe()throws ListaDeException {
        return listDeServ.countDe();
    }

    @GetMapping(path = "changextremede")
    public ResponseEntity<ResponseDOT> changeXtremesDe() throws ListaDeException {
        return listDeServ.chageXtremeDe();
    }

    /*@PostMapping(path = "removeboy")
    public ResponseEntity<ResponseDOT> remove(@RequestBody Boy boy) {
        return listSeServ.remove(boy);
    }*/

    @GetMapping(path = "listgenderde/{gender}")
    public ResponseEntity<ResponseDOT> filtGenderDe(@PathVariable String gender) throws ListaDeException {
        return listDeServ.filtGenderDe(gender);
    }
    @GetMapping(path = "listboybydegreede/{degree}")
    public ResponseEntity<ResponseDOT> listBoyByDegreeDe(@PathVariable Integer degree) throws ListaDeException {
        return listDeServ.listBoyByDegreeDe(degree);
    }

    @GetMapping(path = "listbylocationagede/{age}/{location}")
    public ResponseEntity<ResponseDOT> listByLocationAgeDe(@PathVariable byte age, @PathVariable String location) throws ListaDeException {
        return listDeServ.listByLocationAgeDe(age,location);
    }

    @GetMapping(path = "deletebypositionde/{position}")
    public ResponseEntity<ResponseDOT> deleteByPositionDe(@PathVariable int position) throws ListaDeException {
        return listDeServ.deleteByPostionDe(position);
    }
    @GetMapping(path = "deletemayagede/{age}")
    public ResponseEntity<ResponseDOT> deleteMayAgeDe(@PathVariable byte age)throws ListaDeException{
        return listDeServ.deleteMayAgeDe(age);
    }
    @GetMapping(path = "removeboybygenderde/{gender}")
    public ResponseEntity<ResponseDOT> removeBoyByGenderDe(@PathVariable String gender)throws ListaDeException{
        return listDeServ.removeBoyByGenderDe(gender);
    }

    @GetMapping(path = "boybylocationde")
    public ResponseEntity<ResponseDOT> boysByLocationDe(){

        return listDeServ.getBoysByLocationDe();
    }
    @GetMapping(path = "boybygenderde")
    public ResponseEntity<ResponseDOT> boysByGenderDe(){
        return listDeServ.getBoysByGenderDe();
    }

    @GetMapping(path = "variantde")
    public ResponseEntity<ResponseDOT> variantBoyDe() throws ListaDeException{
        return listDeServ.variantListDe();
    }
    @GetMapping(path = "boysgirlsgreaterde/")
    public ResponseEntity<ResponseDOT> boysGirlsGreaterDe() throws ListaDeException{
        return listDeServ.boysGirlsGreaterDe();
    }
    @GetMapping(path = "advancepositionde/{id}/{position}")
    public ResponseEntity<ResponseDOT> advanceByPositionDe(@PathVariable String id, @PathVariable int position) throws ListaDeException{
        return listDeServ.advanceByPositionDe(id,position);
    }
    @GetMapping(path = "losepositionde/{id}/{position}")
    public ResponseEntity<ResponseDOT> loseByPositionDe(@PathVariable String id, @PathVariable int position) throws ListaDeException{
        return listDeServ.loseByPositionDe(id,position);
    }

}
