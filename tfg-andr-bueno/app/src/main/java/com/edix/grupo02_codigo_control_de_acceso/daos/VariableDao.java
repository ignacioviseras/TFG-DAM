package com.edix.grupo02_codigo_control_de_acceso.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.edix.grupo02_codigo_control_de_acceso.entities.Variable;

import java.util.List;

@Dao
public interface VariableDao {
    @Query("SELECT * FROM variable")
    List<Variable> getAll();

    @Query("SELECT * FROM variable WHERE key LIKE :k")
    Variable findByKey(String k);

    @Query("UPDATE variable SET value = :v WHERE key LIKE :k")
    void update(String k, String v);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Variable variable);

    @Delete
    void delete(Variable variable);
}
