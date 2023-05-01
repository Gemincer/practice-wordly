import java.util.Random;
import java.util.Scanner;

public class Main {
    final static String COLOR_RESET = "\033[0m";
    final static String COLOR_GREEN = "\033[0;32m";
    final static String COLOR_RED = "\033[0;31m";
    public static void main(String[] args) {
        String[] words = {
                "About", "Above", "Alert", "Breed",
                "Bread", "Booth", "Chain", "Chair",
                "Child", "Error", "Event", "Exact",
                "False", "Faith", "Flash", "Floor",
                "Metal", "Media", "Local", "Logic",
                "Mount", "Mouth", "Power", "Radio",
                "Prize", "Prove", "Right", "Queen",
                "Sharp", "Split", "Table", "Think",
                "Tight", "Throw", "Watch", "Wheel",
                "World", "Would", "Video", "Visit"
        };

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String secretWord = pickWord(words, random);
        int attempts = 6;
        String guess;
        System.out.println("***" + secretWord + "***\n");

        while (attempts > 0) {
            System.out.println("Enter your guess: ");
            guess = scanner.nextLine().trim().toLowerCase();

            if (guess.length() != 5) {
                System.out.println("Your word must have 5 letters!");
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