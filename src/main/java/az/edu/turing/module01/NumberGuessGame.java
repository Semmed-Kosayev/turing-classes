package az.edu.turing.module01;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String name;

        System.out.println("Let the game begin!");

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        int randomNumber = random.nextInt(101);
        int[] guesses = new int[100]; // Array to store up to 100 guesses
        int guessCount = 0; // Counter for valid guesses

        while (true) {
            System.out.print("Enter your guess (0-100): ");

            // Validating input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clears invalid input
            }

            int guess = scanner.nextInt();

            // Checks if the guess is within range
            if (guess < 0 || guess > 100) {
                System.out.println("Please enter a number between 0 and 100.");
                continue; // Prompt for input again
            }

            // Store the guess in the array
            guesses[guessCount] = guess;
            guessCount++;

            // Compare the guess with the random number
            if (guess < randomNumber) {
                System.out.println("Your number is too small. Please, try again.");
            } else if (guess > randomNumber) {
                System.out.println("Your number is too big. Please, try again.");
            } else {
                System.out.println("Congratulations, " + name + "!");
                break; // Exit the guessing loop
            }
        }

        System.out.print("Your numbers: ");
        for (int guess : guesses) {
            if (guess != 0)
                System.out.print(guess + " ");
        }
        System.out.println();

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
