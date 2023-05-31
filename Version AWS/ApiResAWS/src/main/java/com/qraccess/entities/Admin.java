package com.qraccess.entities;

public class Admin extends User{

	@Override
	public String toString() {
		return "Admin [ getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getId()=" + getId() + "]";
	}	
	
	public String getRole(){
		return "ADMIN";
	}
}
