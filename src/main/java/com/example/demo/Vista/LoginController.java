package com.example.demo.Vista;

import com.example.demo.BuscarBaseDeDatos.BuscarEnBDD;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;
import com.example.demo.User.Usuario;
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

public class LoginController {

    public static Usuario usuario;
    BuscarEnBDD b = new BuscarEnBDD();
    @FXML
    private Button isBoton;
    @FXML
    private TextField isNomUsuario;
    @FXML
    private PasswordField isContraUsuario;
    @FXML
    private TextArea isCampoErrores;
    @FXML
    private Button isBotonRegistro;
    @FXML
    private RadioButton radioCliente;
    @FXML
    private RadioButton radioAdmin;

    ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        radioCliente.setToggleGroup(toggleGroup);
        radioAdmin.setToggleGroup(toggleGroup);

        isBoton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comprobaciones() == true) {
                    try {
                        if (handleLogin()) {
                            if (usuario.getAdminCliente() == 1) {
                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaInicio.fxml"));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Scene homeScreenScene = new Scene(root);
                                Stage window = (Stage) isNomUsuario.getScene().getWindow();
                                window.setScene(homeScreenScene);
                                window.show();
                            } else{
                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaAnadir.fxml"));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Scene homeScreenScene = new Scene(root);
                                Stage window = (Stage) isNomUsuario.getScene().getWindow();
                                window.setScene(homeScreenScene);
                                window.show();
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        /*if (comprobaciones() == true) {
            try {
                if (handleLogin()) {
                    if (usuario.getAdminCliente() == 1) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaOrdenarPedido1.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene homeScreenScene = new Scene(root);
                        Stage window = (Stage) isNomUsuario.getScene().getWindow();
                        window.setScene(homeScreenScene);
                        window.show();
                    } else{
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaAnadir.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene homeScreenScene = new Scene(root);
                        Stage window = (Stage) isNomUsuario.getScene().getWindow();
                        window.setScene(homeScreenScene);
                        window.show();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/

        isBotonRegistro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/InterfazRegistro.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) isNomUsuario.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });
    }

    public boolean comprobaciones() {

        if(radioCliente.isSelected()==false && radioAdmin.isSelected()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar si eres cliente o admin");

            alert.showAndWait();
            return false;
        } else if (isNomUsuario.getText().isEmpty() && isContraUsuario.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo usuario y contraseña requerido");
            alert.showAndWait();
            return false;
        } else if (isContraUsuario.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo contraseña requerido");
            alert.showAndWait();
            return false;
        } else if (isNomUsuario.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Campo usuario requerido");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    private boolean handleLogin() throws SQLException {
        int aux = 0;
        IniciarSesionBDD i = new IniciarSesionBDD();
        if (radioCliente.isSelected()) {
            aux = 1;
        } else if (radioAdmin.isSelected()) {
            aux = 0;
        }
        usuario = new Usuario(isNomUsuario.getText(), isContraUsuario.getText(), aux);

        try {
            i.ingresarEnMapa();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (i.buscarUsuarioBDD(usuario)) {
            isCampoErrores.setText("Login exitoso");
            return true;
        } else {
            isCampoErrores.setText("Login fallido. La información proporcionada no coincide");
            return false;
        }
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
