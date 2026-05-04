package gruppog3;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Time;
import java.util.*;

public class QuizController implements Initializable {
    private int questionCount;
    private Map<Integer, NumericQuestionAttempt> resultMap;
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
        nextQuestion();
    }

    private void nextQuestion(){
        if (currentQuestion >= questionCount) {
            endQuiz();
            return;
        }
        questionGenerator.randomInit();
        roundLabel.setText(++currentQuestion + "/" + questionCount);
        questionLabel.setText(questionGenerator.toString());
    }

    private void endQuiz(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewView.fxml"));
            Parent root = loader.load();
            ReviewController controller = loader.getController();
            Stage stage = (Stage) timerLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void advanceAction(ActionEvent e) {
        NumericQuestionAttempt attempt = new NumericQuestionAttempt(questionGenerator, Integer.parseInt(answerTF.getText()));
        this.resultMap.put(currentQuestion, attempt);
        answerTF.clear();
        nextQuestion();
    }
}
