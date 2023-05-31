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
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resgistro exitoso");
            alert.setHeaderText(null);
            alert.setContentText("Se ha creado correctamente su usuario");
            alert.showAndWait();*/
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar el administrador en la base de datos: " + e.getMessage());
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ocurrió un error al guardar el objeto Administrador");
            alert.showAndWait();*/
            return false;
        }
    }
}
