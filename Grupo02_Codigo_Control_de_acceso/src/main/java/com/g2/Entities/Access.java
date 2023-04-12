package com.g2.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accesses")
public class Access implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int availables, expires;
	private String uuid;
	
	//@JoinColumn(name = "users", referencedColumnName = "id")
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name="admin_id", referencedColumnName="id")
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
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

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
