package com.qraccess.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.daos.mysql.CustomerDaoImp;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Customer;
import com.qraccess.utils.Log;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminDaoImp adminDao;
	@Autowired
	private CustomerDaoImp customerDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = this.adminDao.getByMail(username);
		Customer customer = null;

		if(admin == null){
			customer = this.customerDao.getByMail(username);
			if(customer == null){
				throw new UsernameNotFoundException(username);
			}else{
				return User
			        .withUsername(username)
			        .password(customer.getPassword())
			        .roles(Set.of("USER").toArray(new String[0]))
			        .build();
			}
		}else{
			return User
			        .withUsername(username)
			        .password(admin.getPassword())
			        .roles(Set.of("ADMIN").toArray(new String[0]))
			        .build();
		}
		 
	}

}
