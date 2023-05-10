import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    final static String COLOR_RESET = "\033[0m";
    final static String COLOR_GREEN = "\033[0;32m";
    final static String COLOR_RED = "\033[0;31m";
    public static void main(String[] args) {
        String[] words = {
                "about", "abort", "above", "alert",
                "breed", "bribe", "bride", "bread",
                "booth", "build", "chain", "chair",
                "child", "clown", "cloud", "error",
                "event", "exact", "false", "faith",
                "flash", "floor", "fluid", "metal",
                "media", "medal", "local", "logic",
                "level", "mount", "mouth", "power",
                "pride", "radio", "prize", "prove",
                "right", "queen", "sharp", "split",
                "slice", "table", "think", "tight",
                "thing", "throw", "watch", "wheel",
                "world", "would", "write", "video",
                "visit", "hello"
        };

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String secretWord = pickWord(words, random);
        int attempts = 6;
        String guess;

        while (attempts > 0) {
            System.out.println("Enter your guess: ");
            guess = scanner.nextLine().trim().toLowerCase();

            if (!guess.matches("[a-z]{5}")) {
                System.out.println("Your word must have 5 letters!");
                continue;
            }

            if (!Arrays.asList(words).contains(guess)) {
                System.out.println("Your word have to be a valid word!");
                continue;
            }

            String answer = checkWord(secretWord, guess);

            if (!answer.equals("MATCH")) {
                System.out.println(answer);
            }
            else {
                System.out.println("Congrats! You made it!!!");
                return;
            }

            attempts--;
        }

        System.out.println("Unfortunately you've lost :(");
    }

    public static String pickWord(String[] words, Random random) {
        return words[random.nextInt(words.length)].toLowerCase();
    }

    public static String checkWord(String secretWord, String guess) {
        char[] guessLetters = guess.toCharArray();
        char[] secretWordLetters = secretWord.toCharArray();
        String template = "";

        if (!guess.equalsIgnoreCase(secretWord)) {
            for (int i = 0; i < guess.length(); i++) {
                if (guessLetters[i] == secretWordLetters[i]) {
                    template += COLOR_GREEN + guessLetters[i] + COLOR_RESET;
                } else if (guessLetters[i] != secretWordLetters[i] && secretWord.contains("" + guessLetters[i])) {
                    template += COLOR_RED + guessLetters[i] + COLOR_RESET;
                } else {
                    template += "_";
                }
            }

            return  template;
        }
        return "MATCH";
    }
}