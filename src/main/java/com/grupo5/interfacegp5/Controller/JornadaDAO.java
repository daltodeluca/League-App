package com.grupo5.interfacegp5.Controller;

import com.grupo5.interfacegp5.Model.Jornada;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JornadaDAO {
    /**
     *
     *      @param p
     *      @return
     *
     */
    public boolean create(Jornada p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // INSERIR DADOS NA BASE DE DADOS

        String insertSQL = "INSERT INTO Jornada (Nome) VALUES ('"
                + p.getJornadaNome() + "')";
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(insertSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(com.grupo5.interfacegp5.Controller.JornadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Jornada p) {
        Connection conn = DBConnect.getConnectionMySQL();

        // ATUALIZAR DADOS NA BASE DE DADOS

        String updateSQL = "UPDATE Jornada SET " +
                "`Nome` = " + p.getJornadaNome() +
                "WHERE `Id_Jornada` = " + p.getJornadaID();
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(updateSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(com.grupo5.interfacegp5.Controller.JornadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // public boolean delete(Jornada p) {
    public static boolean delete(int id) {
        Connection conn = DBConnect.getConnectionMySQL();

        // REMOVER DADOS NA BASE DE DADOS

        String deleteSQL = "DELETE FROM Jornada WHERE Id_Jornada = " + id;
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.executeQuery(deleteSQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(com.grupo5.interfacegp5.Controller.JornadaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Jornada readID(int id) {
        Jornada p = new Jornada();
        Connection conn = DBConnect.getConnectionMySQL();
        String selectSQL = "SELECT * FROM Jornada WHERE Id_Jornada = " + id;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                p.setJornadaID(rs.getInt("Id_Jornada"));
                p.setJornadaNome(rs.getString("Nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(com.grupo5.interfacegp5.Controller.JornadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Jornada> lerTodas() {
        List<Jornada> jornadaList = new ArrayList<>();
        return jornadaList;
    }
}
