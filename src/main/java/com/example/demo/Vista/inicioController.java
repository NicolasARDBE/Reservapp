package com.example.demo.Vista;

import com.example.demo.BuscarBaseDeDatos.BuscarEnBDD;
import com.example.demo.BuscarBaseDeDatos.RegistrarRestaurantes;
import com.example.demo.GuardadoBaseDeDatos.CrearPedido;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;
import com.example.demo.User.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.demo.BuscarBaseDeDatos.IniciarSesionBDD;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class inicioController {
    static PuenteCliente puente;
    LoginController login = new LoginController();
    BuscarEnBDD b = new BuscarEnBDD();
    PagarController p = new PagarController();
    OrdenarController_1 o = new OrdenarController_1();

    @FXML
    private Label labelNombre;
    @FXML
    private Label labelSaldo;
    @FXML
    private Button botonPedido;
    @FXML
    private Button botonSalir;
    @FXML
    private TextArea areaInicio;
    @FXML
    private Button botonPagar;
    @FXML
    private Label labelPagar;

    @FXML
    public void initialize() throws SQLException {
        puente = new PuenteCliente();
        labelNombre.setText(login.getUsuario().getNombre());
        labelSaldo.setText(String.valueOf(puente.retornarSaldo(login.getUsuario().getNombre(), login.getUsuario().getAdminCliente())));

        botonPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaOrdenarPedido1.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) botonPedido.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });

        if(OrdenarController_1.menu_seleccionado != null){
            labelPagar.setText(String.valueOf(puente.retornarPreciomenu(o.getMenu())));
            botonPagar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/com/example/demo/pagar.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene homeScreenScene = new Scene(root);
                    Stage window = (Stage) botonPedido.getScene().getWindow();
                    window.setScene(homeScreenScene);
                    window.show();
                }
            });
        } else{
            areaInicio.setText("No puede proceder a pagar si no ha ordenado un pedido");
        }

        botonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaLogin.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) botonSalir.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });
    }
}

