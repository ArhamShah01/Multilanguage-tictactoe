import java.util.Scanner

fun initBoard(): Array<CharArray> {
    val board = Array(3) { CharArray(3) }
    var cell = '1'
    for (i in 0..2) {
        for (j in 0..2) {
            board[i][j] = cell
            cell++
        }
    }
    return board
}

fun printBoard(board: Array<CharArray>) {
    println()
    for (i in 0..2) {
        println(" ${board[i][0]} | ${board[i][1]} | ${board[i][2]} ")
        if (i != 2) {
            println("---+---+---")
        }
    }
    println()
}

fun checkWin(board: Array<CharArray>): Boolean {
    for (i in 0..2) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
            return true
        }
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
            return true
        }
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        return true
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        return true
    }
    return false
}

fun isDraw(board: Array<CharArray>): Boolean {
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] in '1'..'9') {
                return false
            }
        }
    }
    return true
}

fun makeMove(board: Array<CharArray>, player: Char, choice: Int): Boolean {
    val target = choice.toString()[0]
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == target) {
                board[i][j] = player
                return true
            }
        }
    }
    return false
}

fun main() {
    val scanner = Scanner(System.`in`)
    var player = 'X'
    var gameOver = false
    val board = initBoard()
    println("Tic Tac Toe (Player X vs Player O)")
    printBoard(board)

    while (!gameOver) {
        print("Player $player, enter a cell number (1-9): ")
        val input = scanner.nextLine()
        val choice = input.toIntOrNull()
        if (choice == null || choice !in 1..9) {
            println("Invalid input. Exiting.")
            break
        }

        if (!makeMove(board, player, choice)) {
            println("Cell already taken. Try again.")
            continue
        }

        printBoard(board)

        when {
            checkWin(board) -> {
                println("Player $player wins!")
                gameOver = true
            }
            isDraw(board) -> {
                println("It's a draw.")
                gameOver = true
            }
            else -> {
                player = if (player == 'X') 'O' else 'X'
            }
        }
    }
}
