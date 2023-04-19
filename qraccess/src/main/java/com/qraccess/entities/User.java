package com.qraccess.entities;

import com.qraccess.utils.PasswordEncrypter;

public abstract class User  {
	private int id;
	private String name, password, mail, token_access;
	// token_access es un uuid que se genera cuando una persona se loga
	// el token_access es null mientras la persona no esta logueada

	public String getToken_access() {
		return token_access;
	}

	public void setToken_access(String token_access) {
		this.token_access = token_access;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {		
		this.password = PasswordEncrypter.encrypt(password);
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

}
