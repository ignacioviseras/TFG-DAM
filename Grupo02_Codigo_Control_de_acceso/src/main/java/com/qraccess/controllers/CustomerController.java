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
	private CustomerDaoImp customerDao;
	
	
}
