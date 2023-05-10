package com.qraccess.controllers;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.entities.Customer;
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
