package com.edix.grupo02_codigo_control_de_acceso.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.edix.grupo02_codigo_control_de_acceso.daos.AccessDao;
import com.edix.grupo02_codigo_control_de_acceso.daos.EventDao;
import com.edix.grupo02_codigo_control_de_acceso.daos.UserDao;
import com.edix.grupo02_codigo_control_de_acceso.daos.VariableDao;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;
import com.edix.grupo02_codigo_control_de_acceso.entities.Variable;

@Database(entities = {Variable.class, User.class, Event.class, Access.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VariableDao variableDao();
    public abstract AccessDao accessDao();
    public abstract EventDao eventDao();
    public abstract UserDao userDao();
}