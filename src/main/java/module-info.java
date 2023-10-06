module com.grupo5.interfacegp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens com.grupo5.interfacegp5 to javafx.fxml, javafx.base;
    exports com.grupo5.interfacegp5;
    exports com.grupo5.interfacegp5.Model;
    exports com.grupo5.interfacegp5.JavaFXController;
    opens com.grupo5.interfacegp5.JavaFXController to javafx.fxml;
}

