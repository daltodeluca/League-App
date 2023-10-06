package com.grupo5.interfacegp5.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static String status = "Não conectou...";
    public DBConnect() {
    }

    public static Connection getConnectionMySQL() {
        Connection connection = null;          // atribui um valor para poder inicializar o connection
        try {

            String serverName = "ctespbd.dei.isep.ipp.pt:1433;";    //caminho do servidor do BD
            String mydatabase = "databaseName=2022_DIAS_G5";        //nome do seu banco de dados
            String properties = "encrypt=true;trustServerCertificate=true";
            String url = "jdbc:sqlserver://" + serverName + "/" + mydatabase + properties;       // monta a url
            String username = "DIAS_UG5_2022";        //nome de um usuário de seu BD
            String password = "grupo5!BD";      //sua senha de acesso

            connection = DriverManager.getConnection(url, username, password);      // faz a conexão
            if (connection != null) {              // testa se a conexão funciona
                status = ("STATUS--->Conectado com sucesso!");      // sucesso
            }
            return connection;      // retorna o resultado da conexão

            // inserir criação de tabelas se não existir
        } catch (SQLException ex) {      // conexão não concçuída
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            ex.printStackTrace();
            return null;        // retorna "null" caso não conecte
        }

    }

    public static String statusConection() {
        return status;
    }   // retorna o status da conexão (tem que utilizar o método "statusConection()" para ver o status)

    public static boolean closeConnection() {
        try {
            DBConnect.getConnectionMySQL().close();
            return true;        // fechou com sucesso
        } catch (SQLException e) {
            return false;       // não fechou
        }
    }       // fecha a conexão com a base de dados

    public static Connection RestartConnection() {
        closeConnection();
        return DBConnect.getConnectionMySQL();
    }       // ao utilizar o método "RestartConnection()", ele reinicia a conexão com a base de dados
}