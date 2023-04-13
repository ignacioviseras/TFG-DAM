package com.qraccess.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToMany( mappedBy = "admin",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Access> accesses = new ArrayList<Access>();
	
	public List<Access> getAccesses() {
		return this.getAccesses();
	}
}
