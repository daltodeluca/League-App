package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Arbitro;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class
ArbitroDAO {
    public boolean create(Arbitro p) {        // construtor que insere arbitroes à base de dados através da Classe Arbitro
        Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

        String insertSQL = "INSERT INTO Arbitro (Nome, DataNascimento, Telefone, Email, CC, IBAN) VALUES ('"        // query do sql
                + p.getArbitroName() + "','"
                + p.getArbitroNascimento() + "','"
                + p.getArbitroTelefone() + "','"
                + p.getArbitroEmail() + "','"
                + p.getArbitroCC() + "','"
                + p.getArbitroIban() + "')";       // fim da query
        try {       // tenta inserir
            assert conn != null;
            Statement st = conn.createStatement();      // caso consiga
            st.executeUpdate(insertSQL);                // executa o método "insertSQL"
            return true;                                // e retorna "true"
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, "Inserção de dados", ex);
            return false;
        }       // caso contrário, retorna false e uma mensagem de erro
    }

    /*
    public boolean update(Arbitro p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Arbitro SET " +
                "`arbitroID` = " + p.getArbitroID() + ", `arbitroName` = " + p.getArbitroName() + ", `arbitroNascimento` = " + p.getArbitroNascimento() + ", `arbitroFunction` = " + p.getArbitroRole() + "WHERE `arbitroID` = " + p.getArbitroID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ----------------------------------------------
    A ALTERAÇÃO DOS DADOS AINDA NÃO ESTÁ FUNCIONAL
    ----------------------------------------------
    */

    // public boolean delete(Arbitro p) {

    public static boolean delete(int id) {     // deleta os dados com base na ID inserida pelo arbitro do programa
        Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

        // REMOVER DADOS NA BASE DE DADOS

        String deleteSQL = "DELETE FROM Arbitro WHERE ID_Arbitro = " + id;        // query de delete
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Arbitro readID(int id) {       // lê o arbitro com base no ID inserido
        Arbitro p = new Arbitro();          // novo construtor que permite ser chamado no controlador
        Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
        String selectSQL = "SELECT * FROM Arbitro WHERE arbitroID = " + id;      // query do sql
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {                                             // enquanto houver resultados, executa
                p.setArbitroID(rs.getInt("arbitroID"));
                p.setArbitroName(rs.getString("Nome"));
                p.setArbitroNascimento(rs.getString("DataNascimento"));
                p.setArbitroTelefone(rs.getInt("Telefone"));
                p.setArbitroEmail(rs.getString("Email"));
                p.setArbitroCC(rs.getString("uCC"));
                p.setArbitroIban(rs.getInt("IBAN"));
            }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;       // retorna todos os dados
    }

    public Arbitro nivelAcesso(int arbitroCode) {       // lê o arbitro com base no ID inserido
        Arbitro p = new Arbitro();          // novo construtor que permite ser chamado no controlador
        Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
        String selectSQL = "SELECT * FROM arbitro WHERE Cod_arbitro = " + arbitroCode;      // query do sql
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {                                             // enquanto houver resultados, executa
                p.setArbitroName(rs.getString("Nome"));
                p.setArbitroNascimento(rs.getString("DataNascimento"));
                p.setArbitroTelefone(rs.getInt("Telefone"));
                p.setArbitroEmail(rs.getString("Email"));
                p.setArbitroCC(rs.getString("CC"));
                p.setArbitroIban(rs.getInt("IBAN"));
            }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, "Query Inválida", ex);
        }
        return p;       // retorna todos os dados
    }

    public List<Arbitro> lerTodas() {                     // lista todos os dados
        List<Arbitro> arbitroList = new ArrayList<>();
        return arbitroList;
    }
}



