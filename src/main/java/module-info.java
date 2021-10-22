module com.example.project207_workshop6_group4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;


    opens com.example.project207_workshop6_group4 to javafx.fxml;
    exports com.example.project207_workshop6_group4;
}