package gruppog3;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

public class ReviewController {

    @FXML
    private TableView resultsTable;

    @FXML
    private TableView<NumericQuestionAttempt> tableView;

    @FXML
    private TableColumn<NumericQuestionAttempt, String> TryClm;

    @FXML
    private TableColumn<NumericQuestionAttempt, String> ResultClm;
    
    @FXML
    private Label reviewLabel;

    @FXML
    private Label messageLabel;

    private Collection<NumericQuestionAttempt> attemptsList;

    public void initData(String nome, String cognome, Map<Integer, NumericQuestionAttempt> attempts){
       this.attemptsList = attempts.values();
       messageLabel.setText(String.format("Gentile %s %s, grazie per aver completato il quiz. Esporta i tuoi risultati su file.", nome, cognome));

       TryClm.setCellValueFactory(cellData -> {
           NumericQuestionAttempt attempt = cellData.getValue();
           String domanda = attempt.getQuestion().toString();
           String tentativo = domanda.replace("?", String.valueOf(attempt.getGivenAnswer()));
           return new SimpleStringProperty(tentativo);

       });

       ResultClm.setCellValueFactory(cellData -> {
           return new SimpleStringProperty(cellData.getValue().getResult());
       });

       ObservableList<NumericQuestionAttempt> observableList = FXCollections.observableArrayList(this.attemptsList);
       resultsTable.setItems(observableList);
       
    }

    @FXML
    public void exportAction(){
        Stage stage = (Stage) resultsTable.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Esporta risultati Quiz");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di testo (.txt)", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            salvaSuFile(file);
        }
    }

    private void salvaSuFile(File file){
        try(PrintWriter writer = new PrintWriter(file)){
            writer.println("TENTATIVO;\tRISULTATO;\tESITO");
            for(NumericQuestionAttempt attempt : attemptsList){
                String tentativo = attempt.getQuestion().toString().replace("?", String.valueOf(attempt.getGivenAnswer()));
                int risultatoCorretto = attempt.getQuestion().getResult();
                String esito = attempt.getResult();

                writer.printf("%s;\t%d;\t%s%n", tentativo, risultatoCorretto, esito);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
