package com.example.demo.User;

import com.example.demo.Restaurante.Restaurante;
import com.example.demo.User.Usuario;

public class Administrador extends Usuario {

    Restaurante restaurante;
    
    public Administrador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }
    public Administrador(String nombre, String contrasena, int adminCliente) {
        super(nombre, contrasena, adminCliente);
    }

    public Administrador() {
        super();
    }

    public String getNombre(){
        return super.getNombre();
    }

    public String getContrasena(){
        return super.getContrasena();
    }

    public int getAdminCliente(){
        return super.getAdminCliente();
    }

}
