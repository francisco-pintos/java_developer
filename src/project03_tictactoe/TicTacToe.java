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

        // Print the game state
        String gameState = analyzeGameState(gameArray);
        System.out.println(gameState);
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
                        System.out.print(col == 0 ? " " : "  ");
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

    public static String analyzeGameState(int[][] gameArray) {

        // Get the grid dimensions
        int rows = gameArray.length;
        int cols = gameArray[0].length;

        // Check for the number of the symbols (difference < 2)
        int sum0 = 0;
        int sum1 = 0;
        int sum2 = 0;
        for (int[] row : gameArray) {
            for (int item : row) {
                switch (item) {
                    case 0:
                        sum0++;
                        break;
                    case 1:
                        sum1++;
                        break;
                    case 2:
                        sum2++;
                        break;
                }
            }
        }
        if (sum0 + sum1 + sum2 != 9) {
            return "Impossible";
        }
        if (sum1 - sum2 > 1 || sum2 - sum1 > 1) {
            return "Impossible";
        }

        // Check for three in a row
        int rows1 = 0;
        int rows2 = 0;
        for (int k = 1; k <= 2; k++) {
            // Check in rows
            for (int[] row : gameArray) {
                boolean allEqual = true;
                for (int item : row) {
                    if (item != k) {
                        allEqual = false;
                        break;
                    }
                }
                if (allEqual) {
                    if (k == 1) {
                        rows1++;
                    } else {
                        rows2++;
                    }
                }
            }
            // Check in columns
            for (int col = 0; col < cols; col++) {
                boolean allEqual = true;
                //noinspection ForLoopReplaceableByForEach
                for (int row = 0; row < rows; row++) {
                    if (gameArray[row][col] != k) {
                        allEqual = false;
                        break;
                    }
                }
                if (allEqual) {
                    if (k == 1) {
                        rows1++;
                    } else {
                        rows2++;
                    }
                }
            }
            // Check in principal diagonal
            boolean allEqual = true;
            for (int row = 0; row < rows; row++) {
                if (gameArray[row][row] != k) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                if (k == 1) {
                    rows1++;
                } else {
                    rows2++;
                }
            }
            // Check in other diagonal
            allEqual = true;
            for (int row = 0; row < rows; row++) {
                if (gameArray[row][rows - row - 1] != k) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                if (k == 1) {
                    rows1++;
                } else {
                    rows2++;
                }
            }
        }

        // Check for several diagonals
        if (rows1 + rows2 > 1) {
            return "Impossible";
        }

        // Check for one winner
        if (rows1 == 1) {
            return "X wins";
        }
        if (rows2 == 1) {
            return "O wins";
        }

        // Check for draw
        if (sum0 == 0) {
            return "Draw";
        }

        // If not any other state, game not finished
        return "Game not finished";
    }

}
