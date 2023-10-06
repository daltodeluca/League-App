/**
 * Classe Referente ás Estaticas dos Jogos
 */
package com.grupo5.interfacegp5.Model;

public class Estatistica{
    private int statsTotalGoals;
    private int statsReplacements;
    private String statsInterval;
    private String statsFinal;
    private int statsCanceledGoals;
    private int statsRedCards;
    private int statsYellowCards;

    public Estatistica() {

    }



    public int getStatsTotalGoals() {
        return statsTotalGoals;
    }

    public void setStatsTotalGoals(int statsTotalGoals) {
        this.statsTotalGoals = statsTotalGoals;
    }

    public int getStatsReplacements() {
        return statsReplacements;
    }

    public void setStatsReplacements(int statsReplacements) {
        this.statsReplacements = statsReplacements;
    }

    public String getStatsInterval() {
        return statsInterval;
    }

    public void setStatsInterval(String statsInterval) {
        this.statsInterval = statsInterval;
    }

    public String getStatsFinal() {
        return statsFinal;
    }

    public void setStatsFinal(String statsFinal) {
        this.statsFinal = statsFinal;
    }

    public int getStatsCanceledGoals() {
        return statsCanceledGoals;
    }

    public void setStatsCanceledGoals(int statsCanceledGoals) {
        this.statsCanceledGoals = statsCanceledGoals;
    }

    public int getStatsRedCards() {
        return statsRedCards;
    }

    public void setStatsRedCards(int statsRedCards) {
        this.statsRedCards = statsRedCards;
    }

    public int getStatsYellowCards() {
        return statsYellowCards;
    }

    public void setStatsYellowCards(int statsYellowCards) {
        this.statsYellowCards = statsYellowCards;
    }

    @Override
    public String toString() {
        return "Estatistica{" +
                "statsTotalGoals=" + statsTotalGoals +
                ", statsReplacements=" + statsReplacements +
                ", statsInterval='" + statsInterval + '\'' +
                ", statsFinal='" + statsFinal + '\'' +
                ", statsCanceledGoals=" + statsCanceledGoals +
                ", statsRedCards=" + statsRedCards +
                ", statsYellowCards=" + statsYellowCards +
                '}';
    }
}