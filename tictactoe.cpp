#include <iostream>
#include <vector>

std::vector<std::vector<char>> board(3, std::vector<char>(3));

void initBoard() {
    char cell = '1';
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            board[i][j] = cell++;
        }
    }
}

void printBoard() {
    std::cout << "\n";
    for (int i = 0; i < 3; i++) {
        std::cout << " " << board[i][0] << " | " << board[i][1] << " | " << board[i][2] << " ";
        if (i != 2) std::cout << "\n---+---+---\n";
    }
    std::cout << "\n\n";
}

bool checkWin() {
    // rows and columns
    for (int i = 0; i < 3; i++) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
            return true;
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i])
            return true;
    }
    // diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        return true;
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        return true;

    return false;
}

bool isDraw() {
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (board[i][j] >= '1' && board[i][j] <= '9')
                return false;
    return true;
}

bool makeMove(char player, int choice) {
    char target = '0' + choice;
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

int main() {
    char player = 'X';
    int choice;
    bool gameOver = false;

    initBoard();
    std::cout << "Tic Tac Toe (Player X vs Player O)\n";
    printBoard();

    while (!gameOver) {
        std::cout << "Player " << player << ", enter a cell number (1-9): ";
        if (!(std::cin >> choice) || choice < 1 || choice > 9) {
            std::cout << "Invalid input. Exiting.\n";
            break;
        }

        if (!makeMove(player, choice)) {
            std::cout << "Cell already taken. Try again.\n";
            continue;
        }

        printBoard();

        if (checkWin()) {
            std::cout << "Player " << player << " wins!\n";
            gameOver = true;
        } else if (isDraw()) {
            std::cout << "It's a draw.\n";
            gameOver = true;
        } else {
            player = (player == 'X') ? 'O' : 'X';
        }
    }

    return 0;
}
