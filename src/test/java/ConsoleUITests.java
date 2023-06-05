import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleUITests {

    private OutputStream out;

    public static Question mockQuestion(String prompt, String validityMessage, String[] resultSequence) {

        return new Question(new Random()) {
            private int resultIdx = 0;

            @Override
            public String getPrompt() {
                return prompt;
            }

            @Override
            public String getValidityMessage() {
                return validityMessage;
            }

            @Override
            public String parseAnswer(String answer) {
                var result = resultSequence[resultIdx];
                resultIdx++;
                return result;
            }
        };
    }

    @BeforeEach public void setOut() {
        this.out = new ByteArrayOutputStream();
    }

    @Test
    public void promptForString() {
        var ui = new ConsoleUI(TestUtils.mockScanner("theinput"), new PrintStream(out));

        var response = ui.promptForString("testprompt");

        assertEquals("testprompt", out.toString(), "promptForInt must display the given prompt");
        assertEquals("theinput", response, "promptForString must return the user's input");
    }

    @Test
    public void confirm() {
        String[] inputs = {
                "y",
                "y",
                "Y",
                "n",
                "N",
                "blah", "blah", "y"
        };
        var ui = new ConsoleUI(TestUtils.mockScanner(inputs), new PrintStream(out));

        ui.confirm("testprompt");

        assertEquals("testprompt(y/n) ", out.toString(), "confirm must display the given prompt");

        assertTrue(ui.confirm(""), "confirm must return true when user enters 'y'");
        assertTrue(ui.confirm(""), "confirm must return true when user enters 'Y'");
        assertFalse(ui.confirm(""), "confirm must return false when user enters 'n'");
        assertFalse(ui.confirm(""), "confirm must return false when user enters 'N'");

        ui.confirm("");
        assertEquals(2, TestUtils.numInstancesOfSubstring(out.toString(), "Invalid input"), "confirm must reprompt when the user enters anything other than 'y' or 'n'");
    }

    @Test
    public void printResults() {
        var ui = new ConsoleUI(TestUtils.mockScanner(""), new PrintStream(out));

        ui.printResults(new int[] { 1, 2, 3 });

        assertEquals("1) ✓%n2) 2 tries%n3) 3 tries%n".formatted(), out.toString());
    }

    @Test
    public void askQuestion_worksOnCorrectAnswer() {
        var ui = new ConsoleUI(TestUtils.mockScanner("4"), new PrintStream(out));
        var q = mockQuestion("prompt", "", new String[]{Question.ANSWER_CORRECT});
        int tries = ui.askQuestion(q);

        var output = out.toString();

        assertTrue(output.contains("Correct!"), "askQuestion must print 'Correct!' when the answer is correct");
        assertTrue(out.toString().startsWith("prompt"), "askQuestion must display the question prompt");
        assertEquals(1, tries, "askQuestion must return 1 when user enters correct answer as their first answer");
    }

    @Test
    public void askQuestion_worksOnIncorrectAnswers() {
        String[] inputs = { "5", "5", "4" };
        var ui = new ConsoleUI(TestUtils.mockScanner(inputs), new PrintStream(out));
        var q = mockQuestion("prompt", "", new String[] { Question.ANSWER_INCORRECT, Question.ANSWER_INCORRECT, Question.ANSWER_CORRECT });
        int tries = ui.askQuestion(q);

        var output = out.toString();

        assertTrue(output.contains("Incorrect. Try again."), "askQuestion must print 'Incorrect. Try again.' if the answer is incorrect");
        assertEquals(3, tries, "askQuestion must return number of tries if user enter incorrect answers");
        assertEquals(2, TestUtils.numInstancesOfSubstring(out.toString(), "Incorrect"));
    }

    @Test
    public void askQuestion_worksOnInvalidAnswers() {
        String[] inputs = { "a", "a", "4" };
        var ui = new ConsoleUI(TestUtils.mockScanner(inputs), new PrintStream(out));
        String validityMessage = "validity prompt";
        var q = mockQuestion("prompt", validityMessage, new String[] { Question.ANSWER_INVALID, Question.ANSWER_INVALID, Question.ANSWER_CORRECT });
        int tries = ui.askQuestion(q);

        var output = out.toString();

        assertTrue(output.contains("Invalid answer."), "askQuestion must print 'Invalid answer.' if the answer is invalid");
        assertEquals(1, tries, "askQuestion must not count invalid answers as a try");
        assertEquals(2, TestUtils.numInstancesOfSubstring(out.toString(), validityMessage));
    }

}
