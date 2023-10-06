package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Jogo;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogoDAO {
    public boolean create(Jogo p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // INSERIR DADOS NA BASE DE DADOS

        String insertSQL = "INSERT INTO Jogo (Data_Jogo, horaInicio, Local, ClubeA, ClubeB, Id_Jornada) VALUES ('"
                + p.getJogoData() + "','"
                + p.getJogoHora() + "','"
                + p.getJogoLocal() + "','"
                + p.getJogoTeamID1() + "','"
                + p.getJogoTeamID2() + "','"
                + p.getJogoJornada() + "')";
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(insertSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Jogo p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Jogo SET " +
                ", `Data_Jogo` = " + p.getJogoData() +
                ", `horaInicio` = " + p.getJogoHora() +
                ", `Local` = " + p.getJogoLocal() +
                "WHERE `ID_Jogo` = " + p.getJogoID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // public boolean delete(Jogo p) {
    public static boolean delete(int id) {
        Connection conn = DBConnect.getConnectionMySQL();

        // REMOVER DADOS NA BASE DE DADOS

        String deleteSQL = "DELETE FROM Jogo WHERE ID_Jogo = " + id;
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(deleteSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Jogo readID(int id) {
        Jogo p = new Jogo();
        Connection conn = DBConnect.getConnectionMySQL();
        String selectSQL = "SELECT * FROM Jogo WHERE ID_Jogo = " + id;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                p.setJogoID(rs.getInt("ID_Jogo"));
                p.setJogoTeamID1(rs.getInt("ClubeA"));
                p.setJogoTeamID2(rs.getInt("ClubeB"));
                p.setJogoData(rs.getString("Data_Jogo"));
                p.setJogoHora(rs.getString("horaInicio"));
                p.setJogoLocal(rs.getString("Local"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Jogo> lerTodas() {
        List<Jogo> jogoList = new ArrayList<>();
        return jogoList;
    }
}
