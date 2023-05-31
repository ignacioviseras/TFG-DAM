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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qraccess.daos.mysql.AccessDaoImp;
import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.daos.mysql.EventDaoImp;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Event;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDaoImp adminDao;

	@Autowired
	private EventDaoImp eventDao;

	@Autowired
	private AccessDaoImp accessDao;

/**
 * Adds an event to the system.
 *
 * @param  event the Event object to be added to the system
 * @return       a ResponseEntity object with the added Event and status code 201 CREATED
 */

	@PostMapping(path="/addevent",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> addEvent(@RequestBody Event event) {
		System.out.println("creating event: " + event.toString());
		var auth =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("creating event: " + auth.toString());
		return new ResponseEntity<Event>(eventDao.insert(event),HttpStatus.CREATED);//201 CREATED
	}
	/**
	 * Updates an event for the given event ID.
	 *
	 * @param  event_id	the ID of the event to be updated
	 * @param  event		the updated event object
	 * @return         	a ResponseEntity with the updated event and a 201 CREATED status
	 */
	@PostMapping(path="/event/{eventId}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> updateAdmin(@PathVariable("eventId") int event_id, @RequestBody Event event) {	
		event.setId(event_id);
		return new ResponseEntity<Event>(eventDao.update(event),HttpStatus.CREATED);//201 CREATED
	}
	/**
	 * Deletes an event from the database.
	 *
	 * @param  event_id	the id of the event to delete
	 * @return         	a ResponseEntity<Boolean> indicating if the delete was successful
	 */
	@DeleteMapping(path="/eventDelete/{eventId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateAdmin(@PathVariable("eventId") int event_id) {	
		return new ResponseEntity<Boolean>(eventDao.delete(event_id),HttpStatus.CREATED);//201 CREATED
	}
	
	/**
	 * Deletes an event by ID.
	 *
	 * @param  event_id	the ID of the event to be deleted
	 * @return         	a boolean indicating if the deletion was successful
	 */
	@DeleteMapping(path="/event/{event_id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteEvent(@PathVariable("event_id") int event_id){
		Event event = eventDao.findById(event_id);
		if(event == null){
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND); //404 NOT FOUND
		}else{
			eventDao.delete(event_id);
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);//201 CREATED
		}		
	}
    /**
     * Retrieves an Access object by its ID and validates it.
     *
     * @param  access_id    the ID of the Access object to be retrieved
     * @return          a ResponseEntity containing the validated Access object
     *                  or a 404 NOT FOUND status if the Access object is not found
     */
	@GetMapping(path="/validateAccess/{access_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Access> getEvent(@PathVariable("access_id") int access_id){
		Access access= accessDao.findById(access_id);
		if(access == null){
			return new ResponseEntity<Access>(HttpStatus.NOT_FOUND); //404 NOT FOUND
		}else{
			access.validate();
			if(access.getAvailables() <= 0){
				accessDao.delete(access_id);
			}else{
				accessDao.update(access);
			}			
			return new ResponseEntity<Access>(access, HttpStatus.OK);
		}
	}
	
	/**
	 * Updates an admin with new information.
	 *
	 * @param  newinfo  the new Admin object to be updated
	 * @return          ResponseEntity containing the updated Admin object with the password encrypted and hidden
	 */
	@PostMapping(path="/update",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin newinfo) {		
		newinfo.encryptPassword();
		Admin newAdmin = adminDao.update(newinfo);
		newAdmin.setPassword("******");
		return new ResponseEntity<Admin>(newAdmin,HttpStatus.CREATED);//201 CREATED
	}

	/**
	 * Retrieves the customer information based on the authenticated user's email. 
	 * Returns HTTP status code 403 if the user is not found. 
	 * Otherwise, returns the customer information with the password removed. 
	 *
	 * @return          ResponseEntity<Customer> containing the customer information
	 */
	@GetMapping(path="/whoami", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> whoAmI(){
		var auth =  SecurityContextHolder.getContext().getAuthentication();

		Admin admin = adminDao.getByMail(auth.getName());	
				
		if(admin == null){
			return new ResponseEntity<Admin>(HttpStatus.FORBIDDEN);//403 USER NOT FOUND
		}else{
			admin.setPassword("*********");
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);
		}		
	}
}
