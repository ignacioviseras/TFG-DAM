package com.qraccess.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.entities.Admin;
import com.qraccess.utils.Log;

@RestController
public class AdminController {
	
	@Autowired
	private AdminDaoImp adao;
	
	private ResponseEntity<Admin> checkAdmin(Admin name) {		
		if(false /* ya existe */) {
			System.out.println("\"Admin already exists for this user \""+name+"\"");
			return new ResponseEntity<Admin>(HttpStatus.NOT_ACCEPTABLE);//406 EMAIL ALREADY EXISTS
		}	
		return null;
	}
	
	@PostMapping(path="admin/", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		Log.info("Creando: " + admin);
		ResponseEntity<Admin> entity_response = this.checkAdmin(admin); // comprueba si el nombre es válido		
		if(entity_response == null) {
			entity_response = new ResponseEntity<Admin>(adao.insert(admin),HttpStatus.CREATED);//201 CREATED
		}
		return entity_response;
	}
	
	@GetMapping(path="admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getAdmin(@PathVariable("id") int id){
		System.out.println("GET videogames/"+id);
		Admin admin = adao.getById(id);
		if(admin != null) {
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);
		}else {
			return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND); //404 NOT FOUND
		}
	}
	
}
