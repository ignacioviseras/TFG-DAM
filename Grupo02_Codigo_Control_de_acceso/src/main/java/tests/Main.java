package tests;

import com.qraccess.daos.imp.UserDaoImpl;
import com.qraccess.entities.User;

public class Main {

	public static void main(String[] args) {
		User u = new User();
		u.setMail("sdfsdf");
		u.setName("agapito");
		u.setPassword("tofacil");
		UserDaoImpl.createUser(u);
	}


}
