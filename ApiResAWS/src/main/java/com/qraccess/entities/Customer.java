package com.qraccess.entities;

public class Customer extends User{
	
	@Override
	public String toString() {
		return "Customer [getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getId()=" + getId() + "]";
	}

	public String getRole(){
		return "CUSTOMER";
	}
}
