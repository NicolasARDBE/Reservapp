package com.example.demo.BuscarBaseDeDatos;

import com.example.demo.OperacionesBDD.CrearConexion;
import com.example.demo.Restaurante.Menu;
import com.example.demo.Restaurante.Restaurante;
import com.example.demo.User.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarEnBDD {
    private CrearConexion con;

    public int numPedidos() throws SQLException {
        con = new CrearConexion();
        Statement statement = con.conectar().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM orden");
        resultSet.next();
        int numPedidos = resultSet.getInt("total");
        resultSet.close();
        statement.close();
        con.desconectar();
        return numPedidos;
    }
    
        public Menu buscarMenuPorId(int id) throws SQLException {
            String sql = "SELECT * FROM menu WHERE idMenu = ?";
            CrearConexion conn = new CrearConexion();
            conn.conectar();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    Restaurante restaurante = new Restaurante();

                    Menu menu = new Menu();
                    menu.setPrecio(rs.getDouble("precio"));
                    menu.setIdMenu(rs.getInt("idmenu"));
                    restaurante.setIdRestaurante(rs.getInt("restaurante_idrestaurante"));
                    menu.setNombre(rs.getString("nombremenu"));
                    // Obtener el restaurante correspondiente y establecerlo en el menú
                    menu.setRestaurante(restaurante);
                    conn.desconectar();
                    return menu;
                } else {
                    conn.desconectar();
                    return null;
                }
            }
        }

        public boolean esCliente(String nomUsuario) throws SQLException {
            con = new CrearConexion();
            Statement statement = con.conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nombreusuario, admincliente FROM usuario");
            int admin = 0;
            while (resultSet.next()) {
                String nomU = resultSet.getString("NOMBREUSUARIO");
                admin = resultSet.getInt("ADMINCLIENTE");
                if (nomUsuario.equals(nomU)) {
                    break;
                }
            }
            resultSet.close();
            statement.close();
            con.desconectar();
            if (admin == 1) {
                return true;
            } else{
                return false;
            }
        }
}
