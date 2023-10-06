package com.grupo5.interfacegp5.TesteConsole;

import com.grupo5.interfacegp5.Model.Utilizador;
import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TesteGet {

    public static void main(String[] args) {

        System.out.println();

}

    public List<Utilizador> getUsers() {
        Connection conn = DBConnect.getConnectionMySQL();
        {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT Nome, DataNascimento, Telefone, Email, CC, IBAN, Cod_Utilizador FROM utilizador");
                List<Utilizador> users = new ArrayList<>();
                while (rs.next()) {
                    Utilizador p = new Utilizador(
                            rs.getString("Nome"),
                            rs.getString("DataNascimento"),
                            rs.getInt("Telefone"),
                            rs.getString("Email"),
                            rs.getString("CC"),
                            rs.getInt("IBAN"),
                            rs.getInt("Cod_Utilizador"));
                    users.add(p);
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
