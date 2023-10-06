package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Clube;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClubeDAO {
        public boolean create(Clube p) {        // construtor que insere clubees à base de dados através da Classe Clube
            Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

            String insertSQL = "INSERT INTO Clube (Nome, Id_Local) VALUES ('"        // query do sql
                    + p.getClubeNome() + "','"
                    + p.getClubeLocal() + "')";       // fim da query
            try {       // tenta inserir
                assert conn != null;
                Statement st = conn.createStatement();      // caso consiga
                st.executeUpdate(insertSQL);                // executa o método "insertSQL"
                return true;                                // e retorna "true"
            } catch (SQLException ex) {
                Logger.getLogger(ClubeDAO.class.getName()).log(Level.SEVERE, "Inserção de dados", ex);
                return false;
            }       // caso contrário, retorna false e uma mensagem de erro
        }

    /*
    public boolean update(Clube p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Clube SET " +
                "`clubeID` = " + p.getClubeID() + ", `clubeName` = " + p.getClubeName() + ", `clubeNascimento` = " + p.getClubeNascimento() + ", `clubeFunction` = " + p.getClubeRole() + "WHERE `clubeID` = " + p.getClubeID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClubeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ----------------------------------------------
    A ALTERAÇÃO DOS DADOS AINDA NÃO ESTÁ FUNCIONAL
    ----------------------------------------------
    */

        // public boolean delete(Clube p) {

        public static boolean delete(int id) {     // deleta os dados com base na ID inserida pelo clube do programa
            Connection conn = DBConnect.getConnectionMySQL();       // faz a conexão com a base de dados através do método inserido na DBConnect

            // REMOVER DADOS NA BASE DE DADOS

            String deleteSQL = "DELETE FROM Clube WHERE ID_Clube = " + id;        // query de delete
            try {
                assert conn != null;
                Statement st = conn.createStatement();
                st.executeUpdate(deleteSQL);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ClubeDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        /*

        public Clube readID(int id) {       // lê o clube com base no ID inserido
            Clube p = new Clube(rs.getInt("Clube.ID_Clube"), rs.getString("Clube.Nome"), rs.getString("Local.Cidade"), rs.getString("Local.Pais"), rs.getString("Local.NomeEstadio"));          // novo construtor que permite ser chamado no controlador
            Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
            String selectSQL = "SELECT * FROM Clube WHERE clubeID = " + id;      // query do sql
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);
                while (rs.next()) {                                             // enquanto houver resultados, executa
                    p.setClubeID(rs.getInt("ID_Clube"));
                    p.setClubeNome(rs.getString("Nome"));
                    p.setClubeLocal(rs.getInt("Id_Local"));
                }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
            } catch (SQLException ex) {
                Logger.getLogger(ClubeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return p;       // retorna todos os dados
        }


         */

        /*

        public Clube nivelAcesso(int clubeID) {       // lê o clube com base no ID inserido
            Clube p = new Clube(rs.getInt("Clube.ID_Clube"), rs.getString("Clube.Nome"), rs.getString("Local.Cidade"), rs.getString("Local.Pais"), rs.getString("Local.NomeEstadio"));          // novo construtor que permite ser chamado no controlador
            Connection conn = DBConnect.getConnectionMySQL();       // tenta conexão com base de dados
            String selectSQL = "SELECT * FROM clube WHERE Cod_clube = " + clubeID;      // query do sql
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);
                while (rs.next()) {                                             // enquanto houver resultados, executa
                    p.setClubeID(rs.getInt("ID_Clube"));
                    p.setClubeNome(rs.getString("Nome"));
                    p.setClubeCidade(rs.getString("Local.Cidade"));
                    p.setClubePais(rs.getString("Local.Pais"));
                    p.setClubeEstadio(rs.getString("Local.NomeEstadio"));
                }                                                               // escolhe os dados que serão mostrados ao realizar método "read()"
            } catch (SQLException ex) {
                Logger.getLogger(ClubeDAO.class.getName()).log(Level.SEVERE, "Query Inválida", ex);
            }
            return p;       // retorna todos os dados
        }


         */

        public List<Clube> lerTodas() {                     // lista todos os dados
            List<Clube> clubeList = new ArrayList<>();
            return clubeList;
        }





}
