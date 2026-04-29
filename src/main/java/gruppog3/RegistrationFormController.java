package gruppog3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {


    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSurname ;

    @FXML
    private TextField textFieldQuestion ;

    @FXML
    private Button startButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        textFieldName.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("\\D{0,100}") ? s : null));

        textFieldSurname.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("\\D{0,100}") ? s : null));

        textFieldQuestion.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("[1-9]?|10") ? s : null));


        startButton.disableProperty().bind(textFieldName.textProperty().isEmpty()  );
        startButton.disableProperty().bind(textFieldSurname.textProperty().isEmpty());
        startButton.disableProperty().bind(textFieldQuestion.textProperty().isEmpty());

    }


    @FXML
    private void onStart() {

        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("QuizView.fxml")));
            loader.load();
            QuizController controller = loader.getController();

            Scene scene = textFieldName.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(scene);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }







}
