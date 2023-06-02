package com.example.demo.GuardadoBaseDeDatos;

import com.example.demo.OperacionesBDD.CrearConexion;
import com.example.demo.Restaurante.Menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnadirMenu {
    private CrearConexion conexion;

    public AnadirMenu() throws SQLException {
        this.conexion = CrearConexion.getInstance();
        this.conexion.conectar();
    }
    public String guardarMenu(Menu menu) throws SQLException{
        try {
            PreparedStatement conexion_a_la_base = conexion.getInstance().getConnection().prepareStatement("INSERT INTO menu(precio, idmenu, restaurante_idrestaurante, nombremenu)VALUES (?,?,?,?)");
            conexion_a_la_base.setDouble(1, menu.getPrecio());
            conexion_a_la_base.setInt(2, menu.getIdMenu());
            conexion_a_la_base.setInt(3, menu.getRestaurante().getIdRestaurante());
            conexion_a_la_base.setString(4, menu.getNombre());
            conexion_a_la_base.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el menu en la base de datos: " + e.getMessage());
            SQLException sqlEx = e;
            // Obtenemos el mensaje de la excepción como una cadena
            String errorMessage = sqlEx.getMessage();
            // Devolvemos el mensaje de la excepción como una cadena
            return "Error al guardar el menu en la base de datos: " + e.getMessage();
        }
        return "Se ha ingresado el menu exitosamente";
    }
}
