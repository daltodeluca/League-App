package com.grupo5.interfacegp5.Various;

import com.grupo5.interfacegp5.JavaFXController.EditTeamController;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.Clube;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditTeam extends MainApplication {
    private static Stage stage;

    public EditTeam(Clube c1) {
        EditTeamController.setC(c1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("editor-team.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 381, 668);
        stage.setTitle("Editar Clube");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditTeam.stage = stage;
    }
}
