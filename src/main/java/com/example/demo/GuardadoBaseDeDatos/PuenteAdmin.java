package com.example.demo.GuardadoBaseDeDatos;
import java.sql.PreparedStatement;
import com.example.demo.User.Administrador;
import com.example.demo.OperacionesBDD.CrearConexion;
import javafx.scene.control.Alert;

import java.sql.SQLException;



public class PuenteAdmin implements IGuardarAdmin{
    private CrearConexion conexion;

    public PuenteAdmin() throws SQLException {
        this.conexion = new CrearConexion();
        this.conexion.conectar();
    }

    @Override
    public boolean guardarAdministrador(Administrador admin) throws SQLException{
        try {
            PreparedStatement conexion_a_la_base = conexion.getConnection().prepareStatement("INSERT INTO usuario(nombreusuario, contrasenia,adminCliente)VALUES (?,?,?)");
            conexion_a_la_base.setString(1, admin.getNombre());
            conexion_a_la_base.setString(2, admin.getContrasena());
            conexion_a_la_base.setString(3, "0");
            conexion_a_la_base.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar el administrador en la base de datos: " + e.getMessage());
            return false;
        }
    }
}
