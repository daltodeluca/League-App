package com.grupo5.interfacegp5.JavaFXController;

import com.almasb.fxgl.entity.action.Action;
import com.grupo5.interfacegp5.Controller.UtilizadorDAO;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.Clube;
import com.grupo5.interfacegp5.Model.Jogador;
import com.grupo5.interfacegp5.Model.Jornada;
import com.grupo5.interfacegp5.Model.Utilizador;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MilController implements Initializable{

    @FXML
    private Label ErroLogin;
    @FXML
    private TextField TextUser;
    @FXML
    private TextField TextPass;

    //Variáveis relativas aos Tab do menu
    @FXML
    private Tab MilLoginTab;
    @FXML
    private Tab milEquipasTab;
    @FXML
    private Tab milArbitosTab;
    @FXML
    private Tab milJornadasTab;

    //Variável relativa á TabPane
    @FXML
    private TabPane menuMil;

    //Variável relativa ao index
    @FXML
    private int index = 0;


    //Variáveis Estatisticas do clube(GridPane)
    @FXML
    private TextField textFieldClubeSub;
    @FXML
    private TextField textFieldClubeGol;
    @FXML
    private TextField textFieldClubeNumForaJogo;
    @FXML
    private TextField textFieldClubeCartAmarelo;
    @FXML
    private TextField textFieldClubeCartVermelho;

    /**
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    //Variáveis relativas aos objetos das estatisticas dos clubes

    @FXML
    private Button showEstatisticaClube;

    @FXML
    protected void logOut(ActionEvent Event){
        if(index == 1){
            milEquipasTab.setDisable(true);
        }
        index = 0;
        MilLoginTab.setDisable(false);
        menuMil.getSelectionModel().select(index);

    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        atualizarTabelaEquipas();

        cNome.setCellValueFactory(new PropertyValueFactory<Clube, String>("clubeNome"));

        tabClubes.setItems(clubeList);

    }

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
    protected void loginMil(ActionEvent Event){
        //Variáveis referêntes á credências de login
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

            if(Integer.valueOf(nivelAcesso) == 3){
                MilLoginTab.setDisable(true);
                milEquipasTab.setDisable(false);
                nextTab();
                TextUser.setText("");
                TextPass.setText("");
                ErroLogin.setText("");
            } else if (nivelAcesso != "3") {
                ErroLogin.setText("Não foi possível fazer o login!");
                TextPass.setText("");
            }
        }

    }

    @FXML
    protected void backMenu(ActionEvent Event) throws IOException {
        TextUser.setText("");
        TextPass.setText("");
        ErroLogin.setText("");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Menu Inicial");
        stage.setScene(scene);
        stage.show();
        ((Stage) ErroLogin.getScene().getWindow()).close();
    }

    @FXML
    protected void nextTab(){
        index = index + 1;
        if(index == 1){
            MilLoginTab.setDisable(true);
            milEquipasTab.setDisable(false);
        }
        if(index == 2){
            milArbitosTab.setDisable(false);
            milEquipasTab.setDisable(true);
        }
        if(index == 3){
            milJornadasTab.setDisable(false);
            milEquipasTab.setDisable(true);
        }
        menuMil.getSelectionModel().select(index);
    }

    @FXML
    protected void backTab(ActionEvent Event){
        index = index - 1;
        if(index == 1){
            milEquipasTab.setDisable(false);
            milArbitosTab.setDisable(true);
        }
        if(index == 2){
            milArbitosTab.setDisable(false);
            milJornadasTab.setDisable(true);

        }
        if(index == 3){
            milJornadasTab.setDisable(false);
        }
        menuMil.getSelectionModel().select(index);
    }

    /**
     * Criação das variáveis referênte á tabela dos clubes
     */

    @FXML
    private TableView<Clube> tabClubes;

    @FXML
    private TableColumn<Clube, String> cNome;

    ObservableList<Clube> clubeList = FXCollections.observableArrayList(new Clube());

    @FXML
    private String getSelectedClube;

    /**
     * Método que atualiza a tabela referente aos clubes
     * Os Clubes são retirados da base de dados e adicionados ao vetor referente a eles mesmos
     */
    private void atualizarTabelaEquipas() {

        try {
            clubeList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Clube.Nome from Clube");

            while(rs.next()){
                clubeList.add(new Clube(
                                rs.getString("Nome")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *  Verifica se um utilizador foi selecionado e caso algum utilizador seja selecionado, estõa vai chamar o método para inserir o utilizador
     */
    /*
    @FXML
    private void mJogador(MouseEvent Event){
        Clube selected = tabClubes.getSelectionModel().getSelectedItem();

        if(selected != null){
            atualizarTabelaUtilizadores();
        }else{

        }
    }
    */

    /**
     * Criação dos parametros para inserir os jogadores na tabela
     */

    @FXML
    private TableView<Jogador> tabJogo;

    @FXML
    private TableColumn<Jogador, String> jNome;

    ObservableList<Jogador> playerList = FXCollections.observableArrayList(new Jogador());

    private void atualizarTabelaJogador(){

        try {
            playerList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Jogador");

            while(rs.next()){
                playerList.add(new Jogador(
                                rs.getString("Nome")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void showEstatisticaClube(ActionEvent Event){
        String verEquipa;

        Clube vClube = tabClubes.getSelectionModel().getSelectedItem();
        verEquipa = vClube.getClubeNome();


        showEstatisticaClube(verEquipa);
        showJogadorByClube(verEquipa);
    }


    public int idClube = 0;
    public int idJogador = 0;
    private void showEstatisticaClube(String nClube){
        //Identificar o ID do clube selecionado


        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Clube.Id_Clube from Clube Where Nome like '" + nClube + "'");


            while(rs.next()){
                idClube = rs.getInt("Id_Clube");
            }

        }catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cartão Amarelo
        int countEventoCartAmarelo = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 1 and ID_Clube = " + idClube);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoCartAmarelo = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cartão Vermelho
        int countEventoCartVermelho = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 6 and ID_Clube = " + idClube);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoCartVermelho = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Número de Substítuições
        int countEventoSubs = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 4 and ID_Clube = " + idClube);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoSubs = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Número foras de jogo
        int countEventofora = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 2 and ID_Clube = " + idClube);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventofora = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Remates Equipa
        int countEventoEquipa = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 5  and ID_Clube = " + idClube);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoEquipa = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        textFieldClubeCartAmarelo.setText(String.valueOf(countEventoCartAmarelo));
        textFieldClubeCartVermelho.setText(String.valueOf(countEventoCartVermelho));
        textFieldClubeGol.setText(String.valueOf(countEventoEquipa));
        textFieldClubeNumForaJogo.setText(String.valueOf(countEventofora));
        textFieldClubeSub.setText(String.valueOf(countEventoSubs));
    }

    private void showJogadorByClube(String nClube){
        int idClubeC = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Clube.Id_Clube from Clube Where Nome like '" + nClube + "'");


            while(rs.next()){
                idClubeC = rs.getInt("Id_Clube");
            }

        }catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            playerList.clear();

            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("SELECT Nome FROM Jogador Where Id_Clube = " + idClubeC);

            while(rs.next()){
                playerList.add(new Jogador(
                                rs.getString("Nome")
                        )
                );
            }

        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        jNome.setCellValueFactory(new PropertyValueFactory<Jogador, String>("jogadorName"));

        tabJogo.setItems(playerList);
    }
    @FXML
    private TextField textFieldJogadorGol;

    @FXML
    private TextField textFieldJogadorCartAmarelo;

    @FXML
    private TextField textFieldJogadorCartVermelho;

    @FXML
    private void showEstatisticaJogador(ActionEvent Event){
        String verJogador;

        Jogador vJogador = tabJogo.getSelectionModel().getSelectedItem();
        verJogador = vJogador.getJogadorName();

        showEstatistica(verJogador);

    }

    private void showEstatistica(String nJogador){
        //Selecionar Jogadores de um Clube


        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select Jogador.Id_Jogador from Jogador Where Nome like '" + nJogador + "'");


            while(rs.next()){
                idJogador = rs.getInt("Id_Jogador");
            }

        }catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Golos Jogador
        int countEventoGolo = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 5  and ID_Jogador = " + idJogador);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoGolo = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cartões Amarelos de um Jogador
        int countEventoCartaoAmarelo = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 3  and ID_Jogador = " + idJogador);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoCartaoAmarelo = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cartões Vermelho de um Jogador
        int countEventoCartaoVermelho = 0;

        try {
            Connection conn = DBConnect.getConnectionMySQL();
            ResultSet rs = conn.createStatement().executeQuery("Select COUNT(ID_Evento) AS contagemEvento from EventoJogo Where ID_Evento = 6  and ID_Jogador = " + idJogador);
            while (rs.next()) {
                rs.getInt("contagemEvento");
                countEventoCartaoVermelho = rs.getInt("contagemEvento");
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }


        textFieldJogadorGol.setText(String.valueOf(countEventoGolo));
        textFieldJogadorCartAmarelo.setText(String.valueOf(countEventoCartaoAmarelo));
        textFieldJogadorCartVermelho.setText(String.valueOf(countEventoCartaoVermelho));
    }

}
