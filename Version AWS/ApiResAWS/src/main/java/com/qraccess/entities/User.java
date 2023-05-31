package com.qraccess.entities;

import org.springframework.security.crypto.bcrypt.BCrypt;

public abstract class User  {
	private int id;
	private String name, password, mail;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void encryptPassword() {
		String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(this.password, salt);
	}

	public abstract String getRole();

}
