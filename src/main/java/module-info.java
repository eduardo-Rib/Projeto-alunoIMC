module com.example.projetoalunoimc {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    exports com.example.projetoalunoimc.gui;
    opens com.example.projetoalunoimc.gui to javafx.fxml;
    exports com.example.projetoalunoimc;
    opens com.example.projetoalunoimc to javafx.fxml;
}