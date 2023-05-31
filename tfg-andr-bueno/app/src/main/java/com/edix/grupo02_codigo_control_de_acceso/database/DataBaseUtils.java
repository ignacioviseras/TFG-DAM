package com.edix.grupo02_codigo_control_de_acceso.database;

import android.content.Context;

import androidx.room.Room;

public class DataBaseUtils {

    private static String DATABASE_NAME = "appDataBase";

    public static AppDatabase getDBManager(Context context){
        return Room.databaseBuilder(context,AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public static void removeDB(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
