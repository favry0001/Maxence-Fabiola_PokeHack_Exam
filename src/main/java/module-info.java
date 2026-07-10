module com.example.maxencefabiola_pokehack {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.maxencefabiola_pokehack to javafx.fxml;
    opens com.example.maxencefabiola_pokehack.controller to javafx.fxml;

    exports com.example.maxencefabiola_pokehack;
}
