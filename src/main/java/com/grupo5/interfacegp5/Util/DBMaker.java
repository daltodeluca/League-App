package com.grupo5.interfacegp5.Util;

import com.grupo5.interfacegp5.Controller.UtilizadorDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMaker {

        public boolean createTable(){
            Connection conn = DBConnect.getConnectionMySQL();
            String sqlCreate = "Create Database if not exists SuperLigaEuropeia;" +
                    "use SuperLigaEuropeia" +
                    "Create Table If Not Exists Utilizador(" +
                    "ID_Utilizador int Primary Key Not null auto_increment," +
                    "Nome varchar(50) not null," +
                    "DataNascimento Date," +
                    "Telefone numeric(9)," +
                    "Email varchar(50)," +
                    "CC varchar(14) not null," +
                    "IBAN numeric(9)," +
                    "Cod_utilizador numeric(6)," +
                    "Pass varchar(10)," +
                    "NivelAcesso int," +
                    "Constraint ck_Utilizadore_NivelAcesso check(NivelAcesso > 0 and NivelAcesso < 4)," +
                    "Constraint ck_Utilizador_Nome check(Nome not like '' or Nome not like ' ')," +
                    "Constraint FK_Utilizador_NivelAcesso Foreign key(NivelAcesso) references NivelAcesso(Nivel)," +
                    ");" +
                    "Create Table If Not Exists NivelAcesso(" +
                    "Nivel int Primary Key," +
                    "Descrição nvarchar(50)" +
                    ");" +
                    "Create Table If Not Exists Clube(" +
                    "ID_Clube int Primary Key Not null auto_increment," +
                    "Nome varchar(50) not null," +
                    "Pais varchar(250)not null" +
                    ");" +
                    "Create Table If Not Exists Jogador(" +
                    "ID_Jogador int Primary Key Not Null auto_increment," +
                    "Nome varchar(50) not null," +
                    "DataNascimento Date," +
                    "Telefone numeric(9)," +
                    "Email varchar(50)," +
                    "CC varchar(14) not null," +
                    "IBAN numeric(9) not null," +
                    "constraint ck_Jogador_Nome check(Nome not like '' or Nome not like ' ')" +
                    ");" +
                    "Create Table If Not Exists Arbitro(" +
                    "ID_Arbitro int Primary Key Not null auto_increment," +
                    "Nome varchar(50) not null," +
                    "DataNascimento Date," +
                    "Telefone numeric(9)," +
                    "Email varchar(50)," +
                    "CC varchar(14) not null," +
                    "IBAN numeric(9) not null," +
                    "constraint ck_Arbitro_Nome check(Nome not like '' or Nome not like ' ')" +
                    ");" +
                    "Create Table If Not Exists ClubeJogador(" +
                    "ID_Jogador int NOT NULL," +
                    "ID_Clube int NOT NULL," +
                    "constraint pk_ClubeJogador primary key(ID_Jogador, ID_Clube)," +
                    "constraint FK_ClubeJogador_Jogador Foreign key(ID_jogador) references Jogador(ID_Jogador)," +
                    "constraint FK_ClubeJogador_Clube Foreign key(ID_Clube) references Clube(ID_Clube)" +
                    ");" +
                    "Create Table If Not Exists Jogo(" +
                    "ID_Jogo int Primary Key not null auto_increment," +
                    "DataJogo Date," +
                    "HoraInicio Time," +
                    "GolosTotal int," +
                    "Substituicao int," +
                    "GoloAnulado int," +
                    "ResultadoIntervalo varchar(5)," +
                    "ResultadoFinal varchar(5)," +
                    "Estadio varchar(50)," +
                    "Cidade varchar(50)," +
                    "Pais varchar(50)," +
                    "constraint ck_Jogo_losTotal check(losTotal < 0)," +
                    "constraint ck_Jogo_Substituicao check(Substituicao < 0)," +
                    "constraint ck_Jogo_ResultadoFinal check(ResultadoFinal like '[0-9][0-9]-[0-9][0-9]' or ResultadoFinal like '[0-9]-[0-9]')," +
                    "constraint ck_Jogo_ResultadoIntervalo check(ResultadoIntervalo like '[0-9][0-9]-[0-9][0-9]' or ResultadoIntervalo like '[0-9]-[0-9]')" +
                    ");" +
                    "Create Table If Not Exists JogoEstatistica(" +
                    "ID_Jogo int not null," +
                    "ID_Clube int not null," +
                    "Substituicao int," +
                    "GoloAnulado int," +
                    "Golos int," +
                    "PassesCompletos int," +
                    "PassesErrados int," +
                    "ForaJogo int," +
                    "CartaoAmarelo int," +
                    "CartaoVermelho int," +
                    "Remates int," +
                    "constraint PK_JogoEstatistica primary key(ID_Jogo, ID_Clube)," +
                    "constraint FK_JogoEstatistica_IDJogo Foreign key(ID_Jogo) references Jo(ID_Jogo)," +
                    "constraint FK_JogoEstatistica_ID_Clube Foreign key(ID_Clube) references Clube(ID_Clube)" +
                    ");" +
                    "Create table If Not Exists JogoArbitro(" +
                    "ID_Jogo int not null," +
                    "ID_Arbitro int not null," +
                    "CartaoAmarelo int," +
                    "CartaoVermelho int," +
                    "constraint PK_JogoArbitro primary key(ID_Jogo, ID_Arbitro)," +
                    "constraint FK_JogoArbitro_IDJogo Foreign key(ID_Jogo) references Jogo(ID_Jogo)," +
                    "constraint FK_JogoArbitro_IDArbitro Foreign key(ID_Arbitro) references Arbitro(ID_Arbitro)" +
                    ");" +
                    "Create Table If Not Exists Jornada(" +
                    "ID_Jornada int Primary key auto_increment," +
                    "Clubes numeric(11)," +
                    "Jogos numeric(11)," +
                    "constraint ck_Jornada_Clubes check(Clubes = 11)," +
                    "constraint ck_Jornada_Jogos check(Jogos = 11)" +
                    ");" +
                    "Create Table If Not Exists JornadaJogo(" +
                    "ID_Jornada int," +
                    "ID_Jogo int," +
                    "constraint PK_JornadaJogo Primary key(ID_Jornada, ID_Jogo)," +
                    "constraint FK_JornadaJogo_IDJornada Foreign Key(ID_Jornada) references Jornada(ID_Jornada)," +
                    "constraint FK_JornadaJogo_IDJogo Foreign key(ID_Jogo)  references Jogo(ID_Jogo)" +
                    ");";

            try {       // tenta inserir
                assert conn != null;
                Statement st = conn.createStatement();      // caso consiga
                st.executeUpdate(sqlCreate);                // executa o método "insertSQL"
                return true;                                // e retorna "true"
            } catch (SQLException ex) {
                Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, "Inserção de dados", ex);
                return false;
            }
        }
}
