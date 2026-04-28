module gruppog3.jaassignment10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens gruppog3 to javafx.fxml;
    exports gruppog3;
}