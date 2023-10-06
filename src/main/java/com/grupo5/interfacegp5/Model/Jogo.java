package com.grupo5.interfacegp5.Model;

public class Jogo {

    private int jogoID;
    private int jogoTeamID1;
    private int jogoTeamID2;
    private String jogoData;
    private String jogoHora;
    private String jogoCidade;
    private String jogoPais;
    private String jogoEstadio;
    private String jogoLocal;
    private String jogoTeamA;
    private String jogoTeamB;
    private int jogoJornada;

    // criar uma tabela só para eventos com ligação ao id do jogo
    //


    public Jogo(int jogoTeamID1, int jogoTeamID2, int jogoLocal, String jogoData, String jogoHora, int jogoJornada) {
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoLocal = String.valueOf(jogoLocal);
        this.jogoTeamID1 = jogoTeamID1;
        this.jogoTeamID2 = jogoTeamID2;
        this.jogoJornada = jogoJornada;
    }

    public Jogo(int jogoID, String jogoTeamA, String jogoTeamB, String jogoEstadio, String jogoCidade, String jogoPais, String jogoData, String jogoHora, int jogoJornada) {
        this.jogoID = jogoID;
        this.jogoTeamA = jogoTeamA;
        this.jogoTeamB = jogoTeamB;
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoCidade = jogoCidade;
        this.jogoPais = jogoPais;
        this.jogoEstadio = jogoEstadio;
        this.jogoJornada = jogoJornada;
    }

    public Jogo(int jogoID, int jogoTeamID1, int jogoTeamID2, String jogoData, String jogoHora, String jogoLocal) {
        this.jogoID = jogoID;
        this.jogoTeamID1 = jogoTeamID1;
        this.jogoTeamID2 = jogoTeamID2;
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoLocal = jogoLocal;
    }

    public Jogo(int jogoTeamID1, int jogoTeamID2, String jogoData, String jogoHora, String jogoLocal) {
        this.jogoTeamID1 = jogoTeamID1;
        this.jogoTeamID2 = jogoTeamID2;
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoLocal = jogoLocal;
    }

    public Jogo(int jogoID, String jogoTeamA, String jogoTeamB, String jogoEstadio, String jogoData, String jogoHora, int jogoJornada) {
        this.jogoID = jogoID;
        this.jogoTeamA = jogoTeamA;
        this.jogoTeamB = jogoTeamB;
        this.jogoEstadio = jogoEstadio;
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoJornada = jogoJornada;
    }
    public Jogo(int jogoID, int jogoTeamID1, int jogoTeamID2, String jogoEstadio, String jogoData, String jogoHora, int jogoJornada) {
        this.jogoID = jogoID;
        this.jogoTeamID1 = jogoTeamID1;
        this.jogoTeamID2 = jogoTeamID2;
        this.jogoEstadio = jogoEstadio;
        this.jogoData = jogoData;
        this.jogoHora = jogoHora;
        this.jogoJornada = jogoJornada;
    }

    public Jogo(String jogoTeamA, String jogoTeamB) {
        this.jogoTeamA = jogoTeamA;
        this.jogoTeamB = jogoTeamB;
    }

    public Jogo() {
    }
    public int getJogoJornada() {
        return jogoJornada;
    }

    public void setJogoJornada(int jogoJornada) {
        this.jogoJornada = jogoJornada;
    }

    public String getJogoTeamA() {
        return jogoTeamA;
    }

    public void setJogoTeamA(String jogoTeamA) {
        this.jogoTeamA = jogoTeamA;
    }

    public String getJogoTeamB() {
        return jogoTeamB;
    }

    public void setJogoTeamB(String jogoTeamB) {
        this.jogoTeamB = jogoTeamB;
    }

    public int getJogoID() {
        return jogoID;
    }

    public void setJogoID(int jogoID) {
        this.jogoID = jogoID;
    }

    public int getJogoTeamID1() {
        return jogoTeamID1;
    }

    public void setJogoTeamID1(int jogoTeamID1) {
        this.jogoTeamID1 = jogoTeamID1;
    }

    public int getJogoTeamID2() {
        return jogoTeamID2;
    }

    public void setJogoTeamID2(int jogoTeamID2) {
        this.jogoTeamID2 = jogoTeamID2;
    }

    public String getJogoData() {
        return jogoData;
    }

    public void setJogoData(String jogoData) {
        this.jogoData = jogoData;
    }

    public String getJogoHora() {
        return jogoHora;
    }

    public void setJogoHora(String jogoHora) {
        this.jogoHora = jogoHora;
    }

    public String getJogoLocal() {
        return jogoLocal;
    }

    public void setJogoLocal(String jogoLocal) {
        this.jogoLocal = jogoLocal;
    }

    public String getJogoCidade() {
        return jogoCidade;
    }

    public void setJogoCidade(String jogoCidade) {
        this.jogoCidade = jogoCidade;
    }

    public String getJogoPais() {
        return jogoPais;
    }

    public void setJogoPais(String jogoPais) {
        this.jogoPais = jogoPais;
    }

    public String getJogoEstadio() {
        return jogoEstadio;
    }

    public void setJogoEstadio(String jogoEstadio) {
        this.jogoEstadio = jogoEstadio;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "jogoID=" + jogoID +
                ", jogoTeamID1=" + jogoTeamID1 +
                ", jogoTeamID2=" + jogoTeamID2 +
                ", jogoData='" + jogoData + '\'' +
                ", jogoHora='" + jogoHora + '\'' +
                ", jogoLocal='" + jogoLocal + '\'' +
                '}';
    }
}