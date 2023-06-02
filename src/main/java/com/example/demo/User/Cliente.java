package com.example.demo.User;

import com.example.demo.Restaurante.Menu;

import java.util.Map;

public class Cliente extends Usuario{

    private TarjetadeCredito tarjetaCredito;

    public Cliente(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String contrasena, int adminCliente) {
        super(nombre, contrasena, adminCliente);
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
