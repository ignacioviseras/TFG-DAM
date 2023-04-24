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

import com.qraccess.daos.interfaces.CrudDao;
import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.entities.Customer;
import com.qraccess.utils.Log;

@RestController
public class CustomerController {
	@Autowired
	private CustomerDaoImp cdao;
	
	private ResponseEntity<Customer> ifNotExist(int id) {
		Customer customer = cdao.getById(id);
		ResponseEntity<Customer> entity_response = null;
		if(customer == null) {
			entity_response = new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);//404 NOT FOUND
		}
		return entity_response;		
	}
	
	@PostMapping(path="customer",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Log.info("Creando: " + customer);
		ResponseEntity<Customer> entity_response = new ResponseEntity<Customer>(cdao.insert(customer),HttpStatus.CREATED);//201 CREATED
		return entity_response;
	}
	
	
	@GetMapping(path="customer/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id) {
		System.out.println("GET customer/"+id);
		ResponseEntity<Customer> entity_response = this.ifNotExist(id);
		if(entity_response == null) {
			Customer customer = cdao.getById(id);
			entity_response = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		return entity_response;
	}
	
	@DeleteMapping(path="customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
		System.out.println("ID a borrar: " + id);
		ResponseEntity<Customer> entity_response = this.ifNotExist(id);
		if(entity_response == null) {
			cdao.delete(id);
			entity_response =  new ResponseEntity<Customer>(HttpStatus.OK);//200 OK
		}
		return entity_response;
	}
	
	@PutMapping(path="customer/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@RequestBody Customer customer_update) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Modificacion esperada: " + customer_update);
		
		
		ResponseEntity<Customer> entity_response = this.ifNotExist(id);		
		
		if(entity_response == null) {
			Customer customer = cdao.getById(id);
			customer.update(customer_update);
			Customer customer_updated = cdao.update(customer);
			entity_response = new ResponseEntity<Customer>(customer_update,HttpStatus.OK);//200 OK	
		}
		
		return entity_response;
	}
	
}
