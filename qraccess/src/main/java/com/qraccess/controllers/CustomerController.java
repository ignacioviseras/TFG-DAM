package com.qraccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.entities.Customer;
import com.qraccess.utils.Log;

@RestController
public class CustomerController {
	@Autowired
	private CustomerDaoImp cdao;
	
	private ResponseEntity<Customer> checkCustomer(Customer name) {		
		if(false /* ya existe */) {
			System.out.println("Ya existe un videojuego con el nombre \""+name+"\"");
			return new ResponseEntity<Customer>(HttpStatus.NOT_ACCEPTABLE);//406 EMAIL ALREADY EXISTS
		}	
		return null;
	}
	
	@PostMapping(path="customer",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Log.info("Creando: " + customer);
		ResponseEntity<Customer> entity_response = this.checkCustomer(customer); // comprueba si el nombre es válido		
		if(entity_response == null) {
			entity_response = new ResponseEntity<Customer>(cdao.insert(customer),HttpStatus.CREATED);//201 CREATED
		}
		return entity_response;
	}
	
	
	@GetMapping(path="customer",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer) {
		Log.info("Eliminando: " + customer);
		ResponseEntity<Customer> entity_response = this.checkCustomer(customer); // comprueba si el nombre es válido
		if(entity_response == null) {
			entity_response = new ResponseEntity<Customer>(cdao.delete(customer.getId()),HttpStatus.OK);//201 CREATED
		}
		return entity_response;
	}
	
	
}
