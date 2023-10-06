package com.grupo5.interfacegp5.Model;

public class Jornada {

    private int jornadaID;
    private String jornadaNome;

    public Jornada(int jornadaID, String jornadaNome) {
        this.jornadaID = jornadaID;
        this.jornadaNome = jornadaNome;
    }
    public Jornada(String jornadaNome) {
        this.jornadaNome = jornadaNome;
    }
    public Jornada() {
    }

    public int getJornadaID() {
        return jornadaID;
    }

    public void setJornadaID(int jornadaID) {
        this.jornadaID = jornadaID;
    }

    public String getJornadaNome() {
        return jornadaNome;
    }

    public void setJornadaNome(String jornadaNome) {
        this.jornadaNome = jornadaNome;
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "jornadaID=" + jornadaID +
                ", jornadaNome='" + jornadaNome + '\'' +
                '}';
    }
}
