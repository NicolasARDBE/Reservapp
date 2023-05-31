package com.example.demo.BuscarBaseDeDatos;

import com.example.demo.OperacionesBDD.CrearConexion;
import com.example.demo.Restaurante.Menu;
import com.example.demo.Restaurante.Mesa;
import com.example.demo.Restaurante.Restaurante;
import com.example.demo.User.Administrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegistrarRestaurantes {
    private ArrayList<Restaurante> restaurantes;
    private CrearConexion con;
    Restaurante restaurante;
    public RegistrarRestaurantes() {
        restaurante = new Restaurante();
        restaurantes = new ArrayList<>();
    }

    public void almacenarRestaurantes() throws SQLException {
        int aux;
        con = new CrearConexion();
        Administrador admin = new Administrador();
        Statement statement = con.conectar().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM RESTAURANTE");
        while (resultSet.next()) {
            //Vincular el admin que está en la BDD con el restaurante
            IniciarSesionBDD i = new IniciarSesionBDD();
            restaurante.setNombre(resultSet.getString("NOMBRERESTAURANTE"));
            restaurante.setIdRestaurante(resultSet.getInt("IDRESTAURANTE"));
            admin.setNombre(resultSet.getString("USUARIO_NOMBREUSUARIO"));
            aux = resultSet.getInt("USUARIO_ADMINCLIENTE");
            restaurante.setAdminRestaurante((Administrador) i.retornarUsuario(admin));
        }
        con.desconectar();
    }

    public void almacenarMenus() throws SQLException {
        int aux;
        int cont = 0;
        con = new CrearConexion();
        Statement statement = con.conectar().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM MENU");
        while (resultSet.next()) {
            Menu menu = new Menu();
            //Vincular el admin que está en la BDD con el restaurante
            IniciarSesionBDD i = new IniciarSesionBDD();
            restaurante.getMenus().add(menu);
            restaurante.getMenus().get(cont).setPrecio(resultSet.getDouble("PRECIO"));
            restaurante.getMenus().get(cont).setIdMenu(resultSet.getInt("IDMENU"));
            aux = resultSet.getInt("RESTAURANTE_IDRESTAURANTE");
            restaurante.getMenus().get(cont).setNombre(resultSet.getString("NOMBREMENU"));

            cont++;
        }
        con.desconectar();
    }
    public void almacenarMesas() throws SQLException {
        int cont = 0;
        int aux;
        con = new CrearConexion();
        Statement statement = con.conectar().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM MESA");
        while (resultSet.next()) {
            Mesa mesa = new Mesa();
            restaurante.getMesas().add(mesa);
            restaurante.getMesas().get(cont).setIdMesa((resultSet.getInt("IDMESA")));
            aux = resultSet.getInt("OCUPADA");
            if(aux == 1){
                restaurante.getMesas().get(cont).setOcupada(true);
            } else{
                restaurante.getMesas().get(cont).setOcupada(false);
            }
            restaurante.getMesas().get(cont).setAsientos((resultSet.getInt("ASIENTOS")));
            aux = resultSet.getInt("RESTAURANTE_IDRESTAURANTE");
            cont++;
        }
        con.desconectar();
    }

    public void anadirRestaurante(){
        restaurantes.add(restaurante);
    }

    public ArrayList<Restaurante> getrestaurantes(){
        return restaurantes;
    }

}