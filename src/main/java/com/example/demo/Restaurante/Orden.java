package com.example.demo.Restaurante;

import com.example.demo.User.Cliente;

public class Orden {
    int id;
    Cliente cliente;
    Menu menu;

    public Orden(){
        cliente = new Cliente();
        menu = new Menu();
    }



}
