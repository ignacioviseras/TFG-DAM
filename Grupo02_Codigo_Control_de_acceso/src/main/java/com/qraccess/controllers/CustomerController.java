package com.qraccess.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.qraccess.daos.mysql.AccessDaoImp;
import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.entities.Access;
import com.qraccess.entities.Customer;
import com.qraccess.utils.Log;
import com.qraccess.utils.QRCodeGenerator;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerDaoImp customerDao;
	
	@Autowired
	private AccessDaoImp accessDao;

	/**
     * A POST request to buy a ticket to an event with the given event ID.
     *
     * @param  event_id  event id which will be used to buy access
     * @param  data      A map of data with payment_code and quantity of accesses bought.
     * @return           A ResponseEntity containing the access for that event updated and HTTPStatus.
     */
	@PostMapping(path="/buyevent/{event_id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Access> buyAccess(@PathVariable("event_id") int event_id,@RequestBody  Map<String,String> data) {

		var auth =  SecurityContextHolder.getContext().getAuthentication();
		int availables = 0;

		Customer c = customerDao.getByMail(auth.getName());
		
		if(c == null){
			return new ResponseEntity<Access>(HttpStatus.FORBIDDEN);//403 USER NOT FOUND
		}

		if(data.get("payment_code") != null){

		}else{
			// payment error
		}

		if(data.get("quantity") != null){
			try {
				availables = Integer.parseInt(data.get("quantity"));
			} catch (NumberFormatException e) {
				return new ResponseEntity<Access>(HttpStatus.UNPROCESSABLE_ENTITY);//422 ATTR QUANTITY MUST BE INTEGER
			}
		}else{
			return new ResponseEntity<Access>(HttpStatus.UNPROCESSABLE_ENTITY);//422 ATTR QUANTITY CANNOT BE NULL
		}

		Access access =  accessDao.findByCustomerIdAndEventId(c.getId(), event_id);

		if(access == null){
			access = new Access();
			access.setCustomer_id(c.getId());
			access.setEvent_id(event_id);
			access.setAvailables(availables);
			accessDao.insert(access);
			return new ResponseEntity<Access>(access,HttpStatus.CREATED);//201 CREATED
		}else{
			access.setAvailables(access.getAvailables() + availables);
			accessDao.update(access);
			return new ResponseEntity<Access>(access,HttpStatus.CREATED);//201 UPDATED
		}
	}

	@GetMapping(path="/accesses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Access>> getAccesses(){
		var auth =  SecurityContextHolder.getContext().getAuthentication();

		Customer c = customerDao.getByMail(auth.getName());
				
		if(c == null){
			return new ResponseEntity<List<Access>>(HttpStatus.FORBIDDEN);//403 USER NOT FOUND
		}
		return new ResponseEntity<List<Access>>(accessDao.findByCustomerId(c.getId()), HttpStatus.OK);
	}

	@GetMapping(path="/accesses/{event_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Access> getAccesses(@PathVariable("event_id") int event_id){
		
		var auth =  SecurityContextHolder.getContext().getAuthentication();
		Customer c = customerDao.getByMail(auth.getName());
		
		if(c == null){
			return new ResponseEntity<Access>(HttpStatus.FORBIDDEN);//403 USER NOT FOUND
		}else{
			Access access =  accessDao.findByCustomerIdAndEventId(c.getId(), event_id);
			
			if(access == null){
				return new ResponseEntity<Access>(HttpStatus.NOT_FOUND); //404 NOT FOUND
			}else{
				return new ResponseEntity<Access>(access, HttpStatus.OK);
			}
		}		
	}
	
    /**
     * Updates a customer's information.
     *
     * @param  newinfo  the customer object to update
     * @return          a ResponseEntity containing the updated customer object
     */	
	@PostMapping(path="/update",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer newinfo) {		
		newinfo.encryptPassword();
		Customer newAdmin = customerDao.update(newinfo);
		newAdmin.setPassword("******");
		return new ResponseEntity<Customer>(newAdmin,HttpStatus.CREATED);//201 CREATED
	}
	
	@GetMapping(path="/accesses/{event_id}/qr", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getQrAccess(@PathVariable("event_id") int event_id){
		var auth =  SecurityContextHolder.getContext().getAuthentication();
		Customer c = customerDao.getByMail(auth.getName());
				
		if(c == null){
			return new ResponseEntity<byte[]>(HttpStatus.FORBIDDEN);//403 USER NOT FOUND
		}else{
			Access access =  accessDao.findByCustomerIdAndEventId(c.getId(), event_id);
			if(access == null){
				return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR); //500 INTERNAL ERROR
			}else{	
				byte[] qr = access.gerQr();
				if(qr != null) {
					return new ResponseEntity<byte[]>(qr, HttpStatus.OK);
				}else {
					return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); //404 NOT FOUND
				}
			}
		}
	}
}
