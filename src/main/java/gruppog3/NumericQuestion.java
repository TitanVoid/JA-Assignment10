package gruppog3;

public class NumericQuestion {
    private int num1;
    private int num2;
    private char operator;

    public NumericQuestion(){}

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public char getOperator() {
        return operator;
    }

    public void randomInit(){
        num1 = (int) (Math.random() * 50);
        num2 = (int) (Math.random() * 50);
        operator = Math.random() < 0.5 ? '+' : '-';
    }

    public int getResult(){
        return operator == '+' ? num1 + num2 : num1 - num2;
    }

    @Override
    public String toString(){
        return num1 + " " + operator + " " + num2 + " = ?";
    }
}
