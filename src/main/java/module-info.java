module com.example.obstacles3dgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.obstacles3dgame to javafx.fxml;
    exports com.example.obstacles3dgame;
}