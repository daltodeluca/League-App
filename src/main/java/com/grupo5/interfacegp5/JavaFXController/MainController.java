package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

public class MainController {

    @FXML
    private Label Titulo;

    @FXML
    protected void exitButton(ActionEvent Event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Tem a certeza que pretende sair?");

        ButtonType bsim = new ButtonType("Sim");
        ButtonType bnao = new ButtonType("Não");
        alert.getButtonTypes().setAll(bsim, bnao);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == bsim){
            Platform.exit();
        } else if (result.get() == bnao) {

        }
    }

    @FXML
    protected void admButton(ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-adm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1105, 712);
        stage.setTitle("Menu  Administrador");
        stage.setScene(scene);
        stage.show();
        ((Stage) Titulo.getScene().getWindow()).close();
    }

    @FXML
    protected void opButton(ActionEvent Event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-op.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1105, 712);
        stage.setTitle("Menu Operador");
        stage.setScene(scene);
        stage.show();
        ((Stage) Titulo.getScene().getWindow()).close();

    }

    @FXML
    protected void MultButton(ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-mil.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1105, 712);
        stage.setTitle("Menu Mulionários");
        stage.setScene(scene);
        stage.show();
        ((Stage) Titulo.getScene().getWindow()).close();
    }


}
