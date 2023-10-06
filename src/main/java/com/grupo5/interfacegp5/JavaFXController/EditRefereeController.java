package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.Controller.ArbitroDAO;
import com.grupo5.interfacegp5.Model.Arbitro;
import com.grupo5.interfacegp5.Model.Arbitro;
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

public class EditRefereeController implements Initializable {
    @FXML
    private static Arbitro a;
    @FXML
    public Label Titulo;
    @FXML
    public TextField idField;
    @FXML
    private TextField CCField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField ibanField;
    @FXML
    private TextField nascimentoField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField telefoneField;

    @FXML
    public void edit(ActionEvent event) {
        // método update
        int arbitroId = Integer.parseInt(idField.getText()), arbitroTelefone = Integer.parseInt(telefoneField.getText()), arbitroIban = Integer.parseInt(ibanField.getText());
        String arbitroNome = fullNameField.getText(), arbitroNascimento = nascimentoField.getText(), arbitroEmail = emailField.getText(), arbitroCC = CCField.getText();
        // update skl
        try {
            Connection conn = DBConnect.getConnectionMySQL();
            PreparedStatement st = conn.prepareStatement("UPDATE Arbitro SET Nome = ?, DataNascimento = ?, Telefone = ?," +
                    " Email = ?, CC = ?, Iban = ? WHERE Id_Arbitro = ?");
            st.setString(1, arbitroNome);
            st.setString(2, arbitroNascimento);
            st.setInt(3, arbitroTelefone);
            st.setString(4, arbitroEmail);
            st.setString(5, arbitroCC);
            st.setInt(6, arbitroIban);
            st.setInt(7, arbitroId);

            st.executeUpdate();
            st.close();
        } catch (
                SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Stage) Titulo.getScene().getWindow()).close();
    }

    public static Arbitro getA() {
        return a;
    }

    public static void setA(Arbitro a) {
        EditRefereeController.a = a;
    }

    public void atualizarPlayerData() {
        ArbitroDAO editDao = new ArbitroDAO();
        int arbitroId = Integer.parseInt(idField.getText());
        String arbitroName = fullNameField.getText(), arbitroNascimento = nascimentoField.getText(), arbitroTelefone = telefoneField.getText(), arbitroEmail = emailField.getText(), arbitroCC = CCField.getText(), arbitroIban = ibanField.getText();
        // Utilizador u = new Utilizador(id, userNome, userNascimento, userTelefone, userEmail, userCC, userIban, userCode, userAcessLevel);

    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        initUser();
    }

    public void initUser() {
        idField.setText(String.valueOf(a.getArbitroID()));
        fullNameField.setText(a.getArbitroName());
        nascimentoField.setText(a.getArbitroNascimento());
        telefoneField.setText(String.valueOf(a.getArbitroTelefone()));
        emailField.setText(a.getArbitroEmail());
        CCField.setText(a.getArbitroCC());
        ibanField.setText(String.valueOf(a.getArbitroIban()));

    }

    public void onCancelButton(ActionEvent actionEvent) {
        cancelarEdit();
    }

    private void cancelarEdit() {
        ((Stage) Titulo.getScene().getWindow()).close();
    }

}
