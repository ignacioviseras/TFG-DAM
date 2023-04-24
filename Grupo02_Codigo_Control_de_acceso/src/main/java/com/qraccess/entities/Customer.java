package com.qraccess.entities;

public class Customer extends User{
	
	@Override
	public String toString() {
		return "Customer [getToken_access()=" + getToken_access() + ", getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getId()=" + getId() + "]";
	}

	public void update(Customer customer_update) {
		this.setMail(customer_update.getMail());
		this.setName(customer_update.getName());
		//this.setPassword(admin_update.getPassword());		
	}
}
