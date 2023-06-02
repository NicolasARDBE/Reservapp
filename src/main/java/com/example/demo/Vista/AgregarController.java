package com.example.demo.Vista;

import com.example.demo.GuardadoBaseDeDatos.AnadirMenu;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.Restaurante.Restaurante;
import com.example.demo.User.Administrador;
import com.example.demo.Restaurante.Menu;
import com.example.demo.User.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class AgregarController{

    LoginController sesion;
    static PuenteCliente puente;
    Usuario user;

    @FXML
    private TextField campo;

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;


    @FXML
    public void initialize() throws SQLException {


        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                clickenelboton();
            }
        });
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaInicio.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) aceptar.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();

            }
        });
    }

    private void clickenelboton(){
        //Verificación de que el usuario pueda pagar y no quedar en negativo
        String saldoStr = campo.getText();
        double saldo = Double.parseDouble(saldoStr);
//hola
        puente.aumentarsaldo(saldo, user.getNombre(), user.getContrasena(), "1");
    }
}
