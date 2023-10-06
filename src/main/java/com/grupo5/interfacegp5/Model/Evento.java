package com.grupo5.interfacegp5.Model;

public class Evento {

    private int eventoOrdem;
    private String eventoId;
    private String eventoJogo;
    private String eventoJogador;
    private String eventoClube;
    private String eventoTempo;
    private int eventoIdI;
    private int eventoJogoI;
    private int eventoJogadorI;
    private int eventoClubeI;

    public Evento(String eventoId, String eventoJogador, String eventoClube, String eventoTempo) {
        this.eventoId = eventoId;
        this.eventoJogador = eventoJogador;
        this.eventoClube = eventoClube;
        this.eventoTempo = eventoTempo;
    }

    public Evento(int eventoOrdem, String eventoId, String eventoJogo, String eventoJogador, String eventoClube, String eventoTempo) {
        this.eventoOrdem = eventoOrdem;
        this.eventoId = eventoId;
        this.eventoJogo = eventoJogo;
        this.eventoJogador = eventoJogador;
        this.eventoClube = eventoClube;
        this.eventoTempo = eventoTempo;
    }

    public Evento(int eventoOrdem, String eventoId, String eventoJogador, String eventoClube, String eventoTempo) {
        this.eventoOrdem = eventoOrdem;
        this.eventoId = eventoId;
        this.eventoJogador = eventoJogador;
        this.eventoClube = eventoClube;
        this.eventoTempo = eventoTempo;
    }

    public Evento(int eventoIdI, int eventoJogoI, int eventoJogadorI, int eventoClubeI, String eventoTempo) {
        this.eventoIdI = eventoIdI;
        this.eventoJogoI = eventoJogoI;
        this.eventoJogadorI = eventoJogadorI;
        this.eventoClubeI = eventoClubeI;
        this.eventoTempo = eventoTempo;
    }

    public Evento() {
    }

    public int getEventoIdI() {
        return eventoIdI;
    }

    public void setEventoIdI(int eventoIdI) {
        this.eventoIdI = eventoIdI;
    }

    public int getEventoJogoI() {
        return eventoJogoI;
    }

    public void setEventoJogoI(int eventoJogoI) {
        this.eventoJogoI = eventoJogoI;
    }

    public int getEventoJogadorI() {
        return eventoJogadorI;
    }

    public void setEventoJogadorI(int eventoJogadorI) {
        this.eventoJogadorI = eventoJogadorI;
    }

    public int getEventoClubeI() {
        return eventoClubeI;
    }

    public void setEventoClubeI(int eventoClubeI) {
        this.eventoClubeI = eventoClubeI;
    }

    public String getEventoJogo() {
        return eventoJogo;
    }

    public void setEventoJogo(String eventoJogo) {
        this.eventoJogo = eventoJogo;
    }

    public int getEventoOrdem() {
        return eventoOrdem;
    }

    public void setEventoOrdem(int eventoOrdem) {
        this.eventoOrdem = eventoOrdem;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getEventoJogador() {
        return eventoJogador;
    }

    public void setEventoJogador(String eventoJogador) {
        this.eventoJogador = eventoJogador;
    }

    public String getEventoClube() {
        return eventoClube;
    }

    public void setEventoClube(String eventoClube) {
        this.eventoClube = eventoClube;
    }

    public String getEventoTempo() {
        return eventoTempo;
    }

    public void setEventoTempo(String eventoTempo) {
        this.eventoTempo = eventoTempo;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "eventoId='" + eventoId + '\'' +
                ", eventoJogador='" + eventoJogador + '\'' +
                ", eventoClube='" + eventoClube + '\'' +
                ", eventoTempo='" + eventoTempo + '\'' +
                '}';
    }
}
