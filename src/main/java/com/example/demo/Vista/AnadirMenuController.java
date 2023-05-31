package com.example.demo.Vista;

import com.example.demo.GuardadoBaseDeDatos.AnadirMenu;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.Restaurante.Restaurante;
import com.example.demo.User.Administrador;
import com.example.demo.Restaurante.Menu;
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

public class AnadirMenuController {

    @FXML
    private TextField anNombre;

    @FXML
    private TextField anPrecio;

    @FXML
    private TextField anIdMenu;

    @FXML
    private TextField anIdRestaurante;

    @FXML
    private TextArea anTextoAvisos;

    @FXML
    private Button anCancelar;

    @FXML
    private Button anAnadir;


    @FXML
    public void initialize() {
        anAnadir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clickAnadir();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        anCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaLogin.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) anNombre.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });
    }
    public void clickAnadir() throws SQLException {
        if(excepcionesPantalla()==true){
            Restaurante r = new Restaurante();
            r.setIdRestaurante(Integer.parseInt(anIdRestaurante.getText()));
            System.out.println(anNombre.getText());
            Menu menu = new Menu(anNombre.getText()
                    , Double.parseDouble(anPrecio.getText())
                    ,Integer.parseInt(anIdMenu.getText()));

            System.out.println(menu.getNombre());
            menu.setRestaurante(r);
            AnadirMenu anadir = new AnadirMenu();
            anTextoAvisos.setText(anadir.guardarMenu(menu));
        }
    }

    public boolean excepcionesPantalla(){
        if(anNombre.getText().isEmpty()){
            anTextoAvisos.setText("El campo Nombre es requerido");
            return false;
        } else if(anPrecio.getText().isEmpty()){
            anTextoAvisos.setText("El campo Precio es requerido");
        } else if(anIdMenu.getText().isEmpty()){
            anTextoAvisos.setText("El campo ID Menu es requerido");
        } else if(anIdRestaurante.getText().isEmpty()){
            anTextoAvisos.setText("El campo ID Restaurante es requerido");
        }
        return true;
    }

}
