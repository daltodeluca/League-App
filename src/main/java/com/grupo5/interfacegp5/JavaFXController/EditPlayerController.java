package com.grupo5.interfacegp5.JavaFXController;


import com.grupo5.interfacegp5.Controller.JogadorDAO;
import com.grupo5.interfacegp5.Model.Jogador;
import com.grupo5.interfacegp5.Util.DBConnect;
import com.grupo5.interfacegp5.Various.EditPlayer;
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

public class EditPlayerController implements Initializable {
    @FXML
    private static Jogador p;
    @FXML
    public Label Titulo;
    @FXML
    public TextField idField;
    @FXML
    public TextField nacionalidadeField;
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
        int jogadorId = Integer.parseInt(idField.getText()), jogadorTelefone = Integer.parseInt(telefoneField.getText()), jogadorIban = Integer.parseInt(ibanField.getText());
        String jogadorNome = fullNameField.getText(), jogadorNascimento = nascimentoField.getText(), jogadorEmail = emailField.getText(), jogadorCC = CCField.getText(), jogadorNacionalidade = nacionalidadeField.getText();
        // update skl
        try {
            Connection conn = DBConnect.getConnectionMySQL();
            PreparedStatement st = conn.prepareStatement("UPDATE Jogador SET Nome = ?, DataNascimento = ?, Telefone = ?," +
                    " Email = ?, CC = ?, Iban = ?, Nacionalidade = ? WHERE Id_Jogador = ?");
            st.setString(1, jogadorNome);
            st.setString(2, jogadorNascimento);
            st.setInt(3, jogadorTelefone);
            st.setString(4, jogadorEmail);
            st.setString(5, jogadorCC);
            st.setInt(6, jogadorIban);
            st.setString(7, jogadorNacionalidade);
            st.setInt(8, jogadorId);

            st.executeUpdate();
            st.close();
        } catch (
                SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Stage) Titulo.getScene().getWindow()).close();
    }

    public static Jogador getP() {
        return p;
    }

    public static void setP(Jogador p) {
        EditPlayerController.p = p;
    }

    public void atualizarPlayerData() {
        JogadorDAO editDao = new JogadorDAO();
        int jogadorId = Integer.parseInt(idField.getText());
        String jogadorName = fullNameField.getText(), jogadorNascimento = nascimentoField.getText(), jogadorTelefone = telefoneField.getText(), jogadorEmail = emailField.getText(), jogadorCC = CCField.getText(), jogadorIban = ibanField.getText(), jogadorNacionalidade = nacionalidadeField.getText();
        // Utilizador u = new Utilizador(id, userNome, userNascimento, userTelefone, userEmail, userCC, userIban, userCode, userAcessLevel);

    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        initUser();
    }

    public void initUser() {
        idField.setText(String.valueOf(p.getJogadorID()));
        fullNameField.setText(p.getJogadorName());
        nascimentoField.setText(p.getJogadorNascimento());
        telefoneField.setText(String.valueOf(p.getJogadorTelefone()));
        emailField.setText(p.getJogadorEmail());
        CCField.setText(p.getJogadorCC());
        ibanField.setText(String.valueOf(p.getJogadorIban()));
        nacionalidadeField.setText(p.getJogadorNacionalidade());

    }

    public void onCancelButton(ActionEvent actionEvent) {
        cancelarEdit();
    }

    private void cancelarEdit() {
        ((Stage) Titulo.getScene().getWindow()).close();
    }
}