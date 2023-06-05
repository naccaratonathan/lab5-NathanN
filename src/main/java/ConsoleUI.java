import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner in;
    private PrintStream outStream;

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
    // TODO: Implement askQuestion

    /**
     * Asks the user the given set of questions
     * @param questions The set of questions to ask
     * @return The nth number in the returned array is the number of tries it took for the user to give the
     *      correct answer for the nth question.
     */
    public int[] askQuestions(Question[] questions) {
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
    public boolean confirm(String prompt) {

        while ( true ) {
            outStream.print(prompt + "(y/n) ");

            String response = in.nextLine().trim().toLowerCase();

            if ( response.equals("y") ) {
                return true;
            } else if ( response.equals("n") ) {
                return false;
            } else {
                outStream.println("Invalid input");
            }
        }
    }

    /**
     * Print the given line to the console
     * @param line The line to print
     */
    public void println(String line) {
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

    /**
     * Prompt the user for their name
     * @return The name the user entered
     */
    public String promptForName() {
        return promptForString("What is your name? ");
    }

    /**
     * Prompt the user for a string
     * @param prompt A prompt message to display before requesting user input
     * @return The text entered by the user in response to the prompt
     */
    public String promptForString(String prompt) {
        outStream.print(prompt);
        return in.nextLine();
    }
}
