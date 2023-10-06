package com.grupo5.interfacegp5.TesteConsole;

import com.grupo5.interfacegp5.Util.DBConnect;

import java.sql.Connection;

/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class TesteBD {

    public static void main(String[] args) {

        Connection connection = DBConnect.getConnectionMySQL();

        /*
        Connection conn = null;

        try {

            String serverName = "ctespbd.dei.isep.ipp.pt:1433;";    //caminho do servidor do BD
            String mydatabase = "databaseName=2022_DIAS_G5";        //nome do seu banco de dados
            String properties = "encrypt=true;trustServerCertificate=true";
            String dbURL = "jdbc:sqlserver://" + serverName + "/" + mydatabase + properties;
            String user = "DIAS_UG5_2022";
            String pass = "grupo5!BD";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/
    }
}