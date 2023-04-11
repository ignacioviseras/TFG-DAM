package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public List<Access> getAccesses() {
		return super.getAccesses();
	}
}
