import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [] [] = new String[ROW][COL];
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);

       String playerX = "";
       String playerO = "";
       String symbolPlyX = "";
       String symbolPlyO = "";
       int turn = 0;
       int verticalMove = 0;
       int parallelMove = 0;
       boolean gameOver = false;
       boolean playAgain = false;

       do {
           System.out.println("\n       Tic Tac Toe");
           playerX = SafeInput.getNonZeroLenString(in,"Player X, what is your name");
           symbolPlyX = SafeInput.getregExString(in, "[X or O] ", "[XxOo]");
           symbolPlyX = symbolPlyX.toUpperCase();
           System.out.println(playerX + " chooses " + symbolPlyX);
           playerO = SafeInput.getNonZeroLenString(in, "Player O, enter your name");
           if (symbolPlyX.equals("X")) {
               symbolPlyO = "O";
               turn = 1;
           } else {
               symbolPlyO = "X";
               turn = 2;
           }
           System.out.println(playerO + " has played " + symbolPlyO);
           System.out.println("\nX goes first.");
           clearBoard();

           do {
               display();

               while (true) {
                   if (turn == 1) {
                       System.out.println("Player " + turn + " - " + playerX + "'s turn");
                       parallelMove = SafeInput.getRangedInt(in,"Enter Row", 1,3) - 1;
                       verticalMove = SafeInput.getRangedInt(in,"Enter Col",1,3) - 1;
                       if (isValidMove(parallelMove, verticalMove) == false) {
                           System.out.println("Invalid Move! Go Again.");
                       } else {
                           break;
                       }
                   } else if (turn == 2) {
                       System.out.println("Player " + turn + "- " + playerO + "'s turn");
                       parallelMove = SafeInput.getRangedInt(in,"Enter Row", 1,3) - 1;
                       verticalMove = SafeInput.getRangedInt(in,"Enter Col",1,3) - 1;
                       if (isValidMove(parallelMove,verticalMove) == false) {
                           System.out.println("Invalid Move! Go Again");
                       } else {
                           break;
                       }
                   }
               }
               if (turn == 1) {
                   board[parallelMove][verticalMove] = symbolPlyX;
               } else if (turn == 2) {
                   board[parallelMove][verticalMove] = symbolPlyO;

               }
               if (isWin(symbolPlyX)) {
                   display();
                   System.out.println(playerX = " Winnner!");
                   gameOver = true;
               } else if (isWin(symbolPlyO)) {
                   display();
                   System.out.println(playerO + " Winner!");
                   gameOver = true;
               } else if (isTie()) {
                   display();
                   System.out.println("We have a Tie!");
                   gameOver = true;

               }
               if (turn == 1) {
                   turn = 2;
               } else {
                   turn = 1;
               }
           } while (!gameOver);
           gameOver = false;
           playAgain = SafeInput.getYNConfirm(in,"Play again? ");

       } while (playAgain);
    }
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }
    private static void display() {

        System.out.println();
        System.out.println("-------------");
        for (int i  =0; i < ROW; i++){
            for (int j =0; j < COL; j++){
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    private static boolean isValidMove(int ROW, int COL) {
        boolean Valid = true;
        if (board[ROW][COL] != " "){
            Valid = false;
        }

        return Valid;
    }
    private static boolean isWin(String symbol) {
        boolean isWinner = false;
        if (isRowWin(symbol)) {
            isWinner = true;
        }
        if (isColWin(symbol)) {
            isWinner = true;
        }
        if (isDiagonalWin(symbol)) {
            isWinner = true;
        }
        return isWinner;
    }
    private static boolean isRowWin(String symbol) {
        boolean isWinner = false;
        for (int i = 0; i < ROW; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                isWinner = true;
            }
        }
        return isWinner;
    }
    private static boolean isColWin(String symbol) {
        boolean isWinner = false;
        for (int j = 0; j < COL; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                isWinner = true;
            }
        }
        return isWinner;
    }
    private static boolean isDiagonalWin(String symbol) {
        boolean isWinner = false;
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            isWinner = true;
        } else if (board[0] [2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            isWinner = true;
        }
        return isWinner;
    }
    private static boolean isTie() {
        boolean itsATie  = true;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j] == " ") {
                    itsATie = false;
                }
            }
        }
        return itsATie;
    }
}