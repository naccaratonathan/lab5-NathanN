import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        /* TODO:
            1. Make a ConsoleUI
            2. Make a QuestionFactory
            3. Make the App object
            4. Run the app!
            // Create ConsoleUI object */
        ConsoleUI consoleUI = new ConsoleUI(new Scanner(System.in), System.out);


        QuestionFactory questionFactory = new QuestionFactory() {
            @Override
            public Question[] makeQuestions(int n) {
                return null;
            }
        }; //Create QuestionFactory object

        App app = new App(consoleUI, questionFactory);  //Create App object with ConsoleUI and QuestionFactory objects
        app.run(); //Call the run method on the app object

    }
}
