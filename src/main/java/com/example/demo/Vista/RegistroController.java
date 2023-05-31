/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
*/

package com.example.demo.Vista;


import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;
import javafx.event.EventHandler;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class RegistroController {
    @FXML
    private TextField digitar_usuario;

    @FXML
    private TextField digitar_contraseña;
    @FXML
    private Button registro_aceptar;

    @FXML
    private Button registro_cancelar;

    @FXML
    private RadioButton radioCliente;

    @FXML
    private RadioButton radioAdmin;
    ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        radioCliente.setToggleGroup(toggleGroup);
        radioAdmin.setToggleGroup(toggleGroup);

        registro_aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickenelboton();
            }
        });

        registro_cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaLogin.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) digitar_usuario.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });
    }

    

    private void clickenelboton(){

        if(radioCliente.isSelected()==false && radioAdmin.isSelected()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar si eres cliente o admin");
            alert.showAndWait();
        }

        if(digitar_usuario.getText().isEmpty() && digitar_contraseña.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo usuario y contraseña requerido");
            alert.showAndWait();
        }
        else if(digitar_contraseña.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo contraseña requerido");
            alert.showAndWait();
        }
        else if(digitar_usuario.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo usuario requerido");
            alert.showAndWait();
        }

        else if(radioAdmin.isSelected()){
            String nombre_usuario = digitar_usuario.getText();
            String la_contrasena = digitar_contraseña.getText();
            Administrador admin = new Administrador(nombre_usuario, la_contrasena);
            try {
                PuenteAdmin puente = new PuenteAdmin();
                if(puente.guardarAdministrador(admin)==true){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resgistro exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Se ha creado correctamente su usuario");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ocurrió un error al guardar el objeto Administrador");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al guardar el objeto Administrador en la base de datos");
                alert.showAndWait();
                e.printStackTrace();
            }
            catch (Exception e) {

                e.printStackTrace();
            }
        }
        else if(radioCliente.isSelected()){
            String nombre_usuario = digitar_usuario.getText();
            String la_contrasena = digitar_contraseña.getText();
            Cliente client = new Cliente(nombre_usuario,la_contrasena);
            try {
                PuenteCliente puente = new PuenteCliente();
                if(puente.guardarCliente(client)==true){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resgistro exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Se ha creado correctamente su usuario");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al guardar el objeto Cliente en la base de datos");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al guardar el objeto Cliente en la base de datos");
                alert.showAndWait();
                e.printStackTrace();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ocurrió un error al guardar el objeto Cliente");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
}
