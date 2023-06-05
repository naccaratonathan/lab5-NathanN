import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionTests {

    @Test
    public void getPrompt() {
        Question q = new Question(TestUtils.mockRandom(new int[] { 2, 3 }));
        String prompt = q.getPrompt();
        assertEquals("What is 2 x 3? ", prompt);
    }

    // TODO: Test the getValidityMessage method


    // TODO: Test the parseAnswer method
}
