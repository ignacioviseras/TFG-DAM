package com.qraccess.entities;

public class Access{

	private int id, availables, user_id, event_id;

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

	public int getEvent_id() {
		return event_id;
	}

	public void getEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void validate() {
		this.availables--;
	}
}
