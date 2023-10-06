package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Evento;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDAO {
    public boolean create(Evento p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // INSERIR DADOS NA BASE DE DADOS

        String insertSQL = "INSERT INTO EventoJogo (Id_Evento, Id_Jogo, Id_Clube, Id_Jogador, Hora) VALUES ('"
                + p.getEventoIdI() + "','"
                + p.getEventoJogoI() + "','"
                + p.getEventoClubeI() + "','"
                + p.getEventoJogadorI() + "','"
                + p.getEventoTempo() + "')";
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

    // public boolean delete(Jogo p) {
    public static boolean delete(int id) {
        Connection conn = DBConnect.getConnectionMySQL();

        // REMOVER DADOS NA BASE DE DADOS

        String deleteSQL = "DELETE FROM EventoJogo WHERE Ordem = " + id;
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(deleteSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
