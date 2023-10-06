package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.JavaFXController.RegisterController;
import com.grupo5.interfacegp5.Model.Utilizador;
import com.grupo5.interfacegp5.Util.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilizadorDAO {
    public boolean create(Utilizador p) {        // construtor que insere utilizadores à base de dados através da Classe Utilizador
        Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

        String insertSQL = "INSERT INTO Utilizador (Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_utilizador, Pass, NivelAcesso) VALUES ('"        // query do sql
                + p.getUserName() + "','"
                + p.getUserNascimento() + "','"
                + p.getUserTelefone() + "','"
                + p.getUserEmail() + "','"
                + p.getUserCC() + "','"
                + p.getUserIban() + "','"
                + p.getUserCode() + "','"
                + p.getUserPassword() + "','"
                + p.getUserAccessLevel() + "')";       // fim da query
        try {       // tenta inserir
            assert conn != null;
            Statement st = conn.createStatement();      // caso consiga
            st.executeUpdate(insertSQL);                // executa o método "insertSQL"
            return true;                                // e retorna "true"
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, "Inserção de dados", ex);
            return false;
        }       // caso contrário, retorna false e uma mensagem de erro
    }

    /*
    public boolean update(Utilizador p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Utilizador SET " +
                "Nome` = " + p.getUserName() + ", `DataNascimento` = " + p.getUserNascimento() + ", `userFunction` = " + p.getUserRole() + "WHERE `userID` = " + p.getUserID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ----------------------------------------------
    A ALTERAÇÃO DOS DADOS AINDA NÃO ESTÁ FUNCIONAL
    ----------------------------------------------
    */

    // public boolean delete(Utilizador p) {

    public static boolean delete(int id) {     // deleta os dados com base na ID inserida pelo utilizador do programa
        Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

        // REMOVER DADOS NA BASE DE DADOS

        String deleteSQL = "DELETE FROM Utilizador WHERE ID_Utilizador = " + id;        // query de delete
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Utilizador readID(int id) {       // lê o utilizador com base no ID inserido
        Utilizador p = new Utilizador();          // novo construtor que permite ser chamado no controlador
        Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
        String selectSQL = "SELECT * FROM Utilizador WHERE userID = " + id;      // query do sql
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {                                             // enquanto houver resultados, executa
                p.setUserID(rs.getInt("userID"));
                p.setUserName(rs.getString("Nome"));
                p.setUserNascimento(rs.getString("DataNascimento"));
                p.setUserTelefone(rs.getInt("Telefone"));
                p.setUserEmail(rs.getString("Email"));
                p.setUserCC(rs.getString("uCC"));
                p.setUserIban(rs.getInt("IBAN"));
            }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;       // retorna todos os dados
    }

    public int nivelAcesso(int userCode, String userPassword) {       // lê o utilizador com base no ID inserido
        Utilizador p = new Utilizador();          // novo construtor que permite ser chamado no controlador
        Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
        String selectSQL = "SELECT * FROM utilizador WHERE Cod_utilizador = " + userCode + " and Pass = '" + userPassword + "'";      // query do sql
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {                                             // enquanto houver resultados, executa
                p.setUserAccessLevel(rs.getInt("NivelAcesso"));
            }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, "Query Inválida", ex);
        }
        return p.getUserAccessLevel();       // retorna todos os dados
    }

    /*
    public String allUsers() throws SQLException {
        List<String> members = new ArrayList<String>();
        Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Users");
            while (rs.next()){
                setUserName(rs.getString("Nome"));
                userName = userName.trim();
                members.add(userName);
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }



    private static final String SALES_BY_DATES_SQL = ""
            + "SELECT store_nome, visa, master, diners, amex "
            + "FROM Sale "
            + "WHERE date >= ? AND date <= ?";

    private final ParametrosDeConexao params;

    public SaleDAO(ParametrosDeConexao params) {
        this.params = params;
    }
    */

    public List<Utilizador> getUsers() {
        Connection conn = DBConnect.getConnectionMySQL();
        {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_Utilizador FROM utilizador");
                List<Utilizador> users = new ArrayList<>();

                while (rs.next()) {
                    Utilizador u = new Utilizador();
                    u.setUserName(rs.getString("Nome"));
                    u.setUserNascimento(rs.getString("DataNascimento"));
                    u.setUserTelefone(rs.getInt("Telefone"));
                    u.setUserEmail(rs.getString("Email"));
                    u.setUserCC(rs.getString("CC"));
                    u.setUserIban(rs.getInt("IBAN"));
                    u.setUserCode(rs.getInt("Cod_utilizador"));
                    users.add(u);
                    System.out.println(users.get(0));
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

/*
    public List<Utilizador> imprimirListaU() {
        List<Utilizador> users = getUsers();            //chamando getSales
        StringBuilder sb = new StringBuilder();
        for (Utilizador utilizador : users) {
            sb.append(users.toString());

        }
        return users;
    }

 */

    public void insertRecord(Utilizador utilizador) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnect.getConnectionMySQL();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Utilizador " +
                     "(Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_utilizador, Pass, NivelAcesso) VALUES(?,?,?,?,?,?,?,?,?)")) {
            preparedStatement.setString(1, utilizador.getUserName());
            preparedStatement.setString(2, utilizador.getUserNascimento());
            preparedStatement.setInt(3, utilizador.getUserTelefone());
            preparedStatement.setString(4, utilizador.getUserEmail());
            preparedStatement.setString(5, utilizador.getUserCC());
            preparedStatement.setInt(6, utilizador.getUserIban());
            preparedStatement.setInt(7, utilizador.getUserCode());
            preparedStatement.setString(8, utilizador.getUserPassword());
            preparedStatement.setInt(9, utilizador.getUserAccessLevel());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public boolean validate(Integer userCode, String userPassword) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection conn = DBConnect.getConnectionMySQL();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT Cod_utilizador, Pass FROM utilizador WHERE Cod_utilizador = " + userCode + "AND Pass = " + userPassword)) {
            preparedStatement.setInt(1, userCode);
            preparedStatement.setString(2, userPassword);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<Utilizador> lerTodas() {                     // lista todos os dados
        List<Utilizador> utilizadorList = new ArrayList<>();

        return utilizadorList;
    }

    /**
     *  Criação do método getDatausers() para tentar inserir dados na tabela
     * @return
     */

    /*


    public static ObservableList<Utilizador> getDatausers() {

            ObservableList<Utilizador> userList = FXCollections.observableArrayList(new Utilizador());

            try {
                userList.clear();

                Connection conn = DBConnect.getConnectionMySQL();
                ResultSet rs = conn.createStatement().executeQuery("SELECT Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_Utilizador, NivelAcesso FROM utilizador");

                while(rs.next()){
                    userList.add(new Utilizador(
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
            } return userList;

            }

     */



}

