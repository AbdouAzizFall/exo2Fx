module com.example.fx1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.fx1.model;


    opens com.example.fx1 to javafx.fxml;
    exports com.example.fx1;
}