module com.example.maxencefabiola_pokehack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.maxencefabiola_pokehack to javafx.fxml;
    exports com.example.maxencefabiola_pokehack;
}