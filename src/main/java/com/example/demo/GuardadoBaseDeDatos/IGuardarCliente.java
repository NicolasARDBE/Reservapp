package com.example.demo.GuardadoBaseDeDatos;

import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;

import java.sql.SQLException;

public interface IGuardarCliente {

    public boolean guardarCliente(Cliente cliente)throws SQLException;

    public double retornarPreciomenu(int idMenus);

    public double retornarSaldo(String nomUsuario, int adminCliente);

    public void quitarsaldo(double precio,String nombre,String contrasena,String tipo);
}