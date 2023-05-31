package com.example.demo.Restaurante;

import com.example.demo.User.Administrador;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restaurante {
    String nombre;
    int idRestaurante;
    Administrador adminRestaurante;
    ArrayList<Mesa> mesas;
    ArrayList<Menu> menus;

    public Restaurante(){
        mesas = new ArrayList<>();
        adminRestaurante = new Administrador();
        menus = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Administrador getAdminRestaurante() {
        return adminRestaurante;
    }

    public void setAdminRestaurante(Administrador adminRestaurante) {
        this.adminRestaurante = adminRestaurante;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }
    public void setMenus(ArrayList<Menu> menus){
        this.menus = menus;
    }
    public ArrayList<Mesa> getMesas() {
        return mesas;
    }
    public void serMesas(ArrayList<Mesa> mesas){
        this.mesas = mesas;
    }
}