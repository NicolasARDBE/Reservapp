package com.example.demo;

import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.User.Administrador;
import com.example.demo.User.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import com.example.demo.BuscarBaseDeDatos.IniciarSesionBDD;

public class Main extends Application {

    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InterfazRegistro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Registrarse");
        stage.setScene(scene);
        stage.show();
    }*/

        /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pantallaOrdenarPedido1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("OrdenarPedido");
        stage.setScene(scene);
        stage.show();
    }*/
    /*
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pagar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Pagar");
        stage.setScene(scene);
        stage.show();
    }*/



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pantallaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Iniciar Sesion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        /*try {
        Administrador admin = new Administrador("Juanito","megustas");
        Administrador admin2 = new Administrador("Juanito","meg");

        PuenteAdmin puente = new PuenteAdmin();
        puente.guardarAdministrador(admin);
        }catch(SQLException e){
            System.out.println("Error");
        }
        */
        launch();
    }
}