module gruppog3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;


    opens gruppog3 to javafx.fxml;
    exports gruppog3;
}