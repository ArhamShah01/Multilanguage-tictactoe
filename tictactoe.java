import java.util.Scanner;

public class TicTacToe {
    public static char[][] initBoard() {
        char[][] board = new char[3][3];
        char cell = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = cell++;
            }
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.printf(" %c | %c | %c ", board[i][0], board[i][1], board[i][2]);
            System.out.println();
            if (i != 2) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    public static boolean checkWin(char[][] board) {
        // rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        // diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] >= '1' && board[i][j] <= '9') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean makeMove(char[][] board, char player, int choice) {
        char target = (char) (choice + '0');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == target) {
                    board[i][j] = player;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char player = 'X';
        char[][] board = initBoard();
        System.out.println("Tic Tac Toe (Player X vs Player O)");
        printBoard(board);

        while (true) {
            System.out.printf("Player %c, enter a cell number (1-9): ", player);
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Exiting.");
                break;
            }

            if (choice < 1 || choice > 9) {
                System.out.println("Invalid input. Exiting.");
                break;
            }

            if (!makeMove(board, player, choice)) {
                System.out.println("Cell already taken. Try again.");
                continue;
            }

            printBoard(board);

            if (checkWin(board)) {
                System.out.printf("Player %c wins!%n", player);
                break;
            } else if (isDraw(board)) {
                System.out.println("It's a draw.");
                break;
            } else {
                player = (player == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }
}
