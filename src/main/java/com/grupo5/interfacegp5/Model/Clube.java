package com.grupo5.interfacegp5.Model;

public class Clube {

    private int clubeID = 0;
    private String clubeNome = "";
    private int clubeLocal = 0;
    private String clubeCidade = "";
    private String clubePais="";
    private String clubeEstadio="";

    public Clube(int clubeID, String clubeNome, int clubeLocal) {
        this.clubeID = clubeID;
        this.clubeNome = clubeNome;
        this.clubeLocal = clubeLocal;
    }

    public Clube(int clubeID, String clubeNome, String clubeCidade, String clubePais, String clubeEstadio) {
        this.clubeID = clubeID;
        this.clubeNome = clubeNome;
        this.clubeCidade = clubeCidade;
        this.clubePais = clubePais;
        this.clubeEstadio = clubeEstadio;
    }


    public Clube( String clubeNome, int clubeLocal) {
        this.clubeNome = clubeNome;
        this.clubeLocal = clubeLocal;
    }

    public Clube(String clubeNome){
        this.clubeNome = clubeNome;
    }

    public Clube(int clubeId, String clubeNome){
        this.clubeID = clubeId;
        this.clubeNome = clubeNome;
    }

    public Clube() {
    }

    public int getClubeID() {
        return clubeID;
    }

    public void setClubeID(int clubeID) {
        this.clubeID = clubeID;
    }

    public String getClubeNome() {
        return clubeNome;
    }

    public void setClubeNome(String clubeNome) {
        this.clubeNome = clubeNome;
    }

    public int getClubeLocal() {
        return clubeLocal;
    }

    public void setClubeLocal(int clubeLocal) {
        this.clubeLocal = clubeLocal;
    }

    public String getClubeCidade() {
        return clubeCidade;
    }

    public void setClubeCidade(String clubeCidade) {
        this.clubeCidade = clubeCidade;
    }

    public String getClubePais() {
        return clubePais;
    }

    public void setClubePais(String clubePais) {
        this.clubePais = clubePais;
    }

    public String getClubeEstadio() {
        return clubeEstadio;
    }

    public void setClubeEstadio(String clubeEstadio) {
        this.clubeEstadio = clubeEstadio;
    }

    @Override
    public String toString() {
        return "Clube{" +
                "clubeID=" + clubeID +
                ", clubeNome='" + clubeNome + '\'' +
                ", clubeLocal='" + clubeLocal + '\'' +
                '}';
    }
}