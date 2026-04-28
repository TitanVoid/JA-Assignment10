module gruppog3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens gruppog3 to javafx.fxml;
    exports gruppog3;
}