package com.qraccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AccessDaoImp;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
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
	
	@PostMapping(path="access", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Access> addAccess(@RequestBody Access access) {
		Log.info("Creando: " + access);
		ResponseEntity<Access> entity_response = this.checkAccess(access); // comprueba si el nombre es válido		
		if(entity_response == null) {
			entity_response = new ResponseEntity<Access>(acdao.insert(access),HttpStatus.CREATED);//201 CREATED
		}
		return entity_response;
	}

	

}
