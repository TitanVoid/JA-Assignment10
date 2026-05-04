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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    private Timeline timer;
    private int timeLeft;
    private String nomeUtente;
    private String cognomeUtente;
    private int numeroTentativi;

    @FXML
    private Label questionLabel;
    @FXML
    private TextField answerTF;
    @FXML
    private Label roundLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Button fattoBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resultMap = new HashMap<>();
        this.questionGenerator = new NumericQuestion();
        this.currentQuestion = 0;
        this.timeLeft = 30;
        answerTF.setTextFormatter(new TextFormatter<Integer>(s-> s.getControlNewText().matches("[+-]?\\d*") ? s : null));

        fattoBtn.disableProperty().bind(answerTF.textProperty().isEmpty());
        timerLabel.setText(String.valueOf(timeLeft));
    }


    public void initData(String nome, String cognome, int tentativi){
        this.nomeUtente = nome;
        this.cognomeUtente = cognome;
        this.numeroTentativi = tentativi;
    }


    public void startQuiz(int questionCount){
        this.questionCount = questionCount;
        nextQuestion();
    }

    private void startTimer(){
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            if (timeLeft <= 0) {
                timer.stop();
                timerLabel.setText("30");
                timerFinished();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void timerFinished() {
        NumericQuestionAttempt attempt = new NumericQuestionAttempt(questionGenerator, -9999);
        this.resultMap.put(currentQuestion, attempt);
        answerTF.clear();
        nextQuestion();
    }

    private void nextQuestion(){
        if (timer != null) timer.stop();
        if (currentQuestion >= questionCount) {
            endQuiz();
            return;
        }
        questionGenerator = new NumericQuestion();
        questionGenerator.randomInit();
        roundLabel.setText(++currentQuestion + "/" + questionCount);
        questionLabel.setText(questionGenerator.toString());
        timeLeft = 30;
        startTimer();
    }

    private void endQuiz(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewView.fxml"));
            Parent root = loader.load();
            ReviewController controller = loader.getController();
            controller.initData(nomeUtente, cognomeUtente, resultMap);
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
