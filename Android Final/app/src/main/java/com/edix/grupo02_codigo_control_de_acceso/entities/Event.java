package com.edix.grupo02_codigo_control_de_acceso.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Event {
    @PrimaryKey
    @NonNull
    private int id;
    private long expires;

    private String name, description;

    public Event(){}
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getExpires() {
        return this.expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
