import java.util.Random;
import java.util.Scanner;

public class Main {
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
        final String COLOR_RESET = "\033[0m";
        final String COLOR_GREEN = "\033[0;32m";
        final String COLOR_RED = "\033[0;31m";
        String secretWord = pickWord(words, random);
        char[] secretWordLetters = secretWord.toCharArray();
        int attempts = 6;
        String template;
        String guess;
        System.out.println("***"+secretWord+"***\n");

        while (attempts > 0) {
            System.out.println("Enter your guess: ");
            guess = scanner.nextLine().trim().toLowerCase();
            template = "";

            if (guess.length() != 5) {
                System.out.println("Your word must have 5 letters!");
                continue;
            }

            char[] guessLetters = guess.toCharArray();

            if (!guess.equalsIgnoreCase(secretWord)) {
                for(int i = 0; i < guess.length(); i++) {
                    if (guessLetters[i] == secretWordLetters[i]) {
                        template += COLOR_GREEN + guessLetters[i] + COLOR_RESET;
                    }
                    else if (guessLetters[i] != secretWordLetters[i] && secretWord.contains(""+guessLetters[i])) {
                        template += COLOR_RED + guessLetters[i] + COLOR_RESET;
                    }
                    else {
                        template += "_";
                    }
                }
                System.out.println(template);
            }
            else {
                System.out.println("Congrats!\nYou made it!!!");
                break;
            }
            attempts--;
        }

        if (attempts == 0) System.out.println("Unfortunately you've lost :(");
    }

    public static String pickWord(String[] words, Random random) {
        return words[random.nextInt(words.length)].toLowerCase();
    }

}