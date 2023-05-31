package com.example.demo.BuscarBaseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.demo.User.Usuario;
import com.example.demo.OperacionesBDD.CrearConexion;

public class IniciarSesionBDD {

    private ArrayList<Usuario> usuariosArray = new ArrayList<>();
    private CrearConexion con;

    public void ingresarEnMapa() throws SQLException {
        int adminCliente;
        con = new CrearConexion();
        Statement statement = con.conectar().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USUARIO");
        String conU = null;
        String nomU = null;
        while (resultSet.next()) {

            nomU = resultSet.getString("NOMBREUSUARIO");
            System.out.println(nomU+"comp1");
            conU = resultSet.getString("CONTRASENIA");
            System.out.println(conU+"comp2");
            adminCliente = resultSet.getInt("ADMINCLIENTE");
            Usuario u = new Usuario(nomU, conU, adminCliente);
            usuariosArray.add(u);
        }
        resultSet.close();
        statement.close();
        con.desconectar();
    }

    public boolean buscarUsuarioBDD(Usuario usuario) {
        String nom;
        String con;

        boolean esta = false;

        for(int i=0; i<usuariosArray.size(); i++){
            if (usuario.getNombre().equals(usuariosArray.get(i).getNombre()) &&
                usuario.getAdminCliente() == usuariosArray.get(i).getAdminCliente() &&
                usuario.getContrasena().equals(usuariosArray.get(i).getContrasena())){
                esta = true;
                break;
            }
        }

        if(esta == true){
            System.out.println("Esta");
            return true;
        } else {
            System.out.println("No Esta");
            return false;
        }
}

    public Usuario retornarUsuario(Usuario usuario){
        Usuario uRetornar = new Usuario();
        String nom;
        String con;
        boolean esta = false;
        for(int i=0; i<usuariosArray.size(); i++){
            nom = usuariosArray.get(i).getNombre();
            if (nom.equals(usuario.getNombre())){
                return uRetornar;
            }
        }
        return null;
    }

}
