package com.example.demo.Restaurante;

public class Menu {
    String nombre;
    double precio;
    int idMenu;
    Restaurante restaurante;

    public Menu(String nombre, double precio, int idMenu){
        this.nombre = nombre;
        this.precio = precio;
        this.idMenu = idMenu;
        restaurante = new Restaurante();
    }
    public Menu(){
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

}
