package com.grupo5.interfacegp5.JavaFXController;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.grupo5.interfacegp5.Controller.UtilizadorDAO;
import com.grupo5.interfacegp5.Model.Utilizador;
import com.grupo5.interfacegp5.Util.DBConnect;
import com.grupo5.interfacegp5.Various.EditUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterController implements Initializable {

    public Button btn_refresh;
    public Button btn_remove;
    public Button btn_edit;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField nascimentoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField CCField;

    @FXML
    private TextField ibanField;

    @FXML
    private TextField codeField;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField accessField;

    @FXML
    private Button submitButton;
    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(nascimentoField.getText());
        System.out.println(telefoneField.getText());
        System.out.println(emailField.getText());
        System.out.println(CCField.getText());
        System.out.println(ibanField.getText());
        System.out.println(codeField.getText());
        System.out.println(passField.getText());
        System.out.println(accessField.getText());

        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (nascimentoField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your birthdate");
            return;
        }
        if (telefoneField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a telefone");
            return;
        }
        if (emailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (CCField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a CC");
            return;
        }
        if (ibanField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your IBAN");
            return;
        }
        if (codeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a valid code");
            return;
        }
        if (passField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }
        if (accessField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your access level");
            return;
        }

        String uName = fullNameField.getText();
        String uNascimento = nascimentoField.getText();
        Integer uTelefone = Integer.valueOf(telefoneField.getText());
        String uEmail = emailField.getText();
        String uCC = CCField.getText();
        Integer uIban = Integer.valueOf(ibanField.getText());
        Integer uCode = Integer.valueOf(codeField.getText());
        String uPassword = passField.getText();
        Integer uAccessLevel = Integer.valueOf(accessField.getText());


        Utilizador p = new Utilizador(uName, uNascimento, uTelefone, uEmail, uCC, uIban, uCode, uPassword, uAccessLevel);
        UtilizadorDAO pDao = new UtilizadorDAO();
        pDao.create(p);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + fullNameField.getText());
        atualizarTabela();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }



    //  TABELA ----------------------------------------

    @FXML
    private TableView<Utilizador> tabelaUsers;
    @FXML
    private TableColumn<Utilizador, String> nome;
    @FXML
    private TableColumn<Utilizador, String> nascimento;
    @FXML
    private TableColumn<Utilizador, Integer> telefone;
    @FXML
    private TableColumn<Utilizador, String> email;
    @FXML
    private TableColumn<Utilizador, String> cc;
    @FXML
    private TableColumn<Utilizador, Integer> iban;
    @FXML
    private TableColumn<Utilizador, Integer> cod;
    @FXML
    private TableColumn<Utilizador, Integer> nvacesso;
    @FXML
    public TableColumn<Utilizador, Integer> id;

    ObservableList<Utilizador> userList = FXCollections.observableArrayList(new Utilizador());

    private void atualizarTabela(){

        try {
            userList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT ID_Utilizador, Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_Utilizador, NivelAcesso FROM utilizador");

            while(rs.next()){
                userList.add(new Utilizador(
                                rs.getInt("ID_Utilizador"),
                                rs.getString("Nome"),
                                rs.getString("DataNascimento"),
                                rs.getInt("Telefone"),
                                rs.getString("Email"),
                                rs.getString("CC"),
                                rs.getInt("IBAN"),
                                rs.getInt("Cod_Utilizador"),
                                rs.getInt("NivelAcesso")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){

        atualizarTabela();

        id.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userID"));
        nome.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userName"));
        nascimento.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userNascimento"));
        telefone.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userTelefone"));
        email.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userEmail"));
        cc.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userCC"));
        iban.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userIban"));
        cod.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userCode"));
        nvacesso.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userAccessLevel"));

        tabelaUsers.setItems(userList);
    }

    public void onRefreshButton(ActionEvent actionEvent) {
        atualizarTabela();
    }

    public void onRemoveButton(ActionEvent actionEvent) {

        excluirUtilizador();
        atualizarTabela();

    }

    public void onEditButton(ActionEvent actionEvent) throws Exception {
        Utilizador selected = tabelaUsers.getSelectionModel().getSelectedItem();

        if(selected != null){
            EditUser edit = new EditUser(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex){
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Não foi possível iniciar o stage de Edição", ex);
            }
            atualizarTabela();
        } else {
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setHeaderText("Selecione um utilizador!");
            selectionAlert.show();
        }

    }

    public void excluirUtilizador(){
        Utilizador utilizador = tabelaUsers.getSelectionModel().getSelectedItem();
        int id = utilizador.getUserID();

        userList.remove(utilizador);
        UtilizadorDAO.delete(id);
    }
}