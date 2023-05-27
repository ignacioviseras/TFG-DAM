package com.edix.grupo02_codigo_control_de_acceso.io.response;

import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;

public class User {
    private String username, password, role, name, mail;
    public User(String username, String password){
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

    public User(String username, String password, String name, String role){
        this(username, password);
        this.name = name;
        this.role = role;
    }
    public String toString(){
        return "User{"+this.username+","+this.name+","+this.password+","+this.role+"}";
    }
    public static User get(String username, String password){
        return new User(username, password);
    }

    public static User get(String username, String password, String name, String role){
        return new User(username, password, name, role);
    }
}
