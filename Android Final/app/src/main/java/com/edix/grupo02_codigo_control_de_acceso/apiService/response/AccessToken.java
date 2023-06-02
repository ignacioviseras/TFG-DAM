package com.edix.grupo02_codigo_control_de_acceso.apiService.response;

public class AccessToken {

    private String jwtToken;

    public AccessToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken(){
        return this.jwtToken;
    }

    public void setJwtToken(String token){
        this.jwtToken = token;
    }
}
