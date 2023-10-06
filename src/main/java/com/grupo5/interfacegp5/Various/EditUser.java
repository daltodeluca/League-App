package com.grupo5.interfacegp5.Various;

import com.grupo5.interfacegp5.JavaFXController.EditUserController;
import com.grupo5.interfacegp5.MainApp;
import com.grupo5.interfacegp5.MainApplication;
import com.grupo5.interfacegp5.Model.Utilizador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditUser extends MainApplication {
    private static Stage stage;

    public EditUser(Utilizador u1){
        EditUserController.setU(u1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("editor-user.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 381, 668);
        stage.setTitle("Editar Utilizador");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditUser.stage = stage;
    }
}
