package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.qraccess.daos.interfaces.CustomerDao;
import com.qraccess.entities.Customer;
@Component
public class CustomerDaoImp  extends MySQLCon implements CustomerDao{
	@Override
	public Customer insert(Customer obj) {
		if(this.start()){
			String sql = "INSERT INTO CUSTOMERS(NAME,MAIL,PASSWORD)"+
							" VALUES(?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,obj.getName());
				ps.setString(2,obj.getMail());
				ps.setString(3,obj.getPassword());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
			}catch(SQLException e) {
				System.err.print("No se ha podido registrar el customer:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return null;
	}
	
	public Customer getByMail( String mail) {
		if (!this.start()) {
			return null;
		}
		Customer customer = null;
		String sql = "SELECT ID, NAME, MAIL, PASSWORD FROM CUSTOMERS WHERE MAIL LIKE ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setMail(rs.getString(3));
				customer.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return customer;
	}

	@Override
	public Customer getById(int id) {
		if(!this.start()){
			return null;
		}
		Customer customer = null;
		String sql = "SELECT ID, NAME, MAIL FROM CUSTOMERS WHERE ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setMail(rs.getString(3));
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return customer;	
	}

	@Override
	public Customer update(Customer obj) {
		if(this.start()){
			String sql = "UPDATE CUSTOMERS SET NAME=?, MAIL=? WHERE ID=?";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,obj.getName());
				ps.setString(2,obj.getMail());
				//ps.setString(3,obj.getPassword());
				ps.setInt(3, obj.getId());
				ps.executeUpdate();
			}catch(SQLException e) {
				System.err.print("No se ha podido actualizar el Customer:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return obj;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		if (this.start()) {
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
