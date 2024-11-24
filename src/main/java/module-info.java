module com.example.projetoalunoimc {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.projetoalunoimc to javafx.fxml;
    exports com.example.projetoalunoimc;
}