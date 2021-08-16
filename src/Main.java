import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Show greetings and get username
        String userName = showGreetings();

        // Get the user age
        int userAge = getUserAge();

        // Count number requested to user
        countNumbers();

        // Make the test
        makeTest();
    }

    public static String showGreetings() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Greetings and ask for username
        System.out.println("Hello! My name is Aid.");
        System.out.println("I was created in 2021.");
        System.out.println("Please, remind me your name.");
        String userName = scanner.nextLine();
        System.out.printf("What a great name you have, %s!\n", userName);

        // Return the username
        return userName;
    }

    public static int getUserAge() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Get the remainders of the division of user age by 3, 5 and 7
        System.out.println("Let me guess your age.");
        System.out.println("Enter the reminders of dividing " +
                "your age by 3, 5 and 7.");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();

        // Get the user age
        int userAge =
                (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        // Print the user age
        System.out.printf("Your age is %d; that's a good time " +
                "to start programming!%n", userAge);

        // Return the user age
        return userAge;
    }

    public static void countNumbers() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Ask for the number to count
        System.out.println("Now I will prove to you that I can count " +
                "to any number you want.");
        int number = scanner.nextInt();

        // Print the result of counting from 0 to number
        for (int i = 0; i <= number; i++) {
            System.out.printf("%d!%n", i);
        }
    }

    public static void makeTest() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Make the test
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into " +
                "several small subroutines.");
        System.out.println("3. To determine the execution time " +
                "of a program.");
        System.out.println("4. To interrupt the execution of a program");

        // Create the infinite loop:
        while (true) {
            // Capture the answer
            int answer = scanner.nextInt();

            // Check the answer
            if (answer == 2) {
                System.out.println("Congratulations, have a nice day!");
                break;
            } else {
                System.out.println("Please, try again.");
            }
        }
    }

}