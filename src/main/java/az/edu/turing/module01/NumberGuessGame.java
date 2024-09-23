package az.edu.turing.module01;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String name;

        System.out.println("Let the game begin!");

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        int randomNumber = random.nextInt(101);
        int[] guesses = new int[100];
        int guessCount = 0;

        while (true) {
            int guess = getValidInput(scanner);

            guesses[guessCount] = guess;
            guessCount++;

            if (guess < randomNumber) {
                System.out.println("Your number is too small. Please, try again.");
            } else if (guess > randomNumber) {
                System.out.println("Your number is too big. Please, try again.");
            } else {
                System.out.println("Congratulations, " + name + "!");
                break;
            }
        }

        printGuesses(guesses);

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    public static void printGuesses(int[] guesses) {
        System.out.print("Your numbers: ");
        for (int i = guesses.length - 1; i >= 0; i--) {
            int guess = guesses[i];
            if (guess != 0) System.out.print(guess + " ");
        }
        System.out.println();
    }

    public static int getValidInput(Scanner scanner) {
        int guess;
        while (true) {
            System.out.print("Enter your guess (0-100): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            guess = scanner.nextInt();

            if (guess < 0 || guess > 100) {
                System.out.println("Please enter a number between 0 and 100.");
                continue;
            }
            break;
        }
        return guess;
    }
}
