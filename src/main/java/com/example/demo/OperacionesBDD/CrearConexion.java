package com.example.demo.OperacionesBDD;

import java.sql.*;

public class CrearConexion {
    private static CrearConexion instancia;
    private final String user = "c##Nicolas_R";
    private final String password = "12345";
    private final  String url = "jdbc:oracle:thin:@HP_NR:1521:xe";
    private Connection conn;
    public Connection conectar() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    public static synchronized CrearConexion getInstance() {
        if (instancia == null) {
            instancia = new CrearConexion();
        }
        return instancia;
    }

    public void desconectar() {
        try {
            conn.close();
            System.out.println("Desconexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
        }
    }
    public Connection getConnection(){
        return conn;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }
}
