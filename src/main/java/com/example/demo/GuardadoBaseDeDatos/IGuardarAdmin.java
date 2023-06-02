package com.example.demo.GuardadoBaseDeDatos;

import com.example.demo.User.Administrador;

import java.sql.SQLException;

public interface IGuardarAdmin {

    public boolean guardarAdministrador(Administrador admin)throws SQLException;
}
