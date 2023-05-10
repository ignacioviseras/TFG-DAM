package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;
@Component
public class AccessDaoImp extends MySQLCon implements AccessDao{

	@Override
	public Access insert(Access obj) {
		if(this.start()){
			String sql = "INSERT INTO ACCESSES(AVAILABLES,USER_ID,EVENT_ID)"+
							" VALUES(?,?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());
				ps.setInt(2,obj.getUser_id());
				ps.setInt(3,obj.getEvent_id());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
				return obj;
			}catch(SQLException e) {
				System.err.print("No se ha podido registrar el coche:"+e.getMessage());
			}finally {
				this.close();
			}
		}
		return null;
	}

	@Override
	public Access findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Access update(Access obj) {
		if(this.start()){
			String sql = "UPDATE ACCESSES SET AVAILABLES=?, USER_ID=?, EVENT_ID=? WHERE ID=?";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());				
				ps.setInt(2,obj.getUser_id());
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

	@Override
	public void validate(int id) {
		Access access = this.findById(id);
		access.validate();
		this.update(access);
	}
	
	public void addValidations(int id, int n) {
		Access access = this.findById(id);
		access.setAvailables((access.getAvailables() + n));
		this.update(access);
	}
	
	public void renew(int id, long timestart) {
		Access access = this.findById(id);
		this.update(access);
	}

}
