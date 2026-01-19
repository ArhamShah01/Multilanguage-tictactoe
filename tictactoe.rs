use std::io::{self, Write};

struct TicTacToe {
    board: [[char; 3]; 3],
}

impl TicTacToe {
    fn new() -> Self {
        let mut board = [[' '; 3]; 3];
        let mut cell = '1';
        for i in 0..3 {
            for j in 0..3 {
                board[i][j] = cell;
                cell = ((cell as u8) + 1) as char;
            }
        }
        TicTacToe { board }
    }

    fn print_board(&self) {
        println!();
        for i in 0..3 {
            println!(" {} | {} | {} ", self.board[i][0], self.board[i][1], self.board[i][2]);
            if i != 2 {
                println!("---+---+---");
            }
        }
        println!();
    }

    fn check_win(&self) -> bool {
        // rows and columns
        for i in 0..3 {
            if self.board[i][0] == self.board[i][1] && self.board[i][1] == self.board[i][2] {
                return true;
            }
            if self.board[0][i] == self.board[1][i] && self.board[1][i] == self.board[2][i] {
                return true;
            }
        }
        // diagonals
        if self.board[0][0] == self.board[1][1] && self.board[1][1] == self.board[2][2] {
            return true;
        }
        if self.board[0][2] == self.board[1][1] && self.board[1][1] == self.board[2][0] {
            return true;
        }
        false
    }

    fn is_draw(&self) -> bool {
        for i in 0..3 {
            for j in 0..3 {
                if self.board[i][j] >= '1' && self.board[i][j] <= '9' {
                    return false;
                }
            }
        }
        true
    }

    fn make_move(&mut self, player: char, choice: u32) -> bool {
        let target = std::char::from_digit(choice, 10).unwrap_or('0');
        for i in 0..3 {
            for j in 0..3 {
                if self.board[i][j] == target {
                    self.board[i][j] = player;
                    return true;
                }
            }
        }
        false
    }
}

fn main() {
    let mut game = TicTacToe::new();
    let mut player = 'X';
    let mut game_over = false;

    println!("Tic Tac Toe (Player X vs Player O)");
    game.print_board();

    while !game_over {
        print!("Player {}, enter a cell number (1-9): ", player);
        io::stdout().flush().unwrap();

        let mut input = String::new();
        if io::stdin().read_line(&mut input).is_err() {
            println!("Invalid input. Exiting.");
            break;
        }

        let choice: u32 = match input.trim().parse() {
            Ok(num) if num >= 1 && num <= 9 => num,
            _ => {
                println!("Invalid input. Exiting.");
                break;
            }
        };

        if !game.make_move(player, choice) {
            println!("Cell already taken. Try again.");
            continue;
        }

        game.print_board();

        if game.check_win() {
            println!("Player {} wins!", player);
            game_over = true;
        } else if game.is_draw() {
            println!("It's a draw.");
            game_over = true;
        } else {
            player = if player == 'X' { 'O' } else { 'X' };
        }
    }
}
