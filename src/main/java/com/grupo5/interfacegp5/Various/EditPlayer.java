package com.grupo5.interfacegp5.Various;

import com.grupo5.interfacegp5.JavaFXController.EditPlayerController;
import com.grupo5.interfacegp5.MainApp;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditPlayer extends MainApplication {
    private static Stage stage;

    public EditPlayer(Jogador p1){
        EditPlayerController.setP(p1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("editor-player.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 381, 668);
        stage.setTitle("Editar Jogador");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditPlayer.stage = stage;
    }
}

