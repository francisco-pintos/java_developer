package project03_tictactoe;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TicTacToe {

    public static void main(String[] args) {
        // Get the game grid array
        int[][] gameArray = getInputCells();

        // Print the game grid array
        printGameGrid(gameArray);
    }

    public static int[][] getInputCells() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Get the input string
        int[][] gameArray = new int[3][3];
        while (true) {
            System.out.print("Enter cells: ");
            String inputString = scanner.next();

            // Check if the string is null
            if (inputString == null) {
                System.out.println("Please, try again.");
                continue;
            }

            // Check if the string length is 9
            if (inputString.length() != 9) {
                System.out.println("Please, try again.");
                continue;
            }

            // Check the characters of the string
            Set<Character> chars = new TreeSet<>();
            for (char c : inputString.toCharArray()) {
                chars.add(c);
            }
            boolean wrongCharacter = false;
            for (char c : chars) {
                if (c != 'X' && c != 'O' && c != '_') {
                    System.out.println("Please, enter only 'X', " +
                            "'O' or '_'. Try again.");
                    wrongCharacter = true;
                    break;
                }
            }
            if (wrongCharacter) {
                continue;
            }

            // Create the game grid array
            char[] inputChars = inputString.toCharArray();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    switch (inputChars[i * 3 + j]) {
                        case 'X':
                            gameArray[i][j] = 1;
                            break;
                        case 'O':
                            gameArray[i][j] = 2;
                            break;
                        case '_':
                            gameArray[i][j] = 0;
                            break;
                    }
                }
            }
            break;
        }

        // Return the game grid array
        return gameArray;
    }

    public static void printGameGrid(int[][] gameArray) {
        // Get the grid dimensions
        int cols = gameArray[0].length;

        // Print the game grid
        System.out.println("---------");
        for (int[] row : gameArray) {
            System.out.print("| ");
            for (int col = 0; col < cols; col++) {
                switch (row[col]) {
                    case 0:
                        System.out.print(col == 0 ? "_" : " _");
                        break;
                    case 1:
                        System.out.print(col == 0 ? "X" : " X");
                        break;
                    case 2:
                        System.out.print(col == 0 ? "O" : " O");
                }
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

}
