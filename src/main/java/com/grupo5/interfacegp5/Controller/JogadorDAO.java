package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Jogador;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogadorDAO {
        public boolean create(Jogador p) {        // construtor que insere jogadores à base de dados através da Classe Jogador
            Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

            String insertSQL = "INSERT INTO Jogador (Nome, DataNascimento, Telefone, Email, CC, IBAN, Nacionalidade) VALUES ('"        // query do sql
                    + p.getJogadorName() + "','"
                    + p.getJogadorNascimento() + "','"
                    + p.getJogadorTelefone() + "','"
                    + p.getJogadorEmail() + "','"
                    + p.getJogadorCC() + "','"
                    + p.getJogadorIban() + "','"
                    + p.getJogadorNacionalidade() + "')";       // fim da query
            try {       // tenta inserir
                assert conn != null;
                Statement st = conn.createStatement();      // caso consiga
                st.executeUpdate(insertSQL);                // executa o método "insertSQL"
                return true;                                // e retorna "true"
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, "Inserção de dados", ex);
                return false;
            }       // caso contrário, retorna false e uma mensagem de erro
        }

    /*
    public boolean update(Jogador p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Jogador SET " +
                "`jogadorID` = " + p.getJogadorID() + ", `jogadorName` = " + p.getJogadorName() + ", `jogadorNascimento` = " + p.getJogadorNascimento() + ", `jogadorFunction` = " + p.getJogadorRole() + "WHERE `jogadorID` = " + p.getJogadorID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ----------------------------------------------
    A ALTERAÇÃO DOS DADOS AINDA NÃO ESTÁ FUNCIONAL
    ----------------------------------------------
    */

        // public boolean delete(Jogador p) {

        public static boolean delete(int id) {     // deleta os dados com base na ID inserida pelo jogador do programa
            Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

            // REMOVER DADOS NA BASE DE DADOS

            String deleteSQL = "DELETE FROM Jogador WHERE ID_Jogador = " + id;        // query de delete
            try {
                assert conn != null;
                Statement st = conn.createStatement();
                st.executeUpdate(deleteSQL);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        public Jogador readID(int jogadorID) {       // lê o jogador com base no ID inserido
            Jogador p = new Jogador();          // novo construtor que permite ser chamado no controlador
            Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
            String selectSQL = "SELECT * FROM Jogador WHERE ID_Jogador = " + jogadorID;      // query do sql
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);
                while (rs.next()) {                                             // enquanto houver resultados, executa
                    p.setJogadorID(rs.getInt("ID_Jogador"));
                    p.setJogadorName(rs.getString("Nome"));
                    p.setJogadorNascimento(rs.getString("DataNascimento"));
                    p.setJogadorTelefone(rs.getInt("Telefone"));
                    p.setJogadorEmail(rs.getString("Email"));
                    p.setJogadorCC(rs.getString("CC"));
                    p.setJogadorIban(rs.getInt("IBAN"));
                    p.setJogadorNacionalidade(rs.getString("Nacionalidade"));
                }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return p;       // retorna todos os dados
        }

        public Jogador nivelAcesso(int jogadorID) {       // lê o jogador com base no ID inserido
            Jogador p = new Jogador();          // novo construtor que permite ser chamado no controlador
            Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
            String selectSQL = "SELECT * FROM jogador WHERE ID_Jogador = " + jogadorID;      // query do sql
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);
                while (rs.next()) {                                             // enquanto houver resultados, executa
                    p.setJogadorName(rs.getString("Nome"));
                    p.setJogadorNascimento(rs.getString("DataNascimento"));
                    p.setJogadorTelefone(rs.getInt("Telefone"));
                    p.setJogadorEmail(rs.getString("Email"));
                    p.setJogadorCC(rs.getString("CC"));
                    p.setJogadorIban(rs.getInt("IBAN"));
                    p.setJogadorNacionalidade(rs.getString("Nacionalidade"));
                }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, "Query Inválida", ex);
            }
            return p;       // retorna todos os dados
        }

        public List<Jogador> lerTodas() {                     // lista todos os dados
            List<Jogador> jogadorList = new ArrayList<>();
            return jogadorList;
        }




}
