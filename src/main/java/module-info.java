module com.example.project207_workshop6_group4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project207_workshop6_group4 to javafx.fxml;
    exports com.example.project207_workshop6_group4;
}