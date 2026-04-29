package gruppog3;

public class NumericQuestionAttempt {

        private final NumericQuestion question;
        private final int givenAnswer;

        public NumericQuestionAttempt(NumericQuestion question, int givenAnswer) {
            this.question = question;
            this.givenAnswer = givenAnswer;
        }

        public NumericQuestion getQuestion() {
            return question;
        }

        public int getGivenAnswer() {
            return givenAnswer;
        }

        public boolean isCorrect() {
            
            return givenAnswer == question.getResult();
        }

        public String getResult() {
            return isCorrect() ? "Corretto" : "Sbagliato";
        }

        @Override
        public String toString() {
            return "Domanda: " + question +
                    " | Risposta data: " + givenAnswer +
                    " | Esito: " + getResult();
        }


}
