package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.qraccess.daos.interfaces.AdminDao;
import com.qraccess.entities.Admin;

@Component
public class AdminDaoImp extends MySQLCon implements AdminDao {
	@Override
	public Admin insert(Admin obj) {
		if (this.start()) {
			String sql = "INSERT INTO ADMINS(NAME,MAIL,PASSWORD)" + " VALUES(?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getMail());
				ps.setString(3, obj.getPassword());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
				return obj;
			} catch (SQLException e) {
				System.err.print("No se ha podido registrar el admin:" + e.getMessage());
			} finally {
				this.close();
			}
		}
		return null;
	}

	public Admin insert(String name, String email, String pwd) {
		Admin a = new Admin();
		a.setMail(email);
		a.setName(name);
		a.setPassword(pwd);
		a.encryptPassword();
		return this.insert(a);
	}
	
    public Admin getByMail( String mail) {
		if (!this.start()) {
			return null;
		}
		Admin admin = null;
		String sql = "SELECT ID, NAME, MAIL, PASSWORD FROM ADMINS WHERE MAIL LIKE ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt(1));
				admin.setName(rs.getString(2));                
				admin.setMail(rs.getString(3));
                admin.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return admin;
	}

	@Override
	public Admin findById(int id) {
		if (!this.start()) {
			return null;
		}
		Admin admin = null;
		String sql = "SELECT ID, NAME, MAIL FROM ADMINS WHERE ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt(1));
				admin.setName(rs.getString(2));
				admin.setMail(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return admin;
	}

	@Override
	public Admin update(Admin obj) {
		if (this.start()) {
			String sql = "UPDATE ADMINS SET NAME=?, MAIL=? WHERE ID=?";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getMail());
				ps.setInt(3,  obj.getId());
				// ps.setString(3,obj.getPassword());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.print("No se ha podido actualizar el admin:" + e.getMessage());
			} finally {
				this.close();
			}
		}
		return obj;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		if (this.start()) {
			String sql = "DELETE FROM ADMINS WHERE ID = ?";
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
