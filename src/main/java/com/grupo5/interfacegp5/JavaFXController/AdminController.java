package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.Controller.*;
import com.grupo5.interfacegp5.Model.*;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Util.DBConnect;
import com.grupo5.interfacegp5.Various.EditPlayer;
import com.grupo5.interfacegp5.Various.EditReferee;
import com.grupo5.interfacegp5.Various.EditTeam;
import com.grupo5.interfacegp5.Various.EditUser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Classe controller do menu-adm.fxml
 */

public class AdminController implements Initializable {

    @FXML
    public Button btn_editreferee;
    @FXML
    public TextField gameDate;
    @FXML
    public TextField gameHour;
    @FXML
    public ChoiceBox gameA;
    @FXML
    public ChoiceBox gameB;
    @FXML
    public ChoiceBox gameLocale;
    @FXML
    public ChoiceBox gameJourney;
    @FXML
    public Tab AdmJogosTab;
    @FXML
    public Button gsubmitButton;
    @FXML
    public Button jrSubmitButton;
    @FXML
    public TextField journeyName;
    @FXML
    public Tab AdmJornadaTab;
    @FXML
    public Tab AdmJogadorTab;
    @FXML
    private TabPane MenuAdm;
    @FXML
    private int index = 0;
    @FXML
    private TextField TextUser;
    @FXML
    private TextField TextPass;
    @FXML
    private Label ErroLogin;
    @FXML
    private Tab AdmLoginTab;
    @FXML
    private Tab AdmEquipasTab;
    @FXML
    private Tab AdmArbitrosTab;
    @FXML
    private Tab AdmUtilizadoresTab;

    //Campos de Registo
    @FXML
    private TextField ufullNameField;

    @FXML
    private TextField unascimentoField;

    @FXML
    private TextField utelefoneField;

    @FXML
    private TextField uemailField;

    @FXML
    private TextField uCCField;

    @FXML
    private TextField uibanField;

    @FXML
    private TextField ucodeField;

    @FXML
    private PasswordField upassField;

    @FXML
    private TextField uaccessField;

    @FXML
    private Button usubmitButton;

    /**
     *
     * initatialize inicia as tabelas, "setando" os atributos
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */

    public void initialize(URL url, ResourceBundle resourceBundle){

        //Atualizar dados das tabelas

        atualizarTabelaArbitro();
        atualizarTabelaEquipas();
        atualizarTabelaJogador();
        atualizarTabelaUtilizadores();
        atualizarTabelaJogos();
        atualizarTabelaJornada();

        // Atualizar Dados Choice Box

        carregarClubes();
        carregarLocais();
        carregarJornadas();

        //Tabela Arbitros

        aid.setCellValueFactory(new PropertyValueFactory<Arbitro, Integer>("arbitroID"));
        anome.setCellValueFactory(new PropertyValueFactory<Arbitro, String>("arbitroName"));
        anascimento.setCellValueFactory(new PropertyValueFactory<Arbitro, String>("arbitroNascimento"));
        atelefone.setCellValueFactory(new PropertyValueFactory<Arbitro, Integer>("arbitroTelefone"));
        aemail.setCellValueFactory(new PropertyValueFactory<Arbitro, String>("arbitroEmail"));
        acc.setCellValueFactory(new PropertyValueFactory<Arbitro, String>("arbitroCC"));
        aiban.setCellValueFactory(new PropertyValueFactory<Arbitro, Integer>("arbitroIban"));

        tabelaArbitro.setItems(refereeList);

        //Tabela Clubes

        eid.setCellValueFactory(new PropertyValueFactory<Clube, Integer>("clubeID"));
        eclube.setCellValueFactory(new PropertyValueFactory<Clube, String>("clubeNome"));
        ecidade.setCellValueFactory(new PropertyValueFactory<Clube, String>("clubeCidade"));
        epais.setCellValueFactory(new PropertyValueFactory<Clube, String>("clubePais"));
        eestadio.setCellValueFactory(new PropertyValueFactory<Clube, String>("clubeEstadio"));

        tabelaEquipas.setItems(clubeList);

        //Tabela Jogadores

        jid.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("jogadorID"));
        jnome.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorName"));
        jnascimento.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorNascimento"));
        jtelefone.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("jogadorTelefone"));
        jemail.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorEmail"));
        jcc.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorCC"));
        jiban.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("jogadorIban"));
        jnacionalidade.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorNacionalidade"));

        tabelaJogador.setItems(playerList);

        //Tabela Utilizadores

        uid.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userID"));
        unome.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userName"));
        unascimento.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userNascimento"));
        utelefone.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userTelefone"));
        uemail.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userEmail"));
        ucc.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("userCC"));
        uiban.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userIban"));
        ucod.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userCode"));
        unvacesso.setCellValueFactory(new PropertyValueFactory<Utilizador, Integer>("userAccessLevel"));

        tabelaUsers.setItems(userList);

        //Tabela Jogo

        gID.setCellValueFactory(new PropertyValueFactory<Jogo, Integer>("jogoID"));
        gClubeA.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoTeamA"));
        gClubeB.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoTeamB"));
        gEstadio.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoEstadio"));
        gCidade.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoCidade"));
        gPais.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoPais"));
        gData.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoData"));
        gHora.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoHora"));
        gJourneyID.setCellValueFactory(new PropertyValueFactory<Jogo, Integer>("jogoJornada"));

        tabelaMatch.setItems(matchList);

        // Tabela Jornada

        jrID.setCellValueFactory(new PropertyValueFactory<Jornada, Integer>("jornadaID"));
        jrNome.setCellValueFactory(new PropertyValueFactory<Jornada, String>("jornadaNome"));

        tabelaJourney.setItems(journeyList);

    }

    /**
     *  next tab e backtab navega entre as tabs
     */
    @FXML
    protected void nextTab(){
        index = index + 1;
        if(index == 2){
            AdmArbitrosTab.setDisable(false);
            AdmEquipasTab.setDisable(true);
        }
        if(index == 3){
            AdmJogadorTab.setDisable(false);
            AdmArbitrosTab.setDisable(true);
        }
        if(index == 4){
            AdmUtilizadoresTab.setDisable(false);
            AdmJogadorTab.setDisable(true);
        }
        if(index == 5){
            AdmJogosTab.setDisable(false);
            AdmUtilizadoresTab.setDisable(true);
        }
        if(index == 6){
            AdmJornadaTab.setDisable(false);
            AdmJogosTab.setDisable(true);
        }
        MenuAdm.getSelectionModel().select(index);
    }

    @FXML
    protected void backTab(ActionEvent Event){
        index = index - 1;
        if(index == 1){
            AdmEquipasTab.setDisable(false);
            AdmArbitrosTab.setDisable(true);
        }
        if(index == 2){
            AdmArbitrosTab.setDisable(false);
            AdmJogadorTab.setDisable(true);

        }
        if(index == 3){
            AdmJogadorTab.setDisable(false);
            AdmUtilizadoresTab.setDisable(true);
        }
        if(index == 4){
            AdmUtilizadoresTab.setDisable(false);
            AdmJogosTab.setDisable(true);
        }
        if(index == 5){
            AdmJogosTab.setDisable(false);
            AdmJornadaTab.setDisable(true);
        }
        MenuAdm.getSelectionModel().select(index);;
    }

    /**
     *  exit button sai do programa
     *  @param Event
     */
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

    /**
     * Quando algo está em desenvolvimento, utiliza-se este alerta
     * @param Event
     */
    @FXML
    protected void EmDesenvolvimento(ActionEvent Event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Em Desenvolvimento!");
        alert.setHeaderText("Esta funcionalidade encontra-se em desenvolvimento");

        alert.showAndWait();
    }

    /**
     * faz o login do utilizador na secção de admin
     * @param Event
     */
    @FXML
    protected void loginADM(ActionEvent Event){
        String userid;
        String userPassword;
        int useridint=0;
        String nivelAcesso;

        if((TextUser.getText().equals("") || (TextPass.getText().equals("")))) {
            ErroLogin.setText("Preencha todos os campos!");
            TextUser.setText("");
            TextPass.setText("");
        }else{
            userid =TextUser.getText();
            useridint = Integer.valueOf(userid);
            userPassword = TextPass.getText();

            Utilizador p = new Utilizador();
            UtilizadorDAO readDao = new UtilizadorDAO();
            readDao.nivelAcesso(useridint, userPassword);
            nivelAcesso = String.valueOf(readDao.nivelAcesso(useridint, userPassword));

            if(Integer.valueOf(nivelAcesso) == 1){
                AdmLoginTab.setDisable(true);
                AdmEquipasTab.setDisable(false);
                nextTab();
                TextUser.setText("");
                TextPass.setText("");
                ErroLogin.setText("");
            } else if (nivelAcesso != "1") {
                ErroLogin.setText("Não foi possível fazer o login!");
                TextPass.setText("");
            }
        }

    }

    /**
     * volta para o menu principal
     * @param Event
     * @throws IOException
     */
    @FXML
    protected void GoBackMenu(ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Menu Inicial");
        stage.setScene(scene);
        stage.show();
        ((Stage) ErroLogin.getScene().getWindow()).close();
    }

    @FXML
    protected void logOut(ActionEvent Event){
        if(index == 1){
            AdmEquipasTab.setDisable(true);
        }
        if(index == 2){
            AdmArbitrosTab.setDisable(true);
        }
        if(index == 3){
            AdmJogadorTab.setDisable(true);
        }
        if(index == 4){
            AdmUtilizadoresTab.setDisable(true);
        }
        if(index == 5){
            AdmJogosTab.setDisable(true);
        }
        if(index == 6){
            AdmJornadaTab.setDisable(true);
        }
        index = 0;
        AdmLoginTab.setDisable(false);
        MenuAdm.getSelectionModel().select(index);

    }

    // ************************************************* EQUIPAS


    @FXML
    private TableView<Clube> tabelaEquipas;
    @FXML
    private TableColumn<Clube, Integer> eid;
    @FXML
    private TableColumn<Clube, String> eclube;
    @FXML
    private TableColumn<Clube, String> ecidade;
    @FXML
    private TableColumn<Clube, String> eestadio;
    @FXML
    private TableColumn<Clube, String> epais;


    @FXML
    private TextField EquipaField;

    @FXML
    private TextField LocalField;

    @FXML
    private Button submitButtonClubes;

    @FXML
    private Label Testes;



    ObservableList<Clube> clubeList = FXCollections.observableArrayList(new Clube());

    /**
     * atualiza a tabela
     */
    private void atualizarTabelaEquipas(){

        try {
            clubeList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Clube.Id_Clube, Clube.Nome, Local.Cidade, Local.Pais, Local.NomeEstadio from Clube Inner join Local on Clube.Id_local = Local.ID_Local");

            while(rs.next()){
                clubeList.add(new Clube(
                                rs.getInt("ID_Clube"),
                                rs.getString("Nome"),
                                rs.getString("Cidade"),
                                rs.getString("Pais"),
                                rs.getString("NomeEstadio")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void registeEquipas(ActionEvent event)  throws SQLException{
        Window owner = submitButtonClubes.getScene().getWindow();

        System.out.println(EquipaField.getText());
        System.out.println(LocalField.getText());


        if (EquipaField.getText().isEmpty()) {
            showAlertEquipas(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Insira o seu nome!");
            return;
        }

        if (LocalField.getText().isEmpty()) {
            showAlertEquipas(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Insira o nome da Equipa a Registar!");
            return;
        }

        String cNome = EquipaField.getText();
        int cLocal = Integer.parseInt(LocalField.getText());



        Clube c = new Clube(cNome, cLocal);
        ClubeDAO cDao = new ClubeDAO();
        cDao.create(c);


        showAlertEquipas(Alert.AlertType.INFORMATION, owner, "Registration Successful!",
                "Equipa Registada " + EquipaField.getText() + " com Sucesso!");


        EquipaField.setText("");
        LocalField.setText("");
    }

    private static void showAlertEquipas(Alert.AlertType alertType, Window owner, String title, String message) {

    }

    @FXML
    protected void onRefreshButtonEquipas(ActionEvent event){
        atualizarTabelaEquipas();
    }

    @FXML
    protected void onRemoveButtonEquipas(ActionEvent event){
        removerClube();
    }

    //************************************************************************************* ARBITRO

    public Button btn_refresh;
    public Button btn_remove;
    public Button btn_edit;

    @FXML
    private TextField afullNameField;

    @FXML
    private TextField anascimentoField;

    @FXML
    private TextField atelefoneField;

    @FXML
    private TextField aemailField;

    @FXML
    private TextField aCCField;

    @FXML
    private TextField aibanField;

    @FXML
    private Button submitButton;

    @FXML
    public void registerArbitro(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(afullNameField.getText());
        System.out.println(anascimentoField.getText());
        System.out.println(atelefoneField.getText());
        System.out.println(aemailField.getText());
        System.out.println(aCCField.getText());
        System.out.println(aibanField.getText());

        if (afullNameField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um nome válido");
            return;
        }

        if (anascimentoField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira uma data válida");
            return;
        }
        if (atelefoneField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um telefone válido");
            return;
        }
        if (aemailField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um email válido");
            return;
        }
        if (aCCField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um CC válido");
            return;
        }
        if (aibanField.getText().isEmpty()) {
            showAlertArbitro(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um IBAN válido");
            return;
        }

        String aNome = afullNameField.getText();
        String aNascimento = anascimentoField.getText();
        Integer aTelefone = Integer.valueOf(atelefoneField.getText());
        String aEmail = aemailField.getText();
        String aCC = aCCField.getText();
        Integer aIban = Integer.valueOf(aibanField.getText());


        Arbitro a = new Arbitro(aNome, aNascimento, aTelefone, aEmail, aCC, aIban);
        ArbitroDAO aDao = new ArbitroDAO();
        aDao.create(a);

        showAlertArbitro(Alert.AlertType.INFORMATION, owner, "Registo Concluido!",
                "Árbitro '" + afullNameField.getText() + "' registado com sucesso");
        afullNameField.setText("");
        anascimentoField.setText("");
        atelefoneField.setText("");
        aemailField.setText("");
        aCCField.setText("");
        aibanField.setText("");
    }

    private static void showAlertArbitro(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    protected void removerClube(){
        Clube clube = tabelaEquipas.getSelectionModel().getSelectedItem();
        int id = clube.getClubeID();

        clubeList.remove(clube);
        ClubeDAO.delete(id);
    }

    @FXML
    private TableView<Arbitro> tabelaArbitro;
    @FXML
    private TableColumn<Arbitro, String> anome;
    @FXML
    private TableColumn<Arbitro, String> anascimento;
    @FXML
    private TableColumn<Arbitro, Integer> atelefone;
    @FXML
    private TableColumn<Arbitro, String> aemail;
    @FXML
    private TableColumn<Arbitro, String> acc;
    @FXML
    private TableColumn<Arbitro, Integer> aiban;
    @FXML
    public TableColumn<Arbitro, Integer> aid;

    ObservableList<Arbitro> refereeList = FXCollections.observableArrayList(new Arbitro());

    private void atualizarTabelaArbitro(){

        try {
            refereeList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT ID_Arbitro, Nome, DataNascimento, Telefone, Email, CC, IBAN FROM Arbitro");

            while(rs.next()){
                refereeList.add(new Arbitro(
                                rs.getInt("ID_Arbitro"),
                                rs.getString("Nome"),
                                rs.getString("DataNascimento"),
                                rs.getInt("Telefone"),
                                rs.getString("Email"),
                                rs.getString("CC"),
                                rs.getInt("IBAN")

                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void onRefreshButtonArbitro(ActionEvent actionEvent) {
        atualizarTabelaArbitro();
    }

    public void onRemoveButtonArbitro(ActionEvent actionEvent) {

        excluirArbitro();

    }

    public void excluirArbitro(){
        Arbitro arbitro = tabelaArbitro.getSelectionModel().getSelectedItem();
        int id = arbitro.getArbitroID();

        refereeList.remove(arbitro);
        ArbitroDAO.delete(id);
    }

    // ****************************************************** Jogador


    @FXML
    private TextField jfullNameField;

    @FXML
    private TextField jnascimentoField;

    @FXML
    private TextField jtelefoneField;

    @FXML
    private TextField jemailField;

    @FXML
    private TextField jCCField;

    @FXML
    private TextField jibanField;
    @FXML
    private TextField jnacionalidadeField;
    @FXML
    private ChoiceBox jClubeField;

    @FXML
    private Button submitButtonJogador;

    @FXML
    private TableView<Jogador> tabelaJogador;
    @FXML
    private TableColumn<Jogador, String> jnome;
    @FXML
    private TableColumn<Jogador, String> jnascimento;
    @FXML
    private TableColumn<Jogador, Integer> jtelefone;
    @FXML
    private TableColumn<Jogador, String> jemail;
    @FXML
    private TableColumn<Jogador, String> jcc;
    @FXML
    private TableColumn<Jogador, String> jnacionalidade;
    @FXML
    private TableColumn<Jogador, Integer> jiban;
    @FXML
    public TableColumn<Jogador, Integer> jid;

    @FXML
    public void registerJogador(ActionEvent event) throws SQLException {

        Window owner = submitButtonJogador.getScene().getWindow();

        System.out.println(jfullNameField.getText());
        System.out.println(jnascimentoField.getText());
        System.out.println(jtelefoneField.getText());
        System.out.println(jemailField.getText());
        System.out.println(jCCField.getText());
        System.out.println(jibanField.getText());
        System.out.println(jnacionalidadeField.getText());

        if (jfullNameField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um nome válido");
            return;
        }

        if (jnascimentoField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira uma data válida");
            return;
        }
        if (jtelefoneField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um telefone válido");
            return;
        }
        if (jemailField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um email válido");
            return;
        }
        if (jCCField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um CC válido");
            return;
        }
        if (jibanField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um IBAN válido");
            return;
        }
        if (jnacionalidadeField.getText().isEmpty()) {
            showAlertJogador(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um IBAN válido");
            return;
        }

        String jNome = jfullNameField.getText();
        String jNascimento = jnascimentoField.getText();
        Integer jTelefone = Integer.valueOf(jtelefoneField.getText());
        String jEmail = jemailField.getText();
        String jCC = jCCField.getText();
        Integer jIban = Integer.valueOf(jibanField.getText());
        String jnacionalidade = jnacionalidadeField.getText();


        Jogador j = new Jogador(jNome, jNascimento, jTelefone, jEmail, jCC, jIban, jnacionalidade);
        JogadorDAO jDao = new JogadorDAO();
        jDao.create(j);

        showAlertJogador(Alert.AlertType.INFORMATION, owner, "Registo Concluido!",
                "Jogador '" + jfullNameField.getText() + "' registado com sucesso");
        jfullNameField.setText("");
        jnascimentoField.setText("");
        jtelefoneField.setText("");
        jemailField.setText("");
        jCCField.setText("");
        jibanField.setText("");
        jnacionalidadeField.setText("");
    }
    private static void showAlertJogador(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    ObservableList<Jogador> playerList = FXCollections.observableArrayList(new Jogador());

    private void atualizarTabelaJogador(){

        try {
            playerList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT Jogador.ID_Jogador, Jogador.Nome, Jogador.DataNascimento, " +
                    "Jogador.Telefone, Jogador.Email, Jogador.CC, Jogador.IBAN, Jogador.Nacionalidade FROM Jogador");

            while(rs.next()){
                playerList.add(new Jogador(
                                rs.getInt("ID_Jogador"),
                                rs.getString("Nome"),
                                rs.getString("DataNascimento"),
                                rs.getInt("Telefone"),
                                rs.getString("Email"),
                                rs.getString("CC"),
                                rs.getInt("IBAN"),
                                rs.getString("Nacionalidade")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void onRefreshButtonJogador(ActionEvent actionEvent) {
        atualizarTabelaJogador();
    }

    public void onRemoveButtonJogador(ActionEvent actionEvent) {

        excluirJogador();

    }

    public void excluirJogador(){
        Jogador jogador = tabelaJogador.getSelectionModel().getSelectedItem();
        int id = jogador.getJogadorID();

        playerList.remove(jogador);
        JogadorDAO.delete(id);
    }

    //************************************************************************************* Utilizador
    @FXML
    private TableView<Utilizador> tabelaUsers;
    @FXML
    private TableColumn<Utilizador, String> unome;
    @FXML
    private TableColumn<Utilizador, String> unascimento;
    @FXML
    private TableColumn<Utilizador, Integer> utelefone;
    @FXML
    private TableColumn<Utilizador, String> uemail;
    @FXML
    private TableColumn<Utilizador, String> ucc;
    @FXML
    private TableColumn<Utilizador, Integer> uiban;
    @FXML
    private TableColumn<Utilizador, Integer> ucod;
    @FXML
    private TableColumn<Utilizador, Integer> unvacesso;
    @FXML
    public TableColumn<Utilizador, Integer> uid;

    ObservableList<Utilizador> userList = FXCollections.observableArrayList(new Utilizador());

    private void atualizarTabelaUtilizadores(){

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

    //Função do botão atualizar
    public void onRefreshButtonUtilizadores(ActionEvent actionEvent) {
        atualizarTabelaUtilizadores();
    }

    //Função do botão eliminar do utilizador
    public void onRemoveButtonUtilizadores(ActionEvent actionEvent) {excluirUtilizador();}

    //Função para excluir o Utilizador
    public void excluirUtilizador(){
        Utilizador utilizador = tabelaUsers.getSelectionModel().getSelectedItem();
        int id = utilizador.getUserID();

        userList.remove(utilizador);
        UtilizadorDAO.delete(id);
        atualizarTabelaUtilizadores();
    }



    @FXML
    public void registerUtilizador(ActionEvent event) throws SQLException {

        Window owner = usubmitButton.getScene().getWindow();

        System.out.println(ufullNameField.getText());
        System.out.println(unascimentoField.getText());
        System.out.println(utelefoneField.getText());
        System.out.println(uemailField.getText());
        System.out.println(uCCField.getText());
        System.out.println(uibanField.getText());
        System.out.println(ucodeField.getText());
        System.out.println(upassField.getText());
        System.out.println(uaccessField.getText());

        if (ufullNameField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your name");
            return;
        }

        if (unascimentoField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your birthdate");
            return;
        }
        if (utelefoneField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira a telefone");
            return;
        }
        if (uemailField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your email");
            return;
        }
        if (uCCField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira a CC");
            return;
        }
        if (uibanField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your IBAN");
            return;
        }
        if (ucodeField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira a valid code");
            return;
        }
        if (upassField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your password");
            return;
        }
        if (uaccessField.getText().isEmpty()) {
            showAlertUtilizadores(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira your access level");
            return;
        }

        String uName = ufullNameField.getText();
        String uNascimento = unascimentoField.getText();
        Integer uTelefone = Integer.valueOf(utelefoneField.getText());
        String uEmail = uemailField.getText();
        String uCC = uCCField.getText();
        Integer uIban = Integer.valueOf(uibanField.getText());
        Integer uCode = Integer.valueOf(ucodeField.getText());
        String uPassword = upassField.getText();
        Integer uAccessLevel = Integer.valueOf(uaccessField.getText());


        Utilizador p = new Utilizador(uName, uNascimento, uTelefone, uEmail, uCC, uIban, uCode, uPassword, uAccessLevel);
        UtilizadorDAO pDao = new UtilizadorDAO();
        pDao.create(p);

        showAlertUtilizadores(Alert.AlertType.CONFIRMATION, owner, "Registado com sucesso!",
                "Utilizador Registado com sucesso! ");

        ufullNameField.clear();
        unascimentoField.clear();
        utelefoneField.clear();
        uemailField.clear();
        uCCField.clear();
        uibanField.clear();
        ucodeField.clear();
        upassField.clear();
        uaccessField.clear();

        atualizarTabelaUtilizadores();
    }

    private static void showAlertUtilizadores(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    // EDIÇÕES TUDO

    public void onEditUserButton(ActionEvent actionEvent) throws Exception {
        Utilizador selected = tabelaUsers.getSelectionModel().getSelectedItem();

        if(selected != null){
            EditUser edit = new EditUser(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex){
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Não foi possível iniciar o stage de Edição", ex);
            }
            atualizarTabelaUtilizadores();
        } else {
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setHeaderText("Selecione um utilizador!");
            selectionAlert.show();
        }

    }

    public void onEditPlayerButton(ActionEvent actionEvent) {
        Jogador selected = tabelaJogador.getSelectionModel().getSelectedItem();

        if(selected != null){
            EditPlayer edit = new EditPlayer(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex){
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Não foi possível iniciar o stage de Edição", ex);
            }
            atualizarTabelaJogador();
        } else {
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setHeaderText("Selecione um Jogador!");
            selectionAlert.show();
        }
        atualizarTabelaJogador();
    }

    public void onEditRefereeButton(ActionEvent actionEvent) {
        Arbitro selected = tabelaArbitro.getSelectionModel().getSelectedItem();

        if(selected != null){
            EditReferee edit = new EditReferee(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex){
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Não foi possível iniciar o stage de Edição", ex);
            }
            atualizarTabelaArbitro();
        } else {
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setHeaderText("Selecione um Árbitro!");
            selectionAlert.show();
        }
        atualizarTabelaArbitro();
    }

    public void onEditTeamButton(ActionEvent actionEvent) {
        Clube selected = tabelaEquipas.getSelectionModel().getSelectedItem();

        if(selected != null){
            EditTeam edit = new EditTeam(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex){
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Não foi possível iniciar o stage de Edição", ex);
            }
            atualizarTabelaArbitro();
        } else {
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setHeaderText("Selecione uma Equipa!");
            selectionAlert.show();
        }
        atualizarTabelaEquipas();
    }

    // JOGOS

    @FXML
    private TableView<Jogo> tabelaMatch;
    @FXML
    private TableColumn<Jogo, Integer> gID;
    @FXML
    private TableColumn<Jogo, String> gClubeA;
    @FXML
    private TableColumn<Jogo, String> gClubeB;
    @FXML
    private TableColumn<Jogo, String> gCidade;
    @FXML
    private TableColumn<Jogo, String> gEstadio;
    @FXML
    private TableColumn<Jogo, String> gPais;
    @FXML
    private TableColumn<Jogo, String> gData;
    @FXML
    private TableColumn<Jogo, String> gHora;
    @FXML
    private TableColumn<Jogo, Integer> gJourneyID;

    public void onEditGameButton(ActionEvent actionEvent) {
    }

    ObservableList<Jogo> matchList = FXCollections.observableArrayList(new Jogo());

    private void atualizarTabelaJogos(){

        try {
            matchList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Jogo.Id_Jogo, ClubeA.Nome AS ClubeA, ClubeB.Nome AS ClubeB, Local.NomeEstadio, Local.Cidade, Local.Pais, Jogo.Data_Jogo, Jogo.horaInicio, Jogo.Id_Jornada from Jogo \n" +
                    "Inner join \n" +
                    "\tLocal on Jogo.Local = Local.ID_Local\n" +
                    "INNER JOIN Clube ClubeA ON (Jogo.ClubeA = ClubeA.ID_Clube)\n" +
                    "INNER JOIN Clube ClubeB ON (Jogo.ClubeB = ClubeB.ID_Clube)");

            //Select Jogo.Id_Jogo, Jogo.ClubeA, Jogo.ClubeB, Local.NomeEstadio, Local.Cidade, Local.Pais, Jogo.Data_Jogo, Jogo.horaInicio from Jogo Inner join Local on Jogo.Local = Local.ID_Local

            while(rs.next()){
                matchList.add(new Jogo(
                                rs.getInt("Id_Jogo"),
                                rs.getString("ClubeA"),
                                rs.getString("ClubeB"),
                                rs.getString("NomeEstadio"),
                                rs.getString("Cidade"),
                                rs.getString("Pais"),
                                rs.getString("Data_Jogo"),
                                rs.getString("horaInicio"),
                                rs.getInt("Id_Jornada")
                        )
                );
            }
            conn.close();

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Random random = new Random();
    int clubeRandom = random.nextInt(20);
    int localRandom = random.nextInt(21);
    int jornadaRandom = random.nextInt(20);

    //Função do botão atualizar
    public void onRefreshButtonJogos(ActionEvent actionEvent) {
        atualizarTabelaJogos();
    }

    //Função do botão eliminar do utilizador
    public void onRemoveButtonJogos(ActionEvent actionEvent) {excluirJogos();}

    //Função para excluir o Utilizador
    public void excluirJogos(){
        Jogo jogo = tabelaMatch.getSelectionModel().getSelectedItem();
        int id = jogo.getJogoID();

        matchList.remove(jogo);
        JogoDAO.delete(id);
        atualizarTabelaJogos();
    }

    public void carregarClubes(){
        try{
            Connection conn = DBConnect.getConnectionMySQL();

            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Clube");
            ObservableList nomeClube = FXCollections.observableArrayList();
            while (rs.next()){
                nomeClube.add(new String(rs.getString(1)));
            }
            gameA.setItems(nomeClube);
            gameB.setItems(nomeClube);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void carregarLocais(){
        try{
            Connection conn = DBConnect.getConnectionMySQL();

            ResultSet rs = conn.createStatement().executeQuery("SELECT NomeEstadio FROM Local");
            ObservableList paisLocal = FXCollections.observableArrayList();
            while (rs.next()){
                paisLocal.add(new String(rs.getString(1)));
            }
            gameLocale.setItems(paisLocal);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void carregarJornadas(){
        try{
            Connection conn = DBConnect.getConnectionMySQL();

            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Jornada");
            ObservableList jornadas = FXCollections.observableArrayList();
            while (rs.next()){
                jornadas.add(new String(rs.getString(1)));
            }
            gameJourney.setItems(jornadas);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /*      métodos para pegar o valor da choicebox

    public void getLocal(ActionEvent event) {
        String localeValue = String.valueOf(gameLocale.getValue());
    }
    public void getClubeA(ActionEvent event) {
        String clubeA = String.valueOf(gameA.getValue());
    }
    public void getClubeB(ActionEvent event) {
        String clubeB = String.valueOf(gameB.getValue());
    }

    */

    @FXML
    public void registerJogos(ActionEvent event) throws SQLException {

        Window owner = gsubmitButton.getScene().getWindow();

        String localeValue = String.valueOf(gameLocale.getValue());
        String clubeAValue = String.valueOf(gameA.getValue());
        String clubeBValue = String.valueOf(gameB.getValue());
        String jornadaValue = String.valueOf(gameJourney.getValue());

        System.out.println(clubeAValue);
        System.out.println(clubeBValue);
        System.out.println(localeValue);
        System.out.println(gameDate.getText());
        System.out.println(gameHour.getText());
        System.out.println(jornadaValue);

        if (gameA.getId().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um clube");
            return;
        }

        if (gameB.getId().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um clube");
            return;
        }
        if (gameLocale.getId().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um local");
            return;
        }
        if (gameDate.getText().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira uma data");
            return;
        }
        if (gameHour.getText().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira uma hora");
            return;
        }
        if (gameJourney.getId().isEmpty()) {
            showAlertJogos(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira uma Jornada");
            return;
        }

        /**
         *
         *      Os valores inseridos nas choicebox's são convertidos aqui através dos selects abaixo que atribui o valor
         *      da ID das opções escolhidas à uma variável para poder mandar para a base de dados
         *
         */

        int idClubeA = 0;
        int idClubeB = 0;
        int idLocale = 0;
        int idJornada = 0;

        try{

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Clube From Clube Where Nome LIKE '" + clubeAValue + "'");
            while(rs.next()){
                rs.getInt("Id_Clube");
                idClubeA = rs.getInt("id_clube");
            }
            System.out.println(idClubeA);
            conn.close();

        }catch(Exception e){

        }
        try{
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Clube From Clube Where Nome LIKE '" + clubeBValue + "'");
            while(rs.next()){
                rs.getInt("Id_Clube");
                idClubeB = rs.getInt("id_clube");
            }
            System.out.println(idClubeB);
            conn.close();

        }catch(Exception e){

        }
        try{
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Local From Local Where NomeEstadio LIKE '" + localeValue + "'");
            while(rs.next()){
                rs.getInt("id_local");
                idLocale = rs.getInt("id_local");
            }
            System.out.println(idLocale);
            conn.close();

        }catch(Exception e){

        }
        try{
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Jornada From Jornada Where Nome LIKE '" + jornadaValue + "'");
            while(rs.next()){
                rs.getInt("Id_Jornada");
                idJornada = rs.getInt("Id_Jornada");
            }
            System.out.println(idJornada);
            conn.close();

        }catch(Exception e){

        }

        // -----------------------------------------------------------------------------------

        String jogoData = gameDate.getText();
        String jogoHora = gameHour.getText();


        Jogo m = new Jogo(idClubeA, idClubeB, idLocale, jogoData, jogoHora, idJornada);
        JogoDAO mDao = new JogoDAO();
        mDao.create(m);

        showAlertJogos(Alert.AlertType.CONFIRMATION, owner, "Registado com sucesso!",
                "Jogo Registado com sucesso! ");

        gameA.valueProperty().set(null);;
        gameB.valueProperty().set(null);;
        gameLocale.valueProperty().set(null);;
        gameDate.clear();
        gameHour.clear();
        gameJourney.valueProperty().set(null);;
        atualizarTabelaJogos();

    }
    private static void showAlertJogos(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //  JORNADAS ---------------------------------------------

    @FXML
    private TableView<Jornada> tabelaJourney;
    @FXML
    private TableColumn<Jornada, Integer> jrID;
    @FXML
    private TableColumn<Jornada, String> jrNome;

    @FXML
    public void registerjornadas(ActionEvent actionEvent) {
        Window owner = jrSubmitButton.getScene().getWindow();

        System.out.println(journeyName.getText());

        if (journeyName.getText().isEmpty()) {
            showAlertJornadas(Alert.AlertType.ERROR, owner, "Erro no formulário!",
                    "Por favor insira um Nome");
            return;
        }

        String jrNome = journeyName.getText();

        Jornada jr = new Jornada(jrNome);
        JornadaDAO jrDao = new JornadaDAO();
        jrDao.create(jr);

        showAlertJornadas(Alert.AlertType.CONFIRMATION, owner, "Registado com sucesso!",
                "Jornada Registada com sucesso! ");

        journeyName.clear();

        atualizarTabelaJornada();
    }

    private static void showAlertJornadas(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    ObservableList<Jornada> journeyList = FXCollections.observableArrayList(new Jornada());
    private void atualizarTabelaJornada(){

        try {
            journeyList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT ID_Jornada, Nome FROM Jornada");

            while(rs.next()){
                journeyList.add(new Jornada(
                                rs.getInt("Id_Jornada"),
                                rs.getString("Nome")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void excluirJornada(){
        Jornada jornada = tabelaJourney.getSelectionModel().getSelectedItem();
        int id = jornada.getJornadaID();

        journeyList.remove(jornada);
        JornadaDAO.delete(id);
        atualizarTabelaJornada();
    }

    public void onEditJourneyButton(ActionEvent actionEvent) {
    }

    public void onRemoveButtonJornadas(ActionEvent actionEvent) {
        excluirJornada();
    }

    public void onRefreshButtonJornadas(ActionEvent actionEvent) {
        atualizarTabelaJornada();
    }
}
