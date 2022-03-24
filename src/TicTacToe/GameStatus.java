package TicTacToe;

public class GameStatus {

    public static boolean hasWinner() throws InterruptedException {
        return hasPlayerWinAlignment() || hasCpuWinAlignment() || (PlayTicTacToe.moveCount == 9);
    }

    public static void endGameScript() throws InterruptedException {
        if (hasPlayerWinAlignment()) {
            PlayTicTacToe.playerHasWon = true;
            printEndGameScript("                       You win!");
        }
        else if (hasCpuWinAlignment()) {
            PlayTicTacToe.playerHasWon = false;
            printEndGameScript("                       You lose...");
        }
        else if (PlayTicTacToe.moveCount == 9) {
            PlayTicTacToe.playerHasWon = false;
            PlayTicTacToe.tie = true;
            printEndGameScript("                         Tie!");
        }
    }

    public static void printEndGameScript(String script) throws InterruptedException {
        System.out.println(script);
        System.out.println();
        System.out.println();
        Thread.sleep(700);
    }

    public static int getPoints() {
        if (PlayTicTacToe.playerHasWon) {
            return 1;
        }
        else if (!PlayTicTacToe.playerHasWon && PlayTicTacToe.tie) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public static boolean hasPlayerWinAlignment() {
        if (Gameboard.gameboard[0][0] == " X " && Gameboard.gameboard[0][1] == " X " && Gameboard.gameboard[0][2] == " X " || // row1 Player1:
            Gameboard.gameboard[1][0] == " X " && Gameboard.gameboard[1][1] == " X " && Gameboard.gameboard[1][2] == " X " || // row2
            Gameboard.gameboard[2][0] == " X " && Gameboard.gameboard[2][1] == " X " && Gameboard.gameboard[2][2] == " X " || // row3
            Gameboard.gameboard[0][0] == " X " && Gameboard.gameboard[1][0] == " X " && Gameboard.gameboard[2][0] == " X " || // col1
            Gameboard.gameboard[0][1] == " X " && Gameboard.gameboard[1][1] == " X " && Gameboard.gameboard[2][1] == " X " || // col2
            Gameboard.gameboard[0][2] == " X " && Gameboard.gameboard[1][2] == " X " && Gameboard.gameboard[2][2] == " X " || // col3
            Gameboard.gameboard[0][0] == " X " && Gameboard.gameboard[1][1] == " X " && Gameboard.gameboard[2][2] == " X " || // cross1
            Gameboard.gameboard[0][2] == " X " && Gameboard.gameboard[1][1] == " X " && Gameboard.gameboard[2][0] == " X ") { // cross2
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean hasCpuWinAlignment() {
        if (Gameboard.gameboard[0][0] == " O " && Gameboard.gameboard[0][1] == " O " && Gameboard.gameboard[0][2] == " O " || // row1 Player1:
            Gameboard.gameboard[1][0] == " O " && Gameboard.gameboard[1][1] == " O " && Gameboard.gameboard[1][2] == " O " || // row2
            Gameboard.gameboard[2][0] == " O " && Gameboard.gameboard[2][1] == " O " && Gameboard.gameboard[2][2] == " O " || // row3
            Gameboard.gameboard[0][0] == " O " && Gameboard.gameboard[1][0] == " O " && Gameboard.gameboard[2][0] == " O " || // col1
            Gameboard.gameboard[0][1] == " O " && Gameboard.gameboard[1][1] == " O " && Gameboard.gameboard[2][1] == " O " || // col2
            Gameboard.gameboard[0][2] == " O " && Gameboard.gameboard[1][2] == " O " && Gameboard.gameboard[2][2] == " O " || // col3
            Gameboard.gameboard[0][0] == " O " && Gameboard.gameboard[1][1] == " O " && Gameboard.gameboard[2][2] == " O " || // cross1
            Gameboard.gameboard[0][2] == " O " && Gameboard.gameboard[1][1] == " O " && Gameboard.gameboard[2][0] == " O ") { // cross2
            return true;
        }
        else {
            return false;
        }
    }
}
