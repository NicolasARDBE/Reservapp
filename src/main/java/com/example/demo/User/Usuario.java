package com.example.demo.User;


public class Usuario {
    private String nombre;
    private String contrasena;

    private int adminCliente;

    public Usuario(String nombre,String contrasena, int adminCliente){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.adminCliente = adminCliente;
    }
    public Usuario(String nombre,String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }


    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setAdminCliente(int adminCliente){
        this.adminCliente = adminCliente;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getContrasena(){
        return contrasena;
    }
    public int getAdminCliente(){
        return adminCliente;
    }

}
