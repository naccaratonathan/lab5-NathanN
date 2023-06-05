import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI {

    private static Scanner in;
    private static PrintStream outStream;

    public ConsoleUI(Scanner in, PrintStream out) {
        this.in = in;
        this.outStream = out;
    }

    /**
     * Ask the user the given question.
     * The user will be prompted repeatedly with the question's prompt until they provide a valid and correct answer.
     * Only valid but incorrect responses count toward the number of tries (i.e. invalid responses do NOT count
     * toward the number of tries).
     * @param question The question to ask
     * @return The number of tries the user takes to provide the correct answer
     */
    private static int tries = 1;

    public int getTries(){
        return tries;
    }
    public void setTries(int tries){
        this.tries = tries;
    }

    // TODO: Implement askQuestion
    public static int askQuestion(Question question){
        tries = 1;
        println(question.getPrompt());
        String userInput = in.next();
        while (true) {
            if (question.parseAnswer(userInput).equals(question.CORRECT_ANSWER)) {
                return tries;
            } else if (question.parseAnswer(userInput).equals(question.INCORRECT_ANSWER)) {
                while (question.parseAnswer(userInput).equals(question.INCORRECT_ANSWER)) {
                    tries++;
                    println("Incorrect. Try again.");
                    userInput = in.nextLine();
                }
            } else {
                tries++;
                println("Invalid answer");
                println(question.getValidityMessage());
            }
            userInput = in.nextLine();

        }
    }

    /**
     * Asks the user the given set of questions
     * @param questions The set of questions to ask
     * @return The nth number in the returned array is the number of tries it took for the user to give the
     *      correct answer for the nth question.
     */
    public static int[] askQuestions(Question[] questions) {
        int[] results = new int[questions.length];

        int i = 0;
        for ( Question q : questions ) {
            println("Question #" + (i+1));
            results[i] = askQuestion(q);
            i++;
        }

        return results;
    }

    /**
     * Prompt the user for a 'yes or no' response
     * @param prompt The prompt to present to the user
     * @return True if the user response 'y', false if they respond 'n'
     */
    public static boolean confirm(String prompt) {

        while ( true ) {
            outStream.print(prompt + "(y/n) ");

            String response = in.nextLine().trim().toLowerCase();

            if ( response.equals("y") ) {
                return true;
            } else if ( response.equals("n") ) {
                return false;
            }
        }
    }

    /**
     * Print the given line to the console
     * @param line The line to print
     */
    public static void println(String line) {
        outStream.println(line);
    }

    /**
     * Format the given results to display the number of tries it took the user to answer questions.
     * Each element in the given array is the number of tries it took get the correct answer.
     * If the number of tries was 1 then a ✓ is displayed; otherwise the number of tries is displayed.
     * For example, given the array { 1, 2, 3 } the output would be:
     *
     * 1) ✓
     * 2) 2 tries
     * 3) 3 tries
     *
     * @param results The results to print.
     */
    // TODO: implement printResults
    public void printResult(int [] results){
        for (int i =0; i < results.length;i++){
            if (results[i] == 1){
                println("Correct");
            }
            else {
                println(results[i] + " tries");
            }
        }
    }

    /**
     * Prompt the user for their name
     * @return The name the user entered
     */
    public static String promptForName() {
        return promptForString("What is your name? ");
    }

    /**
     * Prompt the user for a string
     * @param questionPrompt A prompt message to display before requesting user input
     * @return The text entered by the user in response to the prompt
     */
    public static String promptForString(String questionPrompt) {
        outStream.print(questionPrompt);
        return in.nextLine();
    }
}
