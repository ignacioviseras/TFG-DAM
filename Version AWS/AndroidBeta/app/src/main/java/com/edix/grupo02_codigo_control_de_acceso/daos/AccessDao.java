package com.edix.grupo02_codigo_control_de_acceso.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Variable;

import java.util.List;

@Dao
public interface AccessDao {
    @Query("SELECT * FROM access")
    List<Access> getAll();

    @Query("SELECT * FROM access WHERE id LIKE :accessId")
    Access findById(int accessId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Access> accesses);

    @Query("DELETE FROM access")
    void reset();

    @Delete
    void delete(Access access);
}
