package com.qraccess.controllers;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.security.service.JwtUtilService;
import com.qraccess.security.utils.TokenInfo;
import com.qraccess.utils.Log;

@RestController
@RequestMapping
public class PublicController {
	
	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  UserDetailsService usuarioDetailsService;
	  
	  @Autowired
	  private JwtUtilService jwtUtilService;
	  
	  @Autowired
	  private CustomerDaoImp customerDao;

	  @Autowired
	  private AdminDaoImp adminDao;

	    /**
       * Authenticates the user given their username and password.
       *
       * @param  auth a map containing the user's username and password
       * @return      a ResponseEntity containing a TokenInfo object with a JWT token
       */
	  @PostMapping("/login")
	  public ResponseEntity<TokenInfo> authenticate(@RequestBody Map<String,String> auth) {
	   
		String username = auth.get("username");
		String pwd = auth.get("password");
		
		Log.info("Autenticando a" + username);

	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,pwd));

	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);
		
	    final String jwt = jwtUtilService.generateToken(userDetails);
		
	    return ResponseEntity.ok(new TokenInfo(jwt));
	  }

	    /**
       * Endpoint for user sign-in. Validates email and role and inserts 
       * user into appropriate table. Returns true if user is created, 
       * false otherwise. 
       *
       * @param  newUser map containing user information: name, email, 
       *                 password, and role
       * @return         ResponseEntity indicating whether user was 
       *                 successfully created
       */

	  @PostMapping("/signin")
	  public ResponseEntity<Boolean> singin(@RequestBody Map<String,String> newUser) {
		Boolean success = false;
		if(true){
			/* check email */
		}
		// if admin or customer is created returns true, else false
		if(newUser.get("role").equals("admin")){
			success = adminDao.insert(newUser.get("name"), newUser.get("mail"), newUser.get("password")) != null;
		}else if(newUser.get("role").equals("customer")){
			success = customerDao.insert(newUser.get("name"), newUser.get("mail"), newUser.get("password")) != null;
		}else{
			/* exception role not available */			
		}
		return ResponseEntity.ok(success);
	  }
}
