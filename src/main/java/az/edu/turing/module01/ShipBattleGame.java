package az.edu.turing.module01;

import java.util.Random;
import java.util.Scanner;

public class ShipBattleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Size of the square
        final int SIZE = 5;
        char[][] square = new char[SIZE][SIZE];

        // Filling square
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                square[i][j] = '-';
            }
        }

        int targetRow = random.nextInt(SIZE);
        int targetCol = random.nextInt(SIZE);

        System.out.println("All set. Get ready to rumble!");

        while (true) {
            // Prompt is provided to the method to increase readability
            int row = getValidInput(scanner, "Enter the row number (1-5): ", SIZE);
            int col = getValidInput(scanner, "Enter the column number (1-5): ", SIZE);

            if (square[row - 1][col - 1] == '*') {
                System.out.println("You have already hit here. Try another place.");
                continue;
            }

            if (row == targetRow + 1 && col == targetCol + 1) {
                square[targetRow][targetCol] = 'x';
                printGameSquare(square);
                System.out.println("You have won!");
                break;
            } else {
                square[row - 1][col - 1] = '*';
            }

            printGameSquare(square);
        }

        scanner.close();
    }

    // Method to get valid input from user
    private static int getValidInput(Scanner scanner, String prompt, int max) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            input = scanner.nextInt();
            if (input < 1 || input > max) {
                System.out.println("Please enter a number between 1 and " + max);
                continue;
            }
            break;
        }

        return input;
    }

    // Method to print game square
    private static void printGameSquare(char[][] square) {
        System.out.println("  0 | 1 | 2 | 3 | 4 | 5 |");
        for (int i = 0; i < square.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < square[i].length; j++) {
                System.out.print("| " + square[i][j] + " ");
            }
            System.out.println("|");
        }
    }
}