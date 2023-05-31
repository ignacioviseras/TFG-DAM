package com.edix.grupo02_codigo_control_de_acceso.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.edix.grupo02_codigo_control_de_acceso.entities.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Query("SELECT * FROM event WHERE id LIKE :eventId")
    Event findById(int eventId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Event> events);

    @Query("DELETE FROM event")
    void reset();
}
