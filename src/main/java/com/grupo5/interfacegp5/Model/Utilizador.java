package com.grupo5.interfacegp5.Model;

/**
 * Criação da Classe referente a tudo o que é pessoa no programa
 * <p>
 * ALTERAR:  INGLÊS -> PORTUGUÊS,
 */

public class Utilizador {
    public int userID;
    public String userName;

    // private int idade;  // Não há necessidade de haver uma variável para idade quando se existe a variável Data de Nascimento

    public String userNascimento;
    public int userTelefone;
    public String userEmail;
    public String userCC;
    public int userIban;

    public int userCode;
    public String userPassword;
    public int userAccessLevel;


    public Utilizador(String userName, String userNascimento, int userTelefone, String userEmail, String userCC, int userIban, int userCode, String userPassword, int userAccessLevel) {
        this.userName = userName;
        this.userNascimento = userNascimento;
        this.userTelefone = userTelefone;
        this.userEmail = userEmail;
        this.userCC = userCC;
        this.userIban = userIban;
        this.userCode = userCode;
        this.userPassword = userPassword;
        this.userAccessLevel = userAccessLevel;
    }

    public Utilizador() {

    }


    public Utilizador(String userName, String userNascimento, int userTelefone, String userEmail, String userCC, int userIban, int userAccessLevel) {
        this.userName = userName;
        this.userNascimento = userNascimento;
        this.userTelefone = userTelefone;
        this.userEmail = userEmail;
        this.userCC = userCC;
        this.userIban = userIban;
        this.userAccessLevel = userAccessLevel;
    }

    public Utilizador(int userID, String userName, String userNascimento, int userTelefone, String userEmail, String userCC, int userIban, int userCode, int userAccessLevel) {
        this.userID = userID;
        this.userName = userName;
        this.userNascimento = userNascimento;
        this.userTelefone = userTelefone;
        this.userEmail = userEmail;
        this.userCC = userCC;
        this.userIban = userIban;
        this.userCode = userCode;
        this.userAccessLevel = userAccessLevel;
    }

    public Utilizador(int id, String userNome, String userNascimento, String userTelefone, String userEmail, String userCC, String userIban, String userCode, String userAcessLevel) {
        this.userID = userID;
        this.userName = userName;
        this.userNascimento = userNascimento;
        this.userTelefone = Integer.parseInt(userTelefone);
        this.userEmail = userEmail;
        this.userCC = userCC;
        this.userIban = Integer.parseInt(userIban);
        this.userCode = Integer.parseInt(userCode);
        this.userAccessLevel = userAccessLevel;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNascimento() {
        return userNascimento;
    }

    public void setUserNascimento(String userNascimento) {
        this.userNascimento = userNascimento;
    }

    public int getUserTelefone() {
        return userTelefone;
    }

    public void setUserTelefone(int userTelefone) {
        this.userTelefone = userTelefone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCC() {
        return userCC;
    }

    public void setUserCC(String userCC) {
        this.userCC = userCC;
    }

    public int getUserIban() {
        return userIban;
    }

    public void setUserIban(int userIban) {
        this.userIban = userIban;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAccessLevel() {
        return userAccessLevel;
    }

    public void setUserAccessLevel(int userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }

    @Override
    public String toString() {
        return "Nome: " + userName +
                ", Data de Nascimento: " + userNascimento +
                ", Telefone: " + userTelefone +
                ", Email: " + userEmail +
                ", CC: " + userCC +
                ", IBAN: " + userIban +
                ", Código de Acesso: " + userCode;

    }
}
