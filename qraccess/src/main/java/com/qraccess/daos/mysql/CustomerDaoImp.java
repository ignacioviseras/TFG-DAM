package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.qraccess.daos.interfaces.CustomerDao;
import com.qraccess.entities.Customer;
@Component
public class CustomerDaoImp  extends MySQLCon implements CustomerDao{
	
	public Customer insert(Customer obj) {
		if(this.start()){
			String sql = "INSERT INTO CUSTOMERS(NAME,EMAIL,PASSWORD)"+
							" VALUES(?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,obj.getName());
				ps.setString(2,obj.getMail());
				ps.setString(3,obj.getPassword());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
			}catch(SQLException e) {
				System.err.print("No se ha podido registrar el coche:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return null;
	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer update(Customer obj) {
		if(this.start()){
			String sql = "UPDATE CUSTOMERS (NAME=?,EMAIL=?,PASSWORD=?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,obj.getName());
				ps.setString(2,obj.getMail());
				ps.setString(3,obj.getPassword());
				ps.executeUpdate();
			}catch(SQLException e) {
				System.err.print("No se ha podido actualizar el user:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return obj;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		if(this.start()) {
			String sql = "DELETE FROM CUSTOMERS WHERE ID = ?";			
			try {
				PreparedStatement ps = this.con.prepareStatement(sql);
				ps.setInt(1, id);
				deleted = ps.executeUpdate() > 0;
				this.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return deleted;		
	}

	@Override
	public String showQr() {
		// TODO Auto-generated method stub
		return null;
	}

}
