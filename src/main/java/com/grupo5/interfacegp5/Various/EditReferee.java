package com.grupo5.interfacegp5.Various;

import com.grupo5.interfacegp5.JavaFXController.EditRefereeController;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.Arbitro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditReferee extends MainApplication {
    private static Stage stage;

    public EditReferee(Arbitro a1) {
        EditRefereeController.setA(a1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("editor-referee.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 381, 668);
        stage.setTitle("Editar Árbitro");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditReferee.stage = stage;
    }
}
