package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func initBoard() [][]string {
	board := make([][]string, 3)
	cell := '1'
	for i := 0; i < 3; i++ {
		row := make([]string, 3)
		for j := 0; j < 3; j++ {
			row[j] = string(cell)
			cell++
		}
		board[i] = row
	}
	return board
}

func printBoard(board [][]string) {
	fmt.Println()
	for i := 0; i < 3; i++ {
		fmt.Printf(" %s | %s | %s \n", board[i][0], board[i][1], board[i][2])
		if i != 2 {
			fmt.Println("---+---+---")
		}
	}
	fmt.Println()
}

func checkWin(board [][]string) bool {
	// rows and columns
	for i := 0; i < 3; i++ {
		if board[i][0] == board[i][1] && board[i][1] == board[i][2] {
			return true
		}
		if board[0][i] == board[1][i] && board[1][i] == board[2][i] {
			return true
		}
	}
	// diagonals
	if board[0][0] == board[1][1] && board[1][1] == board[2][2] {
		return true
	}
	if board[0][2] == board[1][1] && board[1][1] == board[2][0] {
		return true
	}
	return false
}

func isDraw(board [][]string) bool {
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if board[i][j] >= "1" && board[i][j] <= "9" {
				return false
			}
		}
	}
	return true
}

func makeMove(board [][]string, player string, choice int) bool {
	target := strconv.Itoa(choice)
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if board[i][j] == target {
				board[i][j] = player
				return true
			}
		}
	}
	return false
}

func main() {
	player := "X"
	gameOver := false
	board := initBoard()
	fmt.Println("Tic Tac Toe (Player X vs Player O)")
	printBoard(board)

	scanner := bufio.NewScanner(os.Stdin)

	for !gameOver {
		fmt.Printf("Player %s, enter a cell number (1-9): ", player)
		if !scanner.Scan() {
			fmt.Println("Invalid input. Exiting.")
			break
		}
		input := strings.TrimSpace(scanner.Text())
		choice, err := strconv.Atoi(input)
		if err != nil {
			fmt.Println("Invalid input. Exiting.")
			break
		}

		if choice < 1 || choice > 9 {
			fmt.Println("Invalid input. Exiting.")
			break
		}

		if !makeMove(board, player, choice) {
			fmt.Println("Cell already taken. Try again.")
			continue
		}

		printBoard(board)

		if checkWin(board) {
			fmt.Printf("Player %s wins!\n", player)
			gameOver = true
		} else if isDraw(board) {
			fmt.Println("It's a draw.")
			gameOver = true
		} else {
			if player == "X" {
				player = "O"
			} else {
				player = "X"
			}
		}
	}
}
