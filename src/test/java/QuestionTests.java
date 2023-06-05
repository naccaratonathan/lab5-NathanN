import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionTests {

    @Test
    public void getPrompt() {
        Question q = new Question(TestUtils.mockRandom(new int[] { 2, 8 }));
        String prompt = q.getPrompt();
        assertEquals("What is 2 x 8? ", prompt);
    }

    // TODO: Test the getValidityMessage method
    @Test
    public void getValidityMessage(){
        Question q = new Question(TestUtils.mockRandom(new int[] { 2, 8 }));
        String validityMessage = q.getValidityMessage();
        assertEquals("Your answer must be a number", validityMessage);
    }

    // TODO: Test the parseAnswer method
    public void getParseAnswer(){
        Question q = new Question(TestUtils.mockRandom(new int[] { 2, 8 }));
        int parseAnswer = q.getExpectedAnswer();
        assertEquals(16, parseAnswer);
    }
}