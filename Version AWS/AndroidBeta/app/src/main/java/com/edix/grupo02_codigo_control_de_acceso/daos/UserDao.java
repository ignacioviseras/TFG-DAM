package com.edix.grupo02_codigo_control_de_acceso.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE username LIKE :usr")
    User findByUsername(String usr);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM user")
    void reset();
}
