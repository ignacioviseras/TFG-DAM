package com.qraccess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.EventDaoImp;
import com.qraccess.entities.Event;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventDaoImp eventDao;

	/**
     * Retrieves all events from the database.
     *
     * @return A ResponseEntity containing a list of all events.
     */
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Event>> getEvents(){
		return new ResponseEntity<List<Event>>(eventDao.findAll(), HttpStatus.OK);
	}

    /**
     * Retrieves a single event by its ID.
     *
     * @param  id  the ID of the event to retrieve
     * @return     a ResponseEntity containing the retrieved event and a 
     *             HttpStatus of OK (200) if the event is found, or a 
     *             ResponseEntity with a HttpStatus of NOT_FOUND (404) if the 
     *             event is not found
     */
    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> getEvent(@PathVariable("id") int id){
		System.out.println("GET event/"+id);
		Event event = eventDao.findById(id);
		if(event != null) {
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}else{
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND); //404 NOT FOUND
		}
	}
}
