package com.qraccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AccessDaoImp;
import com.qraccess.entities.Access;
import com.qraccess.utils.Log;
@RestController
public class AccessController {

    @Autowired
    private AccessDaoImp acdao;

    private ResponseEntity<Access>checkAccess(Access name){
        if(false) {
            System.out.println("Access already exists for this user");
            return new ResponseEntity<Access>(HttpStatus.NOT_ACCEPTABLE);//406 EMAIL ALREADY EXISTS
        }
        return null;
    }

    private ResponseEntity<Access> ifNotExist(int id) {
        Access admin = acdao.findById(id);
        ResponseEntity<Access> entity_response = null;
        if(admin == null) {
            entity_response = new ResponseEntity<Access>(HttpStatus.INTERNAL_SERVER_ERROR);//404 NOT FOUND
        }
        return entity_response;
    }

    @PostMapping(path="access", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Access> addAccess(@RequestBody Access access) {
        Log.info("Creando: " + access);
        ResponseEntity<Access> entity_response = this.checkAccess(access); // comprueba si el nombre es válido
        if(entity_response == null) {
            entity_response = new ResponseEntity<Access>(acdao.insert(access),HttpStatus.CREATED);//201 CREATED
        }
        return entity_response;
    }
@GetMapping(path="access/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Access> getAccess(@PathVariable("id") int id){
        System.out.println("GET access/"+id);
        ResponseEntity<Access> entity_response = this.ifNotExist(id);
        if(entity_response == null) {
            Access accs = acdao.findById(id);
            entity_response = new ResponseEntity<Access>(accs, HttpStatus.OK);
        }
        return entity_response;
    }


    @DeleteMapping(path="access/{id}")
    public ResponseEntity<Access> deleteAccess(@PathVariable int id) {
        System.out.println("ID a borrar: " + id);
        ResponseEntity<Access> entity_response = this.ifNotExist(id);
        if(entity_response == null) {
            acdao.delete(id);
            entity_response =  new ResponseEntity<Access>(HttpStatus.OK);//200 OK
        }
        return entity_response;
    }

   /* @PutMapping(path="access/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Access> updateAccess(@PathVariable("id") int id,@RequestBody Access accessUpdated) {
        System.out.println("ID a modificar: " + id);
        System.out.println("Modificacion esperada: " + accessUpdated);


        ResponseEntity<Access> entity_response = this.ifNotExist(id);

        if(entity_response == null) {
            Access access = acdao.getById(id);
            access.update(accessUpdated);
            access.
            Access accessUpdated = acdao.update(access);
            entity_response = new ResponseEntity<Access>(accessUpdated,HttpStatus.OK);//200 OK
        }

        return entity_response;
    }*/

}