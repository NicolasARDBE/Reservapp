package com.example.demo.GuardadoBaseDeDatos;

import com.example.demo.User.Cliente;

import java.sql.SQLException;

public interface    IGuardarCliente {

    public boolean guardarCliente(Cliente cliente)throws SQLException;

    public double retornarPreciomenu(int idMenus);

    public double retornarSaldo(String nomUsuario, int adminCliente);

    public boolean quitarsaldo(double precio, String nombre, String contrasena, String tipo);

    public void aumentarsaldo(double precio,String nombre,String contrasena,String tipo);
}