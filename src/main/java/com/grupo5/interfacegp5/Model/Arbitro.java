package com.grupo5.interfacegp5.Model;

public class Arbitro {
    private int arbitroID;
    private String arbitroName;
    private String arbitroNascimento;
    private int arbitroTelefone;
    private String arbitroEmail;
    private String arbitroCC;
    private int arbitroIban;


    public Arbitro(int arbitroID, String arbitroName, String arbitroNascimento, int arbitroTelefone, String arbitroEmail, String arbitroCC, int arbitroIban){
        this.arbitroID = arbitroID;
        this.arbitroName = arbitroName;
        this.arbitroNascimento = arbitroNascimento;
        this.arbitroTelefone = arbitroTelefone;
        this.arbitroEmail = arbitroEmail;
        this.arbitroCC = arbitroCC;
        this.arbitroIban = arbitroIban;
    }

    public Arbitro() {

    }

    public Arbitro(String arbitroName, String arbitroNascimento, int arbitroTelefone, String arbitroEmail, String arbitroCC, int arbitroIban){
        this.arbitroName = arbitroName;
        this.arbitroNascimento = arbitroNascimento;
        this.arbitroTelefone = arbitroTelefone;
        this.arbitroEmail = arbitroEmail;
        this.arbitroCC = arbitroCC;
        this.arbitroIban = arbitroIban;
    }

    public int getArbitroID() {
        return arbitroID;
    }

    public void setArbitroID(int arbitroID) {
        this.arbitroID = arbitroID;
    }

    public String getArbitroName() {
        return arbitroName;
    }

    public void setArbitroName(String arbitroName) {
        this.arbitroName = arbitroName;
    }

    public String getArbitroNascimento() {
        return arbitroNascimento;
    }

    public void setArbitroNascimento(String arbitroNascimento) {
        this.arbitroNascimento = arbitroNascimento;
    }

    public int getArbitroTelefone() {
        return arbitroTelefone;
    }

    public void setArbitroTelefone(int arbitroTelefone) {
        this.arbitroTelefone = arbitroTelefone;
    }

    public String getArbitroEmail() {
        return arbitroEmail;
    }

    public void setArbitroEmail(String arbitroEmail) {
        this.arbitroEmail = arbitroEmail;
    }

    public String getArbitroCC() {
        return arbitroCC;
    }

    public void setArbitroCC(String arbitroCC) {
        this.arbitroCC = arbitroCC;
    }

    public int getArbitroIban() {
        return arbitroIban;
    }

    public void setArbitroIban(int arbitroIban) {
        this.arbitroIban = arbitroIban;
    }

    @Override
    public String toString() {
        return "Arbitro{" +
                "arbitroID=" + arbitroID +
                ", arbitroName='" + arbitroName + '\'' +
                ", arbitroNascimento='" + arbitroNascimento + '\'' +
                ", arbitroTelefone=" + arbitroTelefone +
                ", arbitroEmail='" + arbitroEmail + '\'' +
                ", arbitroCC='" + arbitroCC + '\'' +
                ", arbitroIban=" + arbitroIban +
                '}';
    }
}
