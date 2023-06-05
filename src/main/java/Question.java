import java.util.Random;

public class Question {
    private int operand1;
    private int operand2;
    private int tries;
    private int expectedAnswer;
    public void setExpectedAnswer(int answer) {
        expectedAnswer = answer;
    }
    private String questionPrompt = "";
    public final static String CORRECT_ANSWER = "Correct";
    public final static String INCORRECT_ANSWER = "Incorrect";
    public final static String INVALID_ANSWER = "Invalid";
    private String UserInput;
    private String validationMessage = "Your answer must be a number";


    public int getOperand1(){
        return operand1;
    }

    public int getOperand2(){
        return operand2;
    }
    public int getExpectedAnswer(){
        return expectedAnswer;
    }

    public Question(Random rand){
        this.operand1 = rand.nextInt(0,12);
        this.operand2 = rand.nextInt(0,12);
        this.expectedAnswer = operand1*operand2;
        this.questionPrompt = "What is "+ operand1+" * " +operand2 + "?";
    }
    public String getPrompt(){
        return questionPrompt;
    }

    public String getValidityMessage(){
        return validationMessage;
    }

    public String parseAnswer(String userInput) {
        userInput = userInput.trim();
        try {
            int userAnswer = Integer.parseInt(userInput);
            this.UserInput = String.valueOf(userAnswer);
            if (userAnswer == expectedAnswer) {
                return CORRECT_ANSWER;
            } else {
                return INCORRECT_ANSWER;
            }
        } catch (NumberFormatException e) {
            return INVALID_ANSWER;
        }
    }

}
