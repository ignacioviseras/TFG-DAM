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
			String sql = "INSERT INTO ACCESSES(AVAILABLES,EXPIRES,USER_ID,ADMIN_ID)"+
							" VALUES(?,?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());
				ps.setLong(2,obj.getExpires());
				ps.setInt(3,obj.getUser_id());
				ps.setInt(4,obj.getAdmin_id());
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
	public Access getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Access update(Access obj) {
		if(this.start()){
			String sql = "UPDATE ACCESSES SET AVAILABLES=?, EXPIRES=?, USER_ID=?, ADMIN_ID=? WHERE ID=?";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,obj.getAvailables());
				ps.setLong(2,obj.getExpires());
				ps.setInt(3,obj.getUser_id());
				ps.setInt(4,obj.getAdmin_id());
				ps.setInt(5,obj.getId());
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
		Access access = this.getById(id);
		access.validate();
		this.update(access);
	}
	
	public void addValidations(int id, int n) {
		Access access = this.getById(id);
		access.setAvailables((access.getAvailables() + n));
		this.update(access);
	}
	
	public void renew(int id, long timestart) {
		Access access = this.getById(id);
		access.setExpires(timestart);
		this.update(access);
	}

}
