package com.grupo5.interfacegp5.JavaFXController;

import com.grupo5.interfacegp5.Controller.EventoDAO;
import com.grupo5.interfacegp5.Controller.UtilizadorDAO;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.*;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpController implements Initializable {

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
    //Variáveis referentes aos tabs
    @FXML
    private Tab AdmLoginTab;
    @FXML
    private Tab AdmEquipasTab;
    @FXML
    private Tab AdmArbitrosTab;

    /* Variáveis Login Operador */


    /* Função para executar o login do operador */
    @FXML
    protected void loginOP(ActionEvent Event) {
        //Variáveis referêntes á credências de login
        String userid;
        String userPassword;

        int useridint = 0;
        String nivelAcesso;

        if ((TextUser.getText().equals("") || (TextPass.getText().equals("")))) {
            ErroLogin.setText("Preencha todos os campos!");
            TextUser.setText("");
            TextPass.setText("");
        } else {
            userid = TextUser.getText();
            useridint = Integer.valueOf(userid);
            userPassword = TextPass.getText();

            Utilizador p = new Utilizador();
            UtilizadorDAO readDao = new UtilizadorDAO();
            readDao.nivelAcesso(useridint, userPassword);
            nivelAcesso = String.valueOf(readDao.nivelAcesso(useridint, userPassword));

            if (Integer.valueOf(nivelAcesso) == 2) {
                TextUser.setText("");
                TextPass.setText("");
                ErroLogin.setText("");
                AdmLoginTab.setDisable(true);
                AdmEquipasTab.setDisable(false);
                nextTab();
            } else if (nivelAcesso != "2") {
                ErroLogin.setText("Não foi possível fazer o login!");
                TextPass.setText("");
            }
        }

    }

    @FXML
    protected void nextTab() {
        index = index + 1;
        if (index == 1) {
            AdmLoginTab.setDisable(true);
            AdmEquipasTab.setDisable(false);
        }
        if (index == 2) {
            AdmArbitrosTab.setDisable(false);
            AdmEquipasTab.setDisable(true);
        }
        MenuAdm.getSelectionModel().select(index);
    }

    @FXML
    protected void backTab() {
        index = index - 1;
        if (index == 1) {
            AdmEquipasTab.setDisable(false);
            AdmArbitrosTab.setDisable(true);
        }
        if (index == 2) {
            AdmArbitrosTab.setDisable(false);
        }
        MenuAdm.getSelectionModel().select(index);
    }

    @FXML
    protected void exitButton(ActionEvent Event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Tem a certeza que pretende sair?");

        ButtonType bsim = new ButtonType("Sim");
        ButtonType bnao = new ButtonType("Não");
        alert.getButtonTypes().setAll(bsim, bnao);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == bsim) {
            Platform.exit();
        } else if (result.get() == bnao) {

        }
    }

    @FXML
    protected void EmDesenvolvimento(ActionEvent Event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Em Desenvolvimento!");
        alert.setHeaderText("Esta funcionalidade encontra-se em desenvolvimento");

        alert.showAndWait();
    }

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
    protected void logOut(ActionEvent Event) {
        if (index == 1) {
            AdmEquipasTab.setDisable(true);
        }
        if (index == 2) {
            AdmArbitrosTab.setDisable(true);
        }
        index = 0;
        AdmLoginTab.setDisable(false);
        MenuAdm.getSelectionModel().select(index);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Atualizar dados das tabelas

        atualizarTabelaJogos();
        atualizarTabelaJornada();
        atualizarTabelaEventos();

        // Atualizar Dados Choice Box
        /*

        carregarClubes();
        carregarLocais();
        carregarJornadas();

         */
        //Tabela Jogo

        gID.setCellValueFactory(new PropertyValueFactory<Jogo, Integer>("jogoID"));
        gClubeA.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoTeamA"));
        gClubeB.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoTeamB"));
        gEstadio.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoEstadio"));
        gData.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoData"));
        gHora.setCellValueFactory(new PropertyValueFactory<Jogo, String>("jogoHora"));
        gJourneyID.setCellValueFactory(new PropertyValueFactory<Jogo, Integer>("jogoJornada"));

        jogosTable.setItems(matchList);

        // Tabela Jornada

        jrID.setCellValueFactory(new PropertyValueFactory<Jornada, Integer>("jornadaID"));
        jrNome.setCellValueFactory(new PropertyValueFactory<Jornada, String>("jornadaNome"));

        jornadaTable.setItems(journeyList);

        // Tabela Evento

        eventID.setCellValueFactory(new PropertyValueFactory<Evento, String>("eventoId"));
        eventJogador.setCellValueFactory(new PropertyValueFactory<Evento, String>("eventoJogador"));
        eventClube.setCellValueFactory(new PropertyValueFactory<Evento, String>("eventoClube"));
        eventTempo.setCellValueFactory(new PropertyValueFactory<Evento, String>("eventoTempo"));

        eventTable.setItems(eventList);

    }

    @FXML
    private TableView<Jornada> jornadaTable;
    @FXML
    private TableColumn<Jornada, Integer> jrID;
    @FXML
    private TableColumn<Jornada, String> jrNome;
    ObservableList<Jornada> journeyList = FXCollections.observableArrayList(new Jornada());

    private void atualizarTabelaJornada() {

        try {
            journeyList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT ID_Jornada, Nome FROM Jornada");

            while (rs.next()) {
                journeyList.add(new Jornada(
                                rs.getInt("Id_Jornada"),
                                rs.getString("Nome")
                        )
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private TableView<Jogo> jogosTable;
    @FXML
    private TableColumn<Jogo, Integer> gID;
    @FXML
    private TableColumn<Jogo, String> gClubeA;
    @FXML
    private TableColumn<Jogo, String> gClubeB;
    @FXML
    private TableColumn<Jogo, String> gEstadio;
    @FXML
    private TableColumn<Jogo, String> gData;
    @FXML
    private TableColumn<Jogo, String> gHora;
    @FXML
    private TableColumn<Jogo, Integer> gJourneyID;

    ObservableList<Jogo> matchList = FXCollections.observableArrayList(new Jogo());

    private void atualizarTabelaJogos() {


        try {
            matchList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Jogo.Id_Jogo, ClubeA.Nome AS ClubeA, ClubeB.Nome AS ClubeB, Local.NomeEstadio, Jogo.Data_Jogo, Jogo.horaInicio, Jogo.Id_Jornada from Jogo \n" +
                    "Inner join \n" +
                    "\tLocal on Jogo.Local = Local.ID_Local\n" +
                    "INNER JOIN Clube ClubeA ON (Jogo.ClubeA = ClubeA.ID_Clube)\n" +
                    "INNER JOIN Clube ClubeB ON (Jogo.ClubeB = ClubeB.ID_Clube)");

            //Select Jogo.Id_Jogo, Jogo.ClubeA, Jogo.ClubeB, Local.NomeEstadio, Local.Cidade, Local.Pais, Jogo.Data_Jogo, Jogo.horaInicio from Jogo Inner join Local on Jogo.Local = Local.ID_Local

            while (rs.next()) {
                matchList.add(new Jogo(
                                rs.getInt("Id_Jogo"),
                                rs.getString("ClubeA"),
                                rs.getString("ClubeB"),
                                rs.getString("NomeEstadio"),
                                rs.getString("Data_Jogo"),
                                rs.getString("horaInicio"),
                                rs.getInt("Id_Jornada")
                        )
                );
            }
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String cA = "";
    String cB = "";
    int idClubeA = 0;
    int idClubeB = 0;
    String nomeClubeA = "";
    String nomeClubeB = "";

    int idClubeEvento = 0;
    int jogID = 0;
    public void selecionarJogo() {                                // SELECIONA O JOGO

        Jogo initJogo = jogosTable.getSelectionModel().getSelectedItem();
        jogID = initJogo.getJogoID();
        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select ClubeA From Jogo Where Id_Jogo LIKE " + jogID);
            while (rs.next()) {
                rs.getInt("ClubeA");
                idClubeA = rs.getInt("ClubeA");
            }
            System.out.println(idClubeA);
            conn.close();
        } catch (Exception e) {
        }
        try {

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select ClubeB From Jogo Where Id_Jogo LIKE " + jogID);
            while (rs.next()) {
                rs.getInt("ClubeB");
                idClubeB = rs.getInt("ClubeB");
            }
            System.out.println(idClubeB);
            conn.close();

        } catch (Exception e) {
        }

        /*select Clube.Nome
        From Clube
        Inner Join Jogo on Jogo.ClubeA = Clube.Id_Clube or Jogo.ClubeB = Clube.Id_Clube

         */
        try {

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Nome From Clube Where ID_Clube LIKE " + idClubeA);
            while (rs.next()) {
                rs.getString("Nome");
                nomeClubeA = rs.getString("Nome");
            }
            System.out.println(nomeClubeA);
            conn.close();

        } catch (Exception e) {

        }
        try {

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Nome From Clube Where ID_Clube LIKE " + idClubeB);
            while (rs.next()) {
                rs.getString("Nome");
                nomeClubeB = rs.getString("Nome");
            }
            System.out.println(nomeClubeB);
            conn.close();

        } catch (Exception e) {

        }
        carregarJogadoresA();
        carregarJogadoresB();

        placarA.setText(nomeClubeA);
        placarB.setText(nomeClubeB);
        opClubeA.setText(nomeClubeA);
        opClubeB.setText(nomeClubeB);

        atualizarTabelaEventos();

        nextTab(); // Tem que ser o utlimo método dentro deste método
    }

    int resultA = 0;
    int resultB=0;
    private void iniciarJogo(){
        opTempo.setText("05:00");
        second =0;
        minute =5;
        countdownTimer();
        timer.start();
        opClubeA.setSelected(false);
        opClubeB.setSelected(false);

        resultA = 0;
        resultB = 0;

        opResultA.setText(String.valueOf(resultA));
        opResultB.setText(String.valueOf(resultB));

        onCheckA();
        onCheckB();
    }

    /*
    if(opClubeA.isSelected()){
        opClubeB.setSelected(false);
        idClubeEvento = idClubeA;
    }
        if(opClubeB.isSelected()){
        opClubeA.setSelected(false);
        idClubeEvento = idClubeB;
    }
    */

    Timer timer;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    public void countdownTimer() {                   // TIMER

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                opTempo.setText(ddMinute + ":" + ddSecond);

                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    opTempo.setText(ddMinute + ":" + ddSecond);
                }
                if(minute==0 && second==0) {
                    timer.stop();
                    showAlertConfirm();
                }
            }
        });
    }

    // ALERTE CONFIRMAÇÃO
    private static void showAlertConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("FIM DE JOGO");
        alert.setHeaderText("Por favor, verifique o resultado do jogo.");
        alert.setContentText("O resultado do jogo está correto? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Jogo Registado");
        }
    }

    // BOTÃO DE SELECIONAR
    public void onSelectButton(ActionEvent actionEvent) {
        selecionarJogo();
    }

    // CARREGA A CHOICEBOX DO CLUBE A
    public void carregarJogadoresA(){
        try{
            Connection conn = DBConnect.getConnectionMySQL();

            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Jogador WHERE Id_Clube = " + idClubeA);
            ObservableList jogadoresA = FXCollections.observableArrayList();
            while (rs.next()){
                jogadoresA.add(new String(rs.getString(1)));
            }
            jogadoresClubeA.setItems(jogadoresA);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // CARREGA A CHOICEBOX DO CLUBE B
    public void carregarJogadoresB(){
        try{
            Connection conn = DBConnect.getConnectionMySQL();

            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Jogador WHERE Id_Clube = " + idClubeB);
            ObservableList jogadoresB = FXCollections.observableArrayList();
            while (rs.next()){
                jogadoresB.add(new String(rs.getString(1)));
            }
            jogadoresClubeB.setItems(jogadoresB);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public CheckBox opClubeA;
    @FXML
    public CheckBox opClubeB;
    @FXML
    public TextField opResultA;
    @FXML
    public TextField opResultB;
    @FXML
    public TextField opTempo;
    @FXML
    public Label placarA;
    @FXML
    public Label placarB;
    @FXML
    private ChoiceBox<Jogador> jogadoresClubeA;
    @FXML
    private ChoiceBox<Jogador> jogadoresClubeB;

    @FXML
    private TableColumn<Evento, String> eventClube;

    @FXML
    private TableColumn<Evento, String> eventID;

    @FXML
    private TableView<Evento> eventTable;

    @FXML
    private TableColumn<Evento, String> eventTempo;

    @FXML
    private TableColumn<Evento, String> eventJogador;

    @FXML
    private TableColumn<Evento, Integer> eventOrder;

    ObservableList<Evento> eventList = FXCollections.observableArrayList(new Evento());
    private void atualizarTabelaEventos(){          // ATUALIZA A TABELA EVENTO

        try {
            eventList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Ordem as Ordem, Evento.Descricao as Evento, Jogador.Nome as NomeJogador, Clube.Nome as Clube, Hora as Tempo\n" +
                    "From EventoJogo\n" +
                    "Inner Join Evento on Evento.ID_Evento = EventoJogo.ID_Evento\n" +
                    "Inner Join Jogador on Jogador.Id_Jogador = EventoJogo.ID_Jogador\n" +
                    "Inner Join Clube on Clube.Id_Clube = EventoJogo.ID_Clube WHERE Id_Jogo = " + jogID);

            while(rs.next()){
                eventList.add(new Evento(
                                rs.getInt("Ordem"),
                                rs.getString("Evento"),
                                rs.getString("NomeJogador"),
                                rs.getString("Clube"),
                                rs.getString("Tempo")
                        )
                );
            }
            conn.close();

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRemoveButton(ActionEvent actionEvent) {       // Remove o Evento
        Evento Evento = eventTable.getSelectionModel().getSelectedItem();
        int id = Evento.getEventoOrdem();

        eventList.remove(Evento);
        EventoDAO.delete(id);
    }


    public void onUpdateButton(ActionEvent actionEvent) {       // ATUALIZA A TABELA EVENTO
        atualizarTabelaEventos();
    }

    public void onInitButton(ActionEvent actionEvent) {         // INICIA O JOGO
        iniciarJogo();
    }
    @FXML
    public void onEndButton(ActionEvent actionEvent) {          // ENCERRA O JOGO
        showAlertConfirm();
        backTab();
    }
    @FXML
    public void onResumeButton(ActionEvent actionEvent) {           // CONTINUA O JOGO
        timer.start();
    }
    @FXML
    public void onYellowButton(ActionEvent actionEvent) {           // CARTÃO AMARELO
        registerCA();
    }
    @FXML
    public void onRedButton(ActionEvent actionEvent) {              // CARTÃO VERMELHA
        registerCV();
    }
    @FXML
    public void onReplaceButton(ActionEvent actionEvent) {              // SUBSTITUIÇÃO
        registerRE();
    }
    @FXML
    public void onGoalButton(ActionEvent actionEvent) {                 // MARCA O GOLO
        registerGO();
    }
    @FXML
    public void onDeleteGoal(ActionEvent actionEvent) {                 // DELETA O GOLO
    }

    @FXML
    void onCheckA() {                   // CHECKBOX DO CLUBE A
        idClubeEvento = idClubeA;
        opClubeB.setSelected(false);
    }

    @FXML
    void onCheckB() {                   // CHECKBOX DO CLUBE B
        idClubeEvento = idClubeB;
        opClubeA.setSelected(false);
    }

    String jogadorSelecionadoString;
    int jogadorSelecionado;
    @FXML
    public void registerCA() {                   // REGISTA O CARTÃO AMARELO
        timer.stop();
        System.out.println("---------- CARTÃO AMARELO ----------");

        System.out.println("3");
        System.out.println(idClubeEvento);
        if (idClubeEvento == idClubeA){  // DETERMINA O CLUBE SELECIONADO NA CHECKBOX
            jogadorSelecionadoString = String.valueOf(jogadoresClubeA.getSelectionModel().getSelectedItem());
        } else {
            jogadorSelecionadoString = String.valueOf(jogadoresClubeB.getSelectionModel().getSelectedItem());
        }

        try { // PEGAR O ID DO JOGADOR
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Jogador from Jogador Where Nome LIKE '" + jogadorSelecionadoString + "'");
            while (rs.next()) {
                rs.getInt("Id_Jogador");
                jogadorSelecionado = rs.getInt("Id_Jogador");
            }
            System.out.println(jogadorSelecionado);
            conn.close();
        } catch (Exception e) {
        }
        System.out.println(jogID);
        System.out.println(ddMinute + ":" + ddSecond);

        Evento ev = new Evento(3, jogID, jogadorSelecionado, idClubeEvento, ddMinute + ":" + ddSecond);
        EventoDAO evDao = new EventoDAO();
        evDao.create(ev);

        atualizarTabelaEventos();
    }
    @FXML
    public void registerCV() {                  // REGISTA O CARTÃO VERMELHO
        timer.stop();
        System.out.println("---------- CARTÃO VERMELHO ----------");
        System.out.println("6");
        System.out.println(idClubeEvento);
        if (idClubeEvento == idClubeA){  // DETERMINA O CLUBE SELECIONADO NA CHECKBOX
            jogadorSelecionadoString = String.valueOf(jogadoresClubeA.getSelectionModel().getSelectedItem());
        } else {
            jogadorSelecionadoString = String.valueOf(jogadoresClubeB.getSelectionModel().getSelectedItem());
        }

        try { // PEGAR O ID DO JOGADOR
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Jogador from Jogador Where Nome LIKE '" + jogadorSelecionadoString + "'");
            while (rs.next()) {
                rs.getInt("Id_Jogador");
                jogadorSelecionado = rs.getInt("Id_Jogador");
            }
            System.out.println(jogadorSelecionado);
            conn.close();
        } catch (Exception e) {
        }
        System.out.println(jogID);
        System.out.println(ddMinute + ":" + ddSecond);

        Evento ev = new Evento(6, jogID, jogadorSelecionado, idClubeEvento, ddMinute + ":" + ddSecond);
        EventoDAO evDao = new EventoDAO();
        evDao.create(ev);

        atualizarTabelaEventos();
    }

    @FXML
    public void registerRE() {                  // REGISTA A SUBSTITUIÇÃO
        timer.stop();
        System.out.println("---------- SUBSTITUIÇÃO ----------");
        System.out.println("4");
        System.out.println(idClubeEvento);
        if (idClubeEvento == idClubeA){  // DETERMINA O CLUBE SELECIONADO NA CHECKBOX
            jogadorSelecionadoString = String.valueOf(jogadoresClubeA.getSelectionModel().getSelectedItem());
        } else {
            jogadorSelecionadoString = String.valueOf(jogadoresClubeB.getSelectionModel().getSelectedItem());
        }

        try { // PEGAR O ID DO JOGADOR
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Jogador from Jogador Where Nome LIKE '" + jogadorSelecionadoString + "'");
            while (rs.next()) {
                rs.getInt("Id_Jogador");
                jogadorSelecionado = rs.getInt("Id_Jogador");
            }
            System.out.println(jogadorSelecionado);
            conn.close();
        } catch (Exception e) {
        }
        System.out.println(jogID);
        System.out.println(ddMinute + ":" + ddSecond);

        Evento ev = new Evento(4, jogID, jogadorSelecionado, idClubeEvento, ddMinute + ":" + ddSecond);
        EventoDAO evDao = new EventoDAO();
        evDao.create(ev);

        atualizarTabelaEventos();
    }

    @FXML
    public void registerGO() {                   // REGISTA O GOLO
        timer.stop();
        System.out.println("---------- GOLO ----------");
        System.out.println("5");
        System.out.println(idClubeEvento);
        if (idClubeEvento == idClubeA){  // DETERMINA O CLUBE SELECIONADO NA CHECKBOX
            jogadorSelecionadoString = String.valueOf(jogadoresClubeA.getSelectionModel().getSelectedItem());
            resultA++;
            opResultA.setText(String.valueOf(resultA));
        } else {
            jogadorSelecionadoString = String.valueOf(jogadoresClubeB.getSelectionModel().getSelectedItem());
            resultB++;
            opResultB.setText(String.valueOf(resultB));
        }

        try { // PEGAR O ID DO JOGADOR
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Id_Jogador from Jogador Where Nome LIKE '" + jogadorSelecionadoString + "'");
            while (rs.next()) {
                rs.getInt("Id_Jogador");
                jogadorSelecionado = rs.getInt("Id_Jogador");
            }
            System.out.println(jogadorSelecionado);
            conn.close();
        } catch (Exception e) {
        }
        System.out.println(jogID);
        System.out.println(ddMinute + ":" + ddSecond);

        Evento ev = new Evento(5, jogID, jogadorSelecionado, idClubeEvento, ddMinute + ":" + ddSecond);
        EventoDAO evDao = new EventoDAO();
        evDao.create(ev);

        atualizarTabelaEventos();
    }


}
