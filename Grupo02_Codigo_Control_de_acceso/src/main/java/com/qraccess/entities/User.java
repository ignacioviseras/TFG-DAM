package com.qraccess.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@OneToMany( mappedBy = "user",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Access> accesses = new ArrayList<Access>();
	
	public List<Access> getAccesses() {
		return this.getAccesses();
	}
}
