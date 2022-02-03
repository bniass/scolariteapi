package cous.stic4.scolariteapi.model;

import java.util.List;

public class JwtResponse {
    private String username,nom_complet,token;
    private List<String> roles;

    public JwtResponse(String username, String nom_complet, String token, List<String> roles) {
        this.username = username;
        this.nom_complet = nom_complet;
        this.token = token;
        this.roles = roles;
    }

    public JwtResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
