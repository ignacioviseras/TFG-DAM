package com.edix.grupo02_codigo_control_de_acceso.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Variable {
    @PrimaryKey
    @NonNull
    public String key;

    public String value;

    public Variable(@NonNull String key, String value){
        this.key = key;
        this.value = value;
    }
}
