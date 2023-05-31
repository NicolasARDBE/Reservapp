package com.example.demo.GuardadoBaseDeDatos;
import java.sql.PreparedStatement;

import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;
import com.example.demo.OperacionesBDD.CrearConexion;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PuenteCliente implements IGuardarCliente{
    private CrearConexion conexion;

    public PuenteCliente() throws SQLException {
        this.conexion = new CrearConexion();
        this.conexion.conectar();
    }



    @Override
    public boolean guardarCliente(Cliente client) throws SQLException{

            try {
                PreparedStatement conexion_a_la_base = conexion.getConnection().prepareStatement("INSERT INTO usuario(nombreusuario, contrasenia,adminCliente)VALUES (?,?,?)");
                conexion_a_la_base.setString(1, client.getNombre());
                conexion_a_la_base.setString(2, client.getContrasena());
                conexion_a_la_base.setString(3, "1");
                conexion_a_la_base.executeUpdate();
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resgistro exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Se ha creado correctamente su usuario");
                alert.showAndWait();*/
                return true;
            } catch (SQLException e) {
                System.out.println("Error al guardar el cliente en la base de datos: " + e.getMessage());
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al guardar el objeto Cliente en la base de datos");
                alert.showAndWait();*/
                return false;
            }
    }
    @Override
    public double retornarPreciomenu(int idMenus){
        double precio = 0.0;
        try {

            Statement statement = conexion.conectar().createStatement();

            // Consulta SQL para buscar el precio de un menú
            String sql = "SELECT precio FROM MENU WHERE idMenu = " + idMenus;

            ResultSet resultSet = statement.executeQuery(sql);

            // Obtención del precio del menú
            if (resultSet.next()) {
                precio = resultSet.getDouble("precio");
            } else {
                System.out.println("No se encontró el menú");
            }

            // Cierre de la conexión
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }

    @Override
    public double retornarSaldo(String nomUsuario, int adminCliente) {
        double saldo = 0.0;
        try {

            Statement statement = conexion.conectar().createStatement();

            // Consulta SQL para buscar el precio de un menú
            String sql = "SELECT SALDO FROM TARJETACREDITO WHERE USUARIO_NOMBREUSUARIO = " +'\''+nomUsuario +'\''+ " AND " + "USUARIO_ADMINCLIENTE = " + adminCliente;

            ResultSet resultSet = statement.executeQuery(sql);

            // Obtención del precio del menú
            if (resultSet.next()) {
                saldo = resultSet.getDouble("saldo");
            } else {
                System.out.println("No se encontró la tarjeta");
            }

            // Cierre de la conexión
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;

    }

    @Override
    public void quitarsaldo(double precio,String nombre,String contrasena,String tipo){
        try {

            Statement statement = conexion.conectar().createStatement();


            String sql = "SELECT saldo FROM USUARIO, TARJETACREDITO  WHERE usuario.nombreusuario = TarjetaCredito.usuario_nombreusuario AND usuario.nombreusuario = " +  "'"+  nombre +"'" +" AND usuario.contrasenia = " + "'"+contrasena+"'";

            ResultSet resultado = statement.executeQuery(sql);

            if (resultado.next()) {
                double saldoActual = resultado.getDouble("saldo");
                double saldoNuevo = saldoActual - precio;
                System.out.println("El saldo nuevo es: " + saldoNuevo);

                String updateQuery = "UPDATE TarjetaCredito " +
                        "SET saldo = ? " +
                        "WHERE usuario_nombreUsuario = (SELECT nombreusuario FROM usuario " +
                        "WHERE nombreusuario = ? " +
                        "AND contrasenia = ? " +
                        "AND admincliente = ?)";
                PreparedStatement updateStmt = conexion.prepareStatement(updateQuery);
                updateStmt.setDouble(1, saldoNuevo);
                updateStmt.setString(2, nombre);
                updateStmt.setString(3, contrasena);
                updateStmt.setString(4, tipo);
                updateStmt.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transaccion exitosa");
                alert.setHeaderText(null);
                alert.setContentText("Se ha completado la transaccion de manera exitosa su nuevo saldo es: "+saldoNuevo);
                alert.showAndWait();
            } else {
                System.out.println("El usuario no existe o la contraseña es incorrecta.");
            }

            // Cierre de la conexión
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al realizar la transaccion");
            alert.showAndWait();
        }

    }

}
