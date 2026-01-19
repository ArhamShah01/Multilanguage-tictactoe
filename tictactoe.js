function initBoard() {
    const board = [];
    let cell = '1'.charCodeAt(0);
    for (let i = 0; i < 3; i++) {
        const row = [];
        for (let j = 0; j < 3; j++) {
            row.push(String.fromCharCode(cell));
            cell += 1;
        }
        board.push(row);
    }
    return board;
}

function printBoard(board) {
    console.log();
    for (let i = 0; i < 3; i++) {
        console.log(` ${board[i][0]} | ${board[i][1]} | ${board[i][2]} `);
        if (i !== 2) {
            console.log("---+---+---");
        }
    }
    console.log();
}

function checkWin(board) {
    // rows and columns
    for (let i = 0; i < 3; i++) {
        if (board[i][0] === board[i][1] && board[i][1] === board[i][2]) {
            return true;
        }
        if (board[0][i] === board[1][i] && board[1][i] === board[2][i]) {
            return true;
        }
    }
    // diagonals
    if (board[0][0] === board[1][1] && board[1][1] === board[2][2]) {
        return true;
    }
    if (board[0][2] === board[1][1] && board[1][1] === board[2][0]) {
        return true;
    }
    return false;
}

function isDraw(board) {
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (board[i][j] >= '1' && board[i][j] <= '9') {
                return false;
            }
        }
    }
    return true;
}

function makeMove(board, player, choice) {
    const target = String(choice);
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (board[i][j] === target) {
                board[i][j] = player;
                return true;
            }
        }
    }
    return false;
}

async function main() {
    const readline = require('readline').createInterface({
        input: process.stdin,
        output: process.stdout
    });

    function question(prompt) {
        return new Promise((resolve) => {
            readline.question(prompt, resolve);
        });
    }

    let player = 'X';
    let gameOver = false;
    const board = initBoard();
    console.log("Tic Tac Toe (Player X vs Player O)");
    printBoard(board);

    while (!gameOver) {
        let choiceStr = await question(`Player ${player}, enter a cell number (1-9): `);
        let choice = parseInt(choiceStr, 10);

        if (isNaN(choice)) {
            console.log("Invalid input. Exiting.");
            break;
        }

        if (choice < 1 || choice > 9) {
            console.log("Invalid input. Exiting.");
            break;
        }

        if (!makeMove(board, player, choice)) {
            console.log("Cell already taken. Try again.");
            continue;
        }

        printBoard(board);

        if (checkWin(board)) {
            console.log(`Player ${player} wins!`);
            gameOver = true;
        } else if (isDraw(board)) {
            console.log("It's a draw.");
            gameOver = true;
        } else {
            player = player === 'X' ? 'O' : 'X';
        }
    }

    readline.close();
}

if (require.main === module) {
    main();
}
