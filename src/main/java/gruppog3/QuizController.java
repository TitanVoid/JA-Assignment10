package gruppog3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class QuizController implements Initializable {
    private int questionCount;
    private Map<String, String> resultMap;
    private int currentQuestion;
    private NumericQuestion questionGenerator;

    @FXML
    private Label questionLabel;
    @FXML
    private TextField answerTF;
    @FXML
    private Label roundLabel;
    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resultMap = new HashMap<>();
        this.questionGenerator = new NumericQuestion();
        this.currentQuestion = 0;
    }

    public void startQuiz(int questionCount){
        this.questionCount = questionCount;
    }

    private void nextQuestion(){
        questionGenerator.randomInit();
        roundLabel.setText(++currentQuestion + "/" + questionCount);
        questionLabel.setText(questionGenerator.toString());
    }

    @FXML
    private void advanceAction(ActionEvent e) {

    }
}
