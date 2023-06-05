import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class TestUtils {

    /**
     * @param numberSequence The sequence of numbers the mock generator will produce
     * @return A mock RandomNumberGenerator that produces the sequence of numbers given in numberSequence
     */
    public static Random mockRandom(int[] numberSequence) {
        return new Random() {
            private int sequenceIdx = 0;
            @Override
            public int nextInt(int origin, int bound) {
                int n = numberSequence[sequenceIdx];
                sequenceIdx++;
                return n;
            }
            @Override
            public int nextInt(int bound) {
                return nextInt(0, bound);
            }
        };
    }

    /**
     * @return A mock RandomNumberGenerator.  Each call to nextInt always returns the value 0
     */
    public static Random mockRandom() {
        return new Random() {
            private int sequenceIdx = 0;
            @Override
            public int nextInt(int origin, int bound) {
                return 0;
            }
        };
    }

    /**
     * @param inputs The sequence of characters to use as the input
     * @return A Scanner which will receive the given string as its sequence of inputs
     */
    public static Scanner mockScanner(String inputs) {
        return new Scanner(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @param inputs The sequence of strings to use as the input; each item in the array will be treated as a line of input
     * @return A Scanner which will receive the given string array as its sequence of inputs
     */
    public static Scanner mockScanner(String[] inputs) {
        String inputStr = String.join("%n".formatted(), inputs);
        return new Scanner(new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @param str The string to analyze
     * @param substr The substring to count the number of instances of
     * @return The number of times that the given substring appears in the given string
     */
    public static int numInstancesOfSubstring(String str, String substr) {
        String temp = str.replace(substr, "");
        return (str.length() - temp.length()) / substr.length();
    }

}
