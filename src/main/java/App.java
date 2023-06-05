public class App {

    private ConsoleUI ui;
    private QuestionFactory questionFactory;

    public App(ConsoleUI ui, QuestionFactory factory) {
        this.questionFactory = factory;
        this.ui = ui;
    }

    /**
     * Run the app!
     * 1.  Prompt the user for their name
     * 2.  Greet the user
     * 3.  Ask the user if they want to take a quiz, and...
     *      3.1 If yes, then...
     *          3.1.1   Loop...
     *              - Make 5 questions
     *              - Ask the user for the answers to the questions
     *              - Print the user's final results
     *              - Ask the user if they want another quiz, and if not exit the loop
     *      3.2 If no, then exit with an exit message
     */
    // TODO: implement the run method
}
