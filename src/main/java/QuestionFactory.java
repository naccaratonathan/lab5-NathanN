import java.util.Random;

/**
 * Creates sets of questions to ask in a quiz
 */
public abstract class QuestionFactory {

    /**
     * @param n The number of questions to create
     * @return An array of n questions
     */
    // TODO: Implement the makeQuestions method

    public static Question[] makeQuestions(int n, Random rand){
        Question[] questions = new Question[5];
        for (int i = 0; i < n; i++) {
            Question question = new Question(rand); // Create a new Question object with random operands
            questions[i] = question; // Set the current question in the array
            question.setExpectedAnswer(question.getOperand1() * question.getOperand2()); // Set the expected answer for the question
        }
        return questions;
    }

    public abstract Question[] makeQuestions(int n);
}
