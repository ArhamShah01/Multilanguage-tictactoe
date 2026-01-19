using System;

class TicTacToe
{
    static char[][] InitBoard()
    {
        char[][] board = new char[3][];
        int cell = '1';
        for (int i = 0; i < 3; i++)
        {
            board[i] = new char[3];
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = (char)cell;
                cell++;
            }
        }
        return board;
    }

    static void PrintBoard(char[][] board)
    {
        Console.WriteLine();
        for (int i = 0; i < 3; i++)
        {
            Console.WriteLine($" {board[i][0]} | {board[i][1]} | {board[i][2]} ");
            if (i != 2)
            {
                Console.WriteLine("---+---+---");
            }
        }
        Console.WriteLine();
    }

    static bool CheckWin(char[][] board)
    {
        // rows and columns
        for (int i = 0; i < 3; i++)
        {
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

    static bool IsDraw(char[][] board)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] >= '1' && board[i][j] <= '9')
                    return false;
            }
        }
        return true;
    }

    static bool MakeMove(char[][] board, char player, int choice)
    {
        char target = choice.ToString()[0];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == target)
                {
                    board[i][j] = player;
                    return true;
                }
            }
        }
        return false;
    }

    static void Main()
    {
        char player = 'X';
        bool gameOver = false;
        char[][] board = InitBoard();
        Console.WriteLine("Tic Tac Toe (Player X vs Player O)");
        PrintBoard(board);

        while (!gameOver)
        {
            Console.Write($"Player {player}, enter a cell number (1-9): ");
            string input = Console.ReadLine();
            int choice;
            if (!int.TryParse(input, out choice))
            {
                Console.WriteLine("Invalid input. Exiting.");
                break;
            }

            if (choice < 1 || choice > 9)
            {
                Console.WriteLine("Invalid input. Exiting.");
                break;
            }

            if (!MakeMove(board, player, choice))
            {
                Console.WriteLine("Cell already taken. Try again.");
                continue;
            }

            PrintBoard(board);

            if (CheckWin(board))
            {
                Console.WriteLine($"Player {player} wins!");
                gameOver = true;
            }
            else if (IsDraw(board))
            {
                Console.WriteLine("It's a draw.");
                gameOver = true;
            }
            else
            {
                player = (player == 'X') ? 'O' : 'X';
            }
        }
    }
}
