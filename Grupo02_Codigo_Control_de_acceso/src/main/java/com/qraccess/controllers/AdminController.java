package com.qraccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.entities.Admin;
import com.qraccess.utils.Log;

@RestController
public class AdminController {
	
	@Autowired
	private AdminDaoImp adao;
	
	
	private ResponseEntity<Admin> ifNotExist(int id) {
		Admin admin = adao.getById(id);
		ResponseEntity<Admin> entity_response = null;
		if(admin == null) {
			entity_response = new ResponseEntity<Admin>(HttpStatus.INTERNAL_SERVER_ERROR);//404 NOT FOUND
		}
		return entity_response;		
	}
	
	@PostMapping(path="admin/", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		Log.info("Creando: " + admin);
		ResponseEntity<Admin> entity_response = new ResponseEntity<Admin>(adao.insert(admin),HttpStatus.CREATED);//201 CREATED
		return entity_response;
	}
	
	@GetMapping(path="admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getAdmin(@PathVariable("id") int id){
		System.out.println("GET admins/"+id);
		ResponseEntity<Admin> entity_response = this.ifNotExist(id);
		if(entity_response == null) {
			Admin admin = adao.getById(id);
			entity_response = new ResponseEntity<Admin>(admin, HttpStatus.OK);
		}
		return entity_response;
	}
	
	@DeleteMapping(path="admin/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) {
		System.out.println("ID a borrar: " + id);
		ResponseEntity<Admin> entity_response = this.ifNotExist(id);
		if(entity_response == null) {
			adao.delete(id);
			entity_response =  new ResponseEntity<Admin>(HttpStatus.OK);//200 OK
		}
		return entity_response;
	}
	
	@PutMapping(path="admin/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") int id,@RequestBody Admin admin_update) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Modificacion esperada: " + admin_update);
		
		
		ResponseEntity<Admin> entity_response = this.ifNotExist(id);		
		
		if(entity_response == null) {
			Admin admin = adao.getById(id);
			admin.update(admin_update);
			Admin admin_updated = adao.update(admin);
			entity_response = new ResponseEntity<Admin>(admin_updated,HttpStatus.OK);//200 OK	
		}
		
		return entity_response;
	}
	
}
