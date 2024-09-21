package az.edu.turing.module01;

import java.util.Scanner;

public class WeekPlanner {
    public static void main(String[] args) {
        String[][] schedule = new String[7][2];
        schedule[0][0] = "Sunday";
        schedule[0][1] = "do home work";
        schedule[1][0] = "Monday";
        schedule[1][1] = "go to courses; watch a film";
        schedule[2][0] = "Tuesday";
        schedule[2][1] = "attend meetings";
        schedule[3][0] = "Wednesday";
        schedule[3][1] = "work on project";
        schedule[4][0] = "Thursday";
        schedule[4][1] = "go shopping";
        schedule[5][0] = "Friday";
        schedule[5][1] = "visit friends";
        schedule[6][0] = "Saturday";
        schedule[6][1] = "relax and read books";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please, input the day of the week: ");
            String userInput = scanner.nextLine().trim().toLowerCase(); // Handles case insensitivity and extra spaces

            if (userInput.equals("exit")) {
                break;
            }

            boolean found = false;
            for (int i = 0; i < schedule.length; i++) {
                if (schedule[i][0].toLowerCase().equals(userInput)) {
                    System.out.println("Your tasks for " + schedule[i][0] + ": " + schedule[i][1]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Sorry, I don't understand you, please try again.");
            }
        }

        scanner.close();
    }
}
