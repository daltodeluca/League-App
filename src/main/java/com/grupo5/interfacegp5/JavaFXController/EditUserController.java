package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.Controller.UtilizadorDAO;
import com.grupo5.interfacegp5.Model.Jogador;
import com.grupo5.interfacegp5.Model.Utilizador;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditUserController implements Initializable {

    private static Utilizador u;

    public static Jogador p;



    @FXML
    public Label Titulo;
    @FXML
    public TextField idField;

    @FXML
    private TextField CCField;

    @FXML
    private TextField accessField;

    @FXML
    private TextField codeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField ibanField;

    @FXML
    private TextField nascimentoField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField telefoneField;

    @FXML
    public void edit(ActionEvent event) {
        // método update
        int id = Integer.parseInt(idField.getText()), userTelefone = Integer.parseInt(telefoneField.getText()), userIban = Integer.parseInt(ibanField.getText()), userCode = Integer.parseInt(codeField.getText()), userAcessLevel = Integer.parseInt(accessField.getText());
        String userNome = fullNameField.getText(), userNascimento = nascimentoField.getText(), userEmail = emailField.getText(), userCC = CCField.getText();
        // update skl
        try {
        Connection conn = DBConnect.getConnectionMySQL();
        PreparedStatement st = conn.prepareStatement("UPDATE Utilizador SET Nome = ?, DataNascimento = ?, Telefone = ?," +
                " Email = ?, CC = ?, Iban = ?, Cod_utilizador = ?, NivelAcesso = ? WHERE Id_Utilizador = ?");
                st.setString(1, userNome);
                st.setString(2, userNascimento);
                st.setInt(3, userTelefone);
                st.setString(4, userEmail);
                st.setString(5, userCC);
                st.setInt(6, userIban);
                st.setInt(7,userCode);
                st.setInt(8, userAcessLevel);
                st.setInt(9, id);

                st.executeUpdate();
                st.close();
    } catch(
    SQLException ex)

    {
        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
    }
        ((Stage) Titulo.getScene().getWindow()).close();
}

    public static Utilizador getU() {
        return u;
    }

    public static void setU(Utilizador u) {
        EditUserController.u = u;
    }

    public void atualizarUserDados(){
        UtilizadorDAO editDao = new UtilizadorDAO();
        int id = Integer.parseInt(idField.getText());
        String userNome = fullNameField.getText(), userNascimento = nascimentoField.getText(), userTelefone = telefoneField.getText(), userEmail = emailField.getText(), userCC = CCField.getText(), userIban = ibanField.getText(), userCode = codeField.getText(), userAcessLevel = accessField.getText();
        // Utilizador u = new Utilizador(id, userNome, userNascimento, userTelefone, userEmail, userCC, userIban, userCode, userAcessLevel);

    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        initUser();
    }
    public void initUser(){
        idField.setText(String.valueOf(u.getUserID()));
        fullNameField.setText(u.getUserName());
        nascimentoField.setText(u.getUserNascimento());
        telefoneField.setText(String.valueOf(u.getUserTelefone()));
        emailField.setText(u.getUserEmail());
        CCField.setText(u.getUserCC());
        ibanField.setText(String.valueOf(u.getUserIban()));
        codeField.setText(String.valueOf(u.getUserCode()));
        accessField.setText(String.valueOf(u.getUserAccessLevel()));

    }

    public void onCancelButton(ActionEvent actionEvent) {
        cancelarEdit();
    }

    private void cancelarEdit() {
        ((Stage) Titulo.getScene().getWindow()).close();
    }
}
