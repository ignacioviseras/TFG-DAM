package com.qraccess.entities;

public class Admin extends User{

	@Override
	public String toString() {
		return "Admin [getToken_access()=" + getToken_access() + ", getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getId()=" + getId() + "]";
	}

	public void update(Admin admin_update) {
		this.setMail(admin_update.getMail());
		this.setName(admin_update.getName());
		//this.setPassword(admin_update.getPassword());		
	}
	
}
