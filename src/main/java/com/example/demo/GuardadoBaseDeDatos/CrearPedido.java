package com.example.demo.GuardadoBaseDeDatos;

import com.example.demo.OperacionesBDD.CrearConexion;
import com.example.demo.Restaurante.Menu;
import com.example.demo.Restaurante.Orden;
import com.example.demo.User.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearPedido {

    private CrearConexion conexion;
    private Orden orden;

    public String guardarPedido(int idOrden, Usuario cliente, Menu menu) throws SQLException{
        conexion = new CrearConexion();
        conexion.conectar();
        try {
            PreparedStatement conexion_a_la_base = conexion.getConnection().prepareStatement("INSERT INTO orden(idorden, usuario_nombreusuario,usuario_admincliente,menu_idmenu,MENU_RESTAURANTE_IDRESTAURANTE)VALUES (?,?,?,?,?)");
            conexion_a_la_base.setString(1, String.valueOf(idOrden));
            conexion_a_la_base.setString(2, cliente.getNombre());
            conexion_a_la_base.setString(3, "1");
            conexion_a_la_base.setString(4, String.valueOf(menu.getIdMenu()));
            conexion_a_la_base.setString(5, String.valueOf(menu.getRestaurante().getIdRestaurante()));
            conexion_a_la_base.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el pedido en la base de datos: " + e.getMessage());
            SQLException sqlEx = e;
            // Obtenemos el mensaje de la excepción como una cadena
            String errorMessage = sqlEx.getMessage();
            // Devolvemos el mensaje de la excepción como una cadena
            return "Error al guardar el pedido en la base de datos: ";// + e.getMessage();
        }
        conexion.desconectar();
        return "Se ha procesado el pedido exitosamente";
    }
}
