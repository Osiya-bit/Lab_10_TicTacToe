
import java.util.Scanner;

public class TicTacToe
{
    // Class-level constants
    private static final int ROWS = 3;
    private static final int COLS = 3;

    // The Tic Tac Toe board
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        boolean playAgain;

        /*
         * PSEUDOCODE:
         * 1. Clear the board.
         * 2. Set X as the first player.
         * 3. Display the board.
         * 4. Ask the current player for a row and column.
         * 5. Check whether the move is valid.
         * 6. Place the player's symbol on the board.
         * 7. Check for a win or tie.
         * 8. Switch players if the game is not over.
         * 9. Ask whether the players want another game.
         */

        do
        {
            clearBoard();

            String player = "X";
            int moves = 0;
            boolean gameOver = false;

            System.out.println("Welcome to Tic Tac Toe!");

            while (!gameOver)
            {
                display();

                // Get the player's row and column
                int row = SafeInput.getRangedInt(console,
                        "Player " + player + ", enter row (1-3): ", 1, 3) - 1;

                int col = SafeInput.getRangedInt(console,
                        "Player " + player + ", enter column (1-3): ", 1, 3) - 1;

                // Keep asking until the move is valid
                while (!isValidMove(row, col))
                {
                    System.out.println("That space is already taken. Try again.");

                    display();

                    row = SafeInput.getRangedInt(console,
                            "Player " + player + ", enter row (1-3): ", 1, 3) - 1;

                    col = SafeInput.getRangedInt(console,
                            "Player " + player + ", enter column (1-3): ", 1, 3) - 1;
                }

                // Place the player's symbol
                board[row][col] = player;
                moves++;

                // Check for a winner
                if (isWin(player))
                {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                }

                // Check for a tie
                else if (isTie())
                {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                }

                // Switch players
                else
                {
                    if (player.equals("X"))
                    {
                        player = "O";
                    }
                    else
                    {
                        player = "X";
                    }
                }
            }

            // Ask whether players want another game
            playAgain = SafeInput.getYNConfirm(console,
                    "Would you like to play again? (Y/N): ");

        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
        console.close();
    }

    // Clears the board
    private static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    // Displays the board
    private static void display()
    {
        System.out.println();

        for (int row = 0; row < ROWS; row++)
        {
            System.out.print(" ");

            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col]);

                if (col < COLS - 1)
                {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (row < ROWS - 1)
            {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Checks whether a move is valid
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    // Checks for a horizontal win
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            boolean win = true;

            for (int col = 0; col < COLS; col++)
            {
                if (!board[row][col].equals(player))
                {
                    win = false;
                }
            }

            if (win)
            {
                return true;
            }
        }

        return false;
    }

    // Checks for a vertical win
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            boolean win = true;

            for (int row = 0; row < ROWS; row++)
            {
                if (!board[row][col].equals(player))
                {
                    win = false;
                }
            }

            if (win)
            {
                return true;
            }
        }

        return false;
    }

    // Checks for a diagonal win
    private static boolean isDiagnalWin(String player)
    {
        boolean diagonal1 =
                board[0][0].equals(player) &&
                        board[ROWS / 2][COLS / 2].equals(player) &&
                        board[ROWS - 1][COLS - 1].equals(player);

        boolean diagonal2 =
                board[0][COLS - 1].equals(player) &&
                        board[ROWS / 2][COLS / 2].equals(player) &&
                        board[ROWS - 1][0].equals(player);

        return diagonal1 || diagonal2;
    }

    // Checks whether the player has won
    private static boolean isWin(String player)
    {
        return isRowWin(player) ||
                isColWin(player) ||
                isDiagnalWin(player);
    }

    // Checks whether a winning line is blocked by both players
    private static boolean isBlocked(int r1, int c1,
                                     int r2, int c2,
                                     int r3, int c3)
    {
        boolean hasX = false;
        boolean hasO = false;

        String[] line = {
                board[r1][c1],
                board[r2][c2],
                board[r3][c3]
        };

        for (String cell : line)
        {
            if (cell.equals("X"))
            {
                hasX = true;
            }

            if (cell.equals("O"))
            {
                hasO = true;
            }
        }

        return hasX && hasO;
    }

    // Checks whether the game is a tie
    private static boolean isTie()
    {
        boolean full = true;

        // First, check whether the board is full
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(" "))
                {
                    full = false;
                }
            }
        }

        if (full)
        {
            return true;
        }

        // Check whether all three rows are blocked
        // Check whether all three columns are blocked
        // Check whether both diagonals are blocked
        return isBlocked(0, 0, 0, 1, 0, 2) &&
                isBlocked(1, 0, 1, 1, 1, 2) &&
                isBlocked(2, 0, 2, 1, 2, 2) &&
                isBlocked(0, 0, 1, 0, 2, 0) &&
                isBlocked(0, 1, 1, 1, 2, 1) &&
                isBlocked(0, 2, 1, 2, 2, 2) &&
                isBlocked(0, 0, 1, 1, 2, 2) &&
                isBlocked(0, 2, 1, 1, 2, 0);
    }
}
