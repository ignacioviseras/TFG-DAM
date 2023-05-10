package com.qraccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.daos.mysql.EventDaoImp;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Event;
import com.qraccess.utils.Log;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDaoImp adao;

	@Autowired
	private EventDaoImp eventDao;

	@PostMapping(path="/addevent",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> addVideoGame(@RequestBody Event event) {
		System.out.println("creating event: " + event.toString());
		var auth =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("creating event: " + auth.toString());
		return new ResponseEntity<Event>(eventDao.insert(event),HttpStatus.CREATED);//201 CREATED
	}
}
