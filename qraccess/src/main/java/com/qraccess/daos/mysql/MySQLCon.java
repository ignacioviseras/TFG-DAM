package com.qraccess.daos.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qraccess.utils.Log;

public class MySQLCon {
	
	private final String path = "jdbc:mysql://localhost:3306/qr_access";
	private final String user = "root";
	private final String pass = "";
	protected Connection con;
	
	public boolean start() {
		// conecta a la base de datos 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Log.error("No se carga el driver:"+e.getMessage());
		}
		
		try {
			con = DriverManager.getConnection(path, user, pass);
			Log.info("base de datos conectada");
			return true;
		} catch (SQLException e) {
			Log.error("No se ha podido realizar la conexion:"+e.getMessage());
			return false;
		}
	}
	
	public boolean close() {
		// cierra la conexión con base de datos
		try {
			Log.info("base de datos cerrada");
			con.close();
		} catch (SQLException e) {
			Log.error("No se ha podido cerrar la conexion:"+e.getMessage());
			return false;
		}
		return true;
	}
	
	protected int getLastId(PreparedStatement query) {
		ResultSet rs;
		try {
			rs = query.getGeneratedKeys();
			if(rs.next()) {
				return Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;		
	}
}
