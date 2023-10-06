package com.grupo5.interfacegp5.Model;

public class Jogador {
    private int jogadorID;
    private String jogadorName;
    private String jogadorNascimento;
    private int jogadorTelefone;
    private String jogadorEmail;
    private String jogadorCC;
    private int jogadorIban;
    private String jogadorNacionalidade;


    public Jogador(String jogadorName, String jogadorNascimento, int jogadorTelefone, String jogadorEmail, String jogadorCC, int jogadorIban, String jogadorNacionalidade){
        this.jogadorName = jogadorName;
        this.jogadorNascimento = jogadorNascimento;
        this.jogadorTelefone = jogadorTelefone;
        this.jogadorEmail = jogadorEmail;
        this.jogadorCC = jogadorCC;
        this.jogadorIban = jogadorIban;
        this.jogadorNacionalidade = jogadorNacionalidade;
    }

    public Jogador() {

    }

    public Jogador(String nome){
        this.jogadorName = nome;
    }

    public Jogador(int jogadorID, String jogadorName, String jogadorNascimento, int jogadorTelefone, String jogadorEmail, String jogadorCC, int jogadorIban, String jogadorNacionalidade){
        this.jogadorID = jogadorID;
        this.jogadorName = jogadorName;
        this.jogadorNascimento = jogadorNascimento;
        this.jogadorTelefone = jogadorTelefone;
        this.jogadorEmail = jogadorEmail;
        this.jogadorCC = jogadorCC;
        this.jogadorIban = jogadorIban;
        this.jogadorNacionalidade = jogadorNacionalidade;
    }

    public int getJogadorID() {
        return jogadorID;
    }

    public void setJogadorID(int jogadorID) {
        this.jogadorID = jogadorID;
    }

    public String getJogadorName() {
        return jogadorName;
    }

    public void setJogadorName(String jogadorName) {
        this.jogadorName = jogadorName;
    }

    public String getJogadorNascimento() {
        return jogadorNascimento;
    }

    public void setJogadorNascimento(String jogadorNascimento) {
        this.jogadorNascimento = jogadorNascimento;
    }

    public int getJogadorTelefone() {
        return jogadorTelefone;
    }

    public void setJogadorTelefone(int jogadorTelefone) {
        this.jogadorTelefone = jogadorTelefone;
    }

    public String getJogadorEmail() {
        return jogadorEmail;
    }

    public void setJogadorEmail(String jogadorEmail) {
        this.jogadorEmail = jogadorEmail;
    }

    public String getJogadorCC() {
        return jogadorCC;
    }

    public void setJogadorCC(String jogadorCC) {
        this.jogadorCC = jogadorCC;
    }

    public int getJogadorIban() {
        return jogadorIban;
    }

    public void setJogadorIban(int jogadorIban) {
        this.jogadorIban = jogadorIban;
    }

    public String getJogadorNacionalidade() {
        return jogadorNacionalidade;
    }

    public void setJogadorNacionalidade(String jogadorNacionalidade) {
        this.jogadorNacionalidade = jogadorNacionalidade;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "jogadorID=" + jogadorID +
                ", jogadorName='" + jogadorName + '\'' +
                ", jogadorNascimento='" + jogadorNascimento + '\'' +
                ", jogadorTelefone=" + jogadorTelefone +
                ", jogadorEmail='" + jogadorEmail + '\'' +
                ", jogadorCC='" + jogadorCC + '\'' +
                ", jogadorIban=" + jogadorIban +
                ", jogadorNacionalidade='" + jogadorNacionalidade + '\'' +
                '}';
    }
}