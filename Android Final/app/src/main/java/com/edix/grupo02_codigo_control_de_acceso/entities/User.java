package com.edix.grupo02_codigo_control_de_acceso.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.edix.grupo02_codigo_control_de_acceso.helpers.AppUtils;
@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    private String password, role, name, mail;
    public User(){   }
    @Ignore
    public User( @NonNull String username, String password){
        if(password.isEmpty()){
            throw new IllegalArgumentException("El password no puede estar vacío");
        }
        if(!AppUtils.isValidMail(username)){
            throw new IllegalArgumentException("Email incorrecto");
        }
        this.username = username;
        this.password = password;
        this.mail = username; // api entiende mail como username, por eso esto
    }
    @Ignore
    public User(String username, String password, String name, String role){
        this(username, password);
        this.name = name;
        this.role = role;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public static User get(String username, String password){
        return new User(username.trim(), password);
    }

    public static User get(String username, String password, String name, String role){
        return new User(username.trim(), password, name, role);
    }

    public static User getForUpdate(String username, String password, String name){
        if (username.isEmpty() && password.isEmpty() && name.isEmpty()) {
            return null;
        }else{
            User user = new User();
            if(!username.equals("")){
                user.setMail(username.trim());
            }
            if(!password.equals("")){
                user.setPassword(password);
            }
            if(!name.equals("")){
                user.setName(name);
            }
            return user;
        }
    }
}
