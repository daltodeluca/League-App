package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.Controller.ClubeDAO;
import com.grupo5.interfacegp5.Model.Clube;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditTeamController implements Initializable {
    @FXML
    private static Clube c;
    @FXML
    public Label Titulo;
    @FXML
    public TextField idField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField localField;
    @FXML
    private Button submitButton;

    @FXML
    public void edit(ActionEvent event) {
        // método update
        int clubeId = Integer.parseInt(idField.getText());
        String clubeNome = fullNameField.getText(), clubeLocal = localField.getText();
        // update skl
        try {
            Connection conn = DBConnect.getConnectionMySQL();
            PreparedStatement st = conn.prepareStatement("UPDATE Clube SET Nome = ?, Id_Local = ? WHERE Id_Clube = ?");
            st.setString(1, clubeNome);
            st.setString(2, clubeLocal);
            st.setInt(3, clubeId);

            st.executeUpdate();
            st.close();
        } catch (
                SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Stage) Titulo.getScene().getWindow()).close();
    }

    public static Clube getC() {
        return c;
    }

    public static void setC(Clube c) {
        EditTeamController.c = c;
    }

    public void atualizarPlayerData() {
        ClubeDAO editDao = new ClubeDAO();
        int clubeId = Integer.parseInt(idField.getText());
        String clubeName = fullNameField.getText(), clubeLocal = localField.getText();
        // Utilizador u = new Utilizador(id, userNome, userNascimento, userTelefone, userEmail, userCC, userIban, userCode, userAcessLevel);

    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        initUser();
    }

    public void initUser() {
        idField.setText(String.valueOf(c.getClubeID()));
        fullNameField.setText(c.getClubeNome());
        localField.setText(String.valueOf(c.getClubeLocal()));

    }

    public void onCancelButton(ActionEvent actionEvent) {
        cancelarEdit();
    }

    private void cancelarEdit() {
        ((Stage) Titulo.getScene().getWindow()).close();
    }

}
