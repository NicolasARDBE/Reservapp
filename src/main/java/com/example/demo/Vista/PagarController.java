package com.example.demo.Vista;


import com.example.demo.BuscarBaseDeDatos.IniciarSesionBDD;


import com.example.demo.Restaurante.Orden;
import com.example.demo.Vista.OrdenarController_1;

import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.User.Usuario;
import javafx.event.EventHandler;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

    import java.io.IOException;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.event.ActionEvent;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.sql.SQLException;

    public class PagarController {

        private int contador = 0;

        OrdenarController_1 ordenar;
        LoginController sesion;
        static PuenteCliente puente;
        Usuario user;

        @FXML
        private TextArea textAreaE;
        @FXML
        private Button aceptar;
        @FXML
        private Button cancelar;
        @FXML
        private Label labelPrecio;
        @FXML
        private Label labelSaldo;

        @FXML
        public void initialize() throws SQLException {
            ordenar = new OrdenarController_1();
            sesion = new LoginController();
            puente = new PuenteCliente();
            user = sesion.getUsuario();
            labelSaldo.setText(Double.toString(puente.retornarSaldo(user.getNombre(), user.getAdminCliente())));
            labelPrecio.setText(Double.toString(puente.retornarPreciomenu(ordenar.getMenu())));
            aceptar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    clickenelboton();
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

                int numero_de_menu = ordenar.getMenu();

                double precio = puente.retornarPreciomenu(numero_de_menu);
                System.out.println("este es el precio "+precio);
                //Verificación de que el usuario pueda pagar y no quedar en negativo
                if(puente.retornarSaldo(user.getNombre(), user.getAdminCliente()) - puente.retornarPreciomenu(numero_de_menu) < 0){
                    textAreaE.setText("Saldo insuficiente. No se pudo procesar el pago.");
                } else{
                    puente.quitarsaldo(precio, user.getNombre(), user.getContrasena(), "1");
                }
            }
        }
