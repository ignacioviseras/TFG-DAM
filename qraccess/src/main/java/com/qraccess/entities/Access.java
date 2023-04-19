package com.qraccess.entities;

public class Access{

	private int id, availables, expires, user_id, admin_id;
	private String uuid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailables() {
		return availables;
	}

	public void setAvailables(int availables) {
		this.availables = availables;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid() {
		this.uuid = java.util.UUID.randomUUID().toString();
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}	
}
