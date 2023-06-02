package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;
@Component
public class AccessDaoImp extends MySQLCon implements AccessDao{

	@Override
	public Access insert(Access obj) {
		if(this.start()){
			String sql = "INSERT INTO ACCESSES(AVAILABLES,CUSTOMER_ID,EVENT_ID)"+
							" VALUES(?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());
				ps.setInt(2,obj.getCustomer_id());
				ps.setInt(3,obj.getEvent_id());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
				return obj;
			}catch(SQLException e) {
				System.err.print("No se ha podido registrar el acceso:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return null;
	}

	@Override
	public Access findById(int id) {
		if (!this.start()) {
			return null;
		}
		Access access = null;
		String sql = "SELECT ID, AVAILABLES, CUSTOMER_ID, EVENT_ID FROM ACCESSES WHERE ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				access = new Access();
				access.setId(rs.getInt(1));
				access.setAvailables(rs.getInt(2));
				access.setCustomer_id(rs.getInt(3));
				access.setEvent_id(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return access;
	}

	public Access findByCustomerIdAndEventId(int customer_id, int event_id){
		if (!this.start()) {
			return null;
		}
		Access access = null;
		String sql = "SELECT ID, AVAILABLES, CUSTOMER_ID, EVENT_ID FROM ACCESSES WHERE EVENT_ID = ? AND CUSTOMER_ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, event_id);
			ps.setInt(2, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				access = new Access();
				access.setId(rs.getInt(1));
				access.setAvailables(rs.getInt(2));
				access.setCustomer_id(rs.getInt(3));
				access.setEvent_id(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return access;
	}

	public List<Access> findByCustomerId(int customer_id){
		if (!this.start()) {
			return null;
		}
		List<Access> accesses = new ArrayList<Access>();
		
		String sql = "SELECT ID, AVAILABLES, CUSTOMER_ID, EVENT_ID FROM ACCESSES WHERE CUSTOMER_ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Access access = new Access();
				access.setId(rs.getInt(1));
				access.setAvailables(rs.getInt(2));
				access.setCustomer_id(rs.getInt(3));
				access.setEvent_id(rs.getInt(4));
				accesses.add(access);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return accesses;
	}

	@Override
	public Access update(Access obj) {
		if(this.start()){
			String sql = "UPDATE ACCESSES SET AVAILABLES=?, CUSTOMER_ID=?, EVENT_ID=? WHERE ID=?";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());				
				ps.setInt(2,obj.getCustomer_id());
				ps.setInt(3,obj.getEvent_id());
				ps.setInt(4,obj.getId());
				ps.executeUpdate();
			}catch(SQLException e) {
				System.err.print("No se ha podido actualizar el acceso:"+e.getMessage());
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
			String sql = "DELETE FROM ACCESSES WHERE ID = ?";			
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
}
