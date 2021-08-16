package project01_cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Get the room size
        int[] roomSize = getRoomSize();
        int rows = roomSize[0];
        int cols = roomSize[1];

        // Create the sold seat array
        int[][] soldSeats = new int[rows][cols];

        // Run an infinity loop
        runMainLoop(soldSeats);
    }

    public static void runMainLoop(int[][] seatArray) {
        // Create the infinity loop
        while (true) {
            // Get the action from the menu
            int action = showMenu();

            // Run the action
            switch (action) {
                case 0:
                    return;
                case 1:
                    printRoomSeats(seatArray);
                    break;
                case 2:
                    saleTicket(seatArray);
                    break;
                case 3:
                    showStatistics(seatArray);
                    break;
                default:
                    System.out.println("\nPlease, enter a valid action!");
                    break;
            }
        }
    }

    public static void showStatistics(int[][] seatArray) {
        System.out.printf("%nNumber of purchased tickets: %d",
                getPurchasedTickets(seatArray));
        System.out.printf("%nPercentage: %.2f%%",
                getPurchasedTicketsPercentage(seatArray));
        System.out.printf("%nCurrent income: $%d",
                getCurrentIncome(seatArray));
        System.out.printf("%nTotal income: $%d%n",
                getTotalIncome(seatArray));
    }

    public static void saleTicket(int[][] seatArray) {
        // Create the infinity loop
        while (true) {
            // Get the room dimensions
            int rows = seatArray.length;
            int cols = seatArray[0].length;

            // Get the seat location
            int[] seatLocation = getSeatLocation();
            int row = seatLocation[0];
            int col = seatLocation[1];

            // Validate the seat location
            if (row > rows || row < 1 || col > cols || col < 1) {
                System.out.println("\nWrong input!");
                continue;
            }
            if (seatArray[row - 1][col - 1] == 1) {
                System.out.println("\nThat ticket has already been purchased!");
                continue;
            }

            // Get the ticket price
            int ticketPrice = getTicketPrice(row, col, seatArray);

            // Print the ticket price
            System.out.println("\nTicket price: $" + ticketPrice);

            // Modify the seat array
            seatArray[row - 1][col - 1] = 1;
            break;
        }
    }

    public static int getTicketPrice(int row, int col, int[][] seatArray) {
        // Get the room size
        int rows = seatArray.length;
        int cols = seatArray[0].length;

        // Get the ticket prices by row
        int price1 = 10;
        int price2;
        if (rows * cols > 60) {
            price2 = 8;
        } else {
            price2 = 10;
        }

        // Get the seat ticket price
        int firstHalf = rows / 2;
        // int secondHalf = rows - firstHalf;
        int ticketPrice;
        if (row <= firstHalf) {
            ticketPrice = price1;
        } else {
            ticketPrice = price2;
        }

        // Return the ticket price
        return ticketPrice;
    }

    public static void printRoomSeats(int[][] seatArray) {
        // Get the room dimensions
        int rows = seatArray.length;
        int cols = seatArray[0].length;

        // Print first row
        System.out.println("\nproject01_cinema.Cinema:");

        // Print the seat indices
        for (int i = 0; i <= cols; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();

        // Print the seats by row
        for (int i = 1; i <= rows; i++) {
            int[] row = seatArray[i - 1];
            for (int j = 0; j <= cols; j++) {
                if (j == 0) {
                    System.out.print(i);
                } else {
                    if (row[j - 1] == 0) {
                        System.out.print(" S");
                    } else {
                        System.out.print(" B");
                    }
                }
            }
            System.out.println();
        }
    }

    public static int[] getSeatLocation() {
        // Create the scanner and the output array
        Scanner scanner = new Scanner(System.in);
        int[] output = new int[2];

        // Get the row of the seat
        System.out.print("\nEnter a row number:\n> ");
        output[0] = scanner.nextInt();

        // Get the seat number in that row
        System.out.print("Enter a seat number in that row:\n> ");
        output[1] = scanner.nextInt();

        // Return the output array
        return output;
    }

    public static int showMenu() {
        // Create the scanner and the output action variable
        Scanner scanner = new Scanner(System.in);
        int action;

        // Print the menu
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");

        // Get the action
        action = scanner.nextInt();

        // Return the action
        return action;
    }

    public static int[] getRoomSize() {
        // Create the scanner and the output array
        Scanner scanner = new Scanner(System.in);
        int[] outputArray = new int[2];

        // Get the number of rows of the room
        System.out.print("Enter the number of rows:\n> ");
        outputArray[0] = scanner.nextInt();

        // Get the number of seats by row of the room
        System.out.print("Enter the number of seats in each row:\n> ");
        outputArray[1] = scanner.nextInt();

        // Return the output array
        return outputArray;
    }

    public static int getPurchasedTickets(int[][] seatArray) {
        // Define the sum variable
        int sum = 0;

        // Iterate over the array rows getting their sum
        for (int[] row : seatArray) {
            sum += Arrays.stream(row).sum();
        }

        // Return the result
        return sum;
    }

    public static double getPurchasedTicketsPercentage(int[][] seatArray) {
        // Get the purchased tickets
        double purchased = getPurchasedTickets(seatArray);

        // Get the overall tickets
        double overall = seatArray.length * seatArray[0].length;

        // Return the percentage
        return purchased / overall * 100;
    }

    public static int getCurrentIncome(int[][] seatArray) {
        // Get the room dimensions
        int rows = seatArray.length;
        int cols = seatArray[0].length;

        // Define the income aggregation variable
        int income = 0;

        // Iterate over the array
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                if (seatArray[row - 1][col - 1] == 1) {
                    income += getTicketPrice(row, col, seatArray);
                }
            }
        }

        // Return the actual income
        return income;
    }

    public static int getTotalIncome(int[][] seatArray) {
        // Get the room dimensions
        int rows = seatArray.length;
        int cols = seatArray[0].length;

        //

        // Create an array full of ones
        int[][] arrayFullOfOnes = new int[rows][cols];
        for (int[] row : arrayFullOfOnes) {
            Arrays.fill(row, 1);
        }

        // Return the total income
        return getCurrentIncome(arrayFullOfOnes);
    }

}