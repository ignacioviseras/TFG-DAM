package com.qraccess.daos.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.qraccess.daos.interfaces.EventDao;
import com.qraccess.entities.Event;

@Component
public class EventDaoImp extends MySQLCon implements EventDao{

    @Override
    public Event insert(Event obj) {
        if (this.start()) {
			String sql = "INSERT INTO EVENTS(NAME,DESCRIPTION,EXPIRES)" + " VALUES(?,?,?)";
			try {
				PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getDescription());
				ps.setLong(3, obj.getExpires());
				ps.executeUpdate();
				obj.setId(this.getLastId(ps));
				return obj;
			} catch (SQLException e) {
				System.err.print("No se ha podido registrar el evento:" + e.getMessage());
			} finally {
				this.close();
			}
		}
		return null;
    }

    @Override
    public Event findById(int id) {
        if (!this.start()) {
			return null;
		}
		Event event = null;
		String sql = "SELECT ID, NAME, DESCRIPTION, EXPIRES FROM EVENTS WHERE ID = ?";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setId(rs.getInt(1));
				event.setName(rs.getString(2));
				event.setDescription(rs.getString(3));
                event.setExpires(rs.getLong(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return event;
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<Event>();
        if (!this.start()) {
			return null;
		}
		String sql = "SELECT ID, NAME, DESCRIPTION, EXPIRES FROM EVENTS";
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setId(rs.getInt(1));
				event.setName(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setExpires(rs.getLong(4));
                events.add(event);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			this.close();
		}
		return events;
    }

    @Override
    public Event update(Event obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    } 
    
}
