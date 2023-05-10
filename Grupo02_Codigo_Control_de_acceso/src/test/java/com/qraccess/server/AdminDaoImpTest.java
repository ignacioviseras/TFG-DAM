package com.qraccess.server;

import org.junit.jupiter.api.Test;

import com.qraccess.daos.mysql.AdminDaoImp;
import com.qraccess.entities.Admin;

public class AdminDaoImpTest {
	
	@Test
	public void test1() {
		Admin admin = new Admin();
		admin.setMail("admin1@gmail.com");
		admin.setName("admin uno");
		admin.setPassword("admin1");
		final AdminDaoImp  admin_dao = new AdminDaoImp();
		admin = admin_dao.insert(admin);
		System.out.println(admin);
		 try {
			 // tras 5 segundos recupera el admin
			 Thread.sleep(5*1000);
			 System.out.println( admin_dao.findById(admin.getId()));
            //tras 15 segundos borra el admin
            Thread.sleep(15*1000);
            admin_dao.delete(admin.getId());
         } catch (Exception e) {
            System.out.println(e);
         }		
	}

}
