package com.qraccess.entities;

public class Admin extends User{

	@Override
	public String toString() {
		return "Admin [getToken_access()=" + getToken_access() + ", getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getId()=" + getId() + "]";
	}
	
}
