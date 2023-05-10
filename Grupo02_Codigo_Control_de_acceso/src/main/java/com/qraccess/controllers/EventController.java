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
import com.qraccess.utils.Log;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventDaoImp eventDao;

    @GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Event>> getVideoGame(){
		System.out.println("GET events/");
        Log.info("sdgreg");
		return new ResponseEntity<List<Event>>(eventDao.findAll(), HttpStatus.OK);
	}

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> getVideoGame(@PathVariable("id") int id){
		System.out.println("GET event/"+id);
		Event event = eventDao.findById(id);
		if(event != null) {
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}else {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND); //404 NOT FOUND
		}
	}

}
