package entities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "app")
@XmlType(propOrder={"bookshops", "product_name", "version"})
public class AppSystem {
	
	private EntityManagerFactory factory;
	private String version = "beta";
	private String product_name = "TusLibrerias.com";
	
	public AppSystem() {
		this.factory = Persistence.createEntityManagerFactory("ProyectoDAM");		
		this.loadInfo();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return this.factory;
	}

	@SuppressWarnings("deprecation")
	private void loadInfo() {
		// carga los entity manager
		EntityManager em =  factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		// comienza la transacción
		et.begin();
		
	
		
		//se cierra la conexión a BD
		
		em.close();
		
	}
	


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	
	
	private String out_str = "";
	
	private void out(String str) {
		out_str +="\n " + str;
		System.out.println(str);
	}
}
