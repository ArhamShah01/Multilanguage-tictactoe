def init_board
  board = []
  cell = '1'.ord
  3.times do
    row = []
    3.times do
      row << cell.chr
      cell += 1
    end
    board << row
  end
  board
end

def print_board(board)
  puts
  3.times do |i|
    puts " #{board[i][0]} | #{board[i][1]} | #{board[i][2]} "
    puts "---+---+---" if i != 2
  end
  puts
end

def check_win(board)
  3.times do |i|
    return true if board[i][0] == board[i][1] && board[i][1] == board[i][2]
    return true if board[0][i] == board[1][i] && board[1][i] == board[2][i]
  end
  return true if board[0][0] == board[1][1] && board[1][1] == board[2][2]
  return true if board[0][2] == board[1][1] && board[1][1] == board[2][0]
  false
end

def is_draw(board)
  3.times do |i|
    3.times do |j|
      return false if ('1'..'9').include?(board[i][j])
    end
  end
  true
end

def make_move(board, player, choice)
  target = choice.to_s
  3.times do |i|
    3.times do |j|
      if board[i][j] == target
        board[i][j] = player
        return true
      end
    end
  end
  false
end

def main
  player = 'X'
  game_over = false
  board = init_board
  puts "Tic Tac Toe (Player X vs Player O)"
  print_board(board)

  until game_over
    print "Player #{player}, enter a cell number (1-9): "
    input = gets.chomp
    begin
      choice = Integer(input)
    rescue ArgumentError
      puts "Invalid input. Exiting."
      break
    end

    if choice < 1 || choice > 9
      puts "Invalid input. Exiting."
      break
    end

    unless make_move(board, player, choice)
      puts "Cell already taken. Try again."
      next
    end

    print_board(board)

    if check_win(board)
      puts "Player #{player} wins!"
      game_over = true
    elsif is_draw(board)
      puts "It's a draw."
      game_over = true
    else
      player = (player == 'X' ? 'O' : 'X')
    end
  end
end

if __FILE__ == $0
  main
end
