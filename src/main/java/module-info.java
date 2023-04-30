module com.example.javaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.javaapp to javafx.fxml;
    exports com.example.javaapp;
    exports com.example.javaapp.view;
    opens com.example.javaapp.view to javafx.fxml;
}