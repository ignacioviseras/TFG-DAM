package tests;

import com.qraccess.daos.imp.AccessDaoImpl;
import com.qraccess.daos.imp.AdminDaoImpl;
import com.qraccess.daos.imp.CustomerDaoImpl;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Customer;
import com.qraccess.entities.User;

public class Main {

	public static void main(String[] args) {
		
		User u = new User();
		u.setMail("sdfsdf");
		u.setName("agapito");
		u.setPassword("tofacil");
		
		Admin a = new Admin();		
		a.setUser(u);
		AdminDaoImpl.createAdmin(a);
		
		
		Access ac = new Access();
		ac.setAvailables(22);
		ac.setExpires(55);
		ac.setUuid();
		AccessDaoImpl.createAccess(ac);
		
		Customer c = new Customer();
		c.setUser(u);
		CustomerDaoImpl.createUser(c);
		
		 u = new User();
		u.setMail("sdfsdf22");
		u.setName("agapito22");
		u.setPassword("tofacil22");
		c = new Customer();
		c.setUser(u);
		CustomerDaoImpl uss = new CustomerDaoImpl();
		uss.getById(1);
		uss.update(1, c);
		
		
	}


}
