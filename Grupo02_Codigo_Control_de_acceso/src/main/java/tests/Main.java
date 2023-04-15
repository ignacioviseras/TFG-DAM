package tests;

import com.qraccess.daos.imp.AccessDaoImpl;
import com.qraccess.daos.imp.AdminDaoImpl;
import com.qraccess.daos.imp.UserDaoImpl;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Customer;
import com.qraccess.entities.User;

public class Main {

	public static void main(String[] args) {
//		
//		User u = new User();
//		u.setMail("sdfsdf");
//		u.setName("agapito");
//		u.setPassword("tofacil");
//		
//		Admin a = new Admin();		
//		a.setUser(u);
//		AdminDaoImpl.createAdmin(a);
//		
//		
//		Access ac = new Access();
//		ac.setAvailables(22);
//		ac.setExpires(55);
//		ac.setUuid();
//		AccessDaoImpl.createAccess(ac);
//		
//		Customer c = new Customer();
//		c.setUser(u);
//		UserDaoImpl.createUser(c);
		
		UserDaoImpl uss = new UserDaoImpl();
		uss.getById(1);
		
		
	}


}
