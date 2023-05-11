package com.qraccess.entities;

public class Access{

	private int id, availables, customer_id, event_id;

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

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int user_id) {
		this.customer_id = user_id;
	}
	
	public void validate() {
		this.availables--;
	}
}
