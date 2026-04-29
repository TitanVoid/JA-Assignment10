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



        textFieldName.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("\\D") ? s : null));

        textFieldName.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("\\D") ? s : null));

        textFieldName.setTextFormatter(new TextFormatter<String>(s-> s.getControlNewText().matches("\\d[0-9]{0,10}") ? s : null));




    }


    @FXML
    private void onStart() {

        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("QuizView.fxml")));
            Parent root = loader.load(); //????
            QuizController controller = loader.getController();

            Scene scene = textFieldName.getScene();
            scene.setRoot(root);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }







}
