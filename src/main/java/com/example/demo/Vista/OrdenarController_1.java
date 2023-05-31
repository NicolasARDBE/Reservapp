package com.example.demo.Vista;

import com.example.demo.BuscarBaseDeDatos.BuscarEnBDD;
import com.example.demo.BuscarBaseDeDatos.RegistrarRestaurantes;
import com.example.demo.GuardadoBaseDeDatos.CrearPedido;
import com.example.demo.User.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class OrdenarController_1 {
    @FXML
    private ChoiceBox<String> op1Retaurante;
    @FXML
    private ChoiceBox <Integer> op1Mesa;
    @FXML
    private ChoiceBox <String> op1Menu;
    @FXML
    private Button op1BotonCancelar;
    @FXML
    private Button op1BotonIrPagar;
    @FXML
    private TextArea op1TextField;
    public static String menu_seleccionado;
    BuscarEnBDD b = new BuscarEnBDD();
    @FXML
    public void initialize() throws SQLException {

        ObservableList<String> nombresRestaurantes = FXCollections.observableArrayList();

        RegistrarRestaurantes r = new RegistrarRestaurantes();
        r.almacenarRestaurantes();
        r.almacenarMenus();
        r.almacenarMesas();
        r.anadirRestaurante();
        for(int i=0; i<r.getrestaurantes().size(); i++){
            nombresRestaurantes.add(r.getrestaurantes().get(i).getNombre());
        }

        op1Retaurante.setItems(nombresRestaurantes);

        op1Retaurante.setOnAction(event -> {
            // Obtener la categoría seleccionada
            String restauranteSeleccionado = op1Retaurante.getValue();

            // Cargar opciones del segundo ChoiceBox
            ObservableList<Integer> idMesas = FXCollections.observableArrayList();
            for(int i=0; i<r.getrestaurantes().size(); i++){
                if(restauranteSeleccionado.equals(r.getrestaurantes().get(i).getNombre())){
                    for(int j=0; j<r.getrestaurantes().get(i).getMesas().size(); j++){
                        idMesas.add(r.getrestaurantes().get(i).getMesas().get(j).getIdMesa());
                    }
                }
            }
            op1Mesa.setItems(idMesas);

            String espacio = " ";

            ObservableList<String> nombresMenus = FXCollections.observableArrayList();
            for(int i=0; i<r.getrestaurantes().size(); i++){
                if(restauranteSeleccionado.equals(r.getrestaurantes().get(i).getNombre())){
                    for(int j=0; j<r.getrestaurantes().get(i).getMenus().size(); j++){
                        String concatenada;
                        if(r.getrestaurantes().get(i).getMenus().get(j).getNombre() == null){
                            concatenada = " ".concat(String.valueOf(r.getrestaurantes().get(i).getMenus().get(j).getIdMenu()));
                        } else{
                            concatenada = r.getrestaurantes().get(i).getMenus().get(j).getNombre().concat(espacio).concat(String.valueOf(r.getrestaurantes().get(i).getMenus().get(j).getIdMenu()));
                        }
                        nombresMenus.add(concatenada);
                    }
                }
            }
            op1Menu.setItems(nombresMenus);
            //String menuSeleccionado = op1Menu.getValue();
        });


        op1Menu.setOnAction(event -> {
            menu_seleccionado=op1Menu.getValue();
            // Actualizar el contenido del TextArea según la opción seleccionada
            try {
                op1TextField.setText("El precio de "+menu_seleccionado+" es: "+ b.buscarMenuPorId(getMenu()).getPrecio());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        op1BotonIrPagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(excepcionesPantalla() == true){
                    String[] palabras = menu_seleccionado.split("\\s+");
                    String idMenu = palabras[palabras.length - 1];
                    System.out.println("Hola: " + menu_seleccionado);
                    CrearPedido crear = new CrearPedido();

                    LoginController l = new LoginController();
                    try {
                        crear.guardarPedido(b.numPedidos()+1, l.getUsuario(), b.buscarMenuPorId(Integer.parseInt(idMenu)));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/com/example/demo/pagar.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene homeScreenScene = new Scene(root);
                    Stage window = (Stage) op1Menu.getScene().getWindow();
                    window.setScene(homeScreenScene);
                    window.show();
                }
            }
        });

        op1BotonCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/example/demo/pantallaLogin.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScreenScene = new Scene(root);
                Stage window = (Stage) op1Menu.getScene().getWindow();
                window.setScene(homeScreenScene);
                window.show();
            }
        });

    }

    public int getMenu(){
        String[] palabras = menu_seleccionado.split("\\s+");
        String idMenu = palabras[palabras.length - 1];
        System.out.println("mi clase" + menu_seleccionado);
        return Integer.parseInt(idMenu);
    }

    public void setMenu(String menu_seleccionado){
        this.menu_seleccionado.equals(menu_seleccionado);
    }

    public boolean excepcionesPantalla(){
        if(op1Retaurante.getValue() == null){
            op1TextField.setText("El campo Restaurante es requerido");
            return false;
        } else if(op1Menu.getValue()==null){
            op1TextField.setText("El campo Menu es requerido");
            return false;
        }
        return true;
    }
}
