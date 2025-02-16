module com.example.rental_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.rental_application to javafx.fxml;
    exports com.example.rental_application;
}