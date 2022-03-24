package Checkers;

public class PlayCheckers {
    public static boolean isPlayerTurn;
    public static boolean isFirstTurn;
    public static boolean playerHasWon;

    public static int playGame() throws InterruptedException {
        Gameboard.printRules();
        Gameboard.initializeGameboard();
        initializeNewGameVariables();
        while (true) {
            if (!isFirstTurn) {
                Thread.sleep(500);
            }
            if (isPlayerTurn) {
                if (isFirstTurn) {
                    System.out.println("                          You go first!");
                    isFirstTurn = false;
                }
                PlayerMove.playerTurn();
                if (MoveInput.requestedEndGame) {
                    return 0;
                }
            } else {
                if (isFirstTurn) {
                    System.out.println("                          CPU goes first!");
                    isFirstTurn = false;
                }
                CpuMove.cpuTurn();
            }
            if (GameStatus.hasWinner()) {
                GameStatus.endGameScript();
                if (playerHasWon) {
                    return 1;
                } else {
                    Gameboard.printCpuGameboard();
                    return -1;
                }
            } else {
                switchTurns();
            }
        }
    }

    public static void initializeNewGameVariables() {
        MoveInput.requestedEndGame = false;
        isFirstTurn = true;
        isPlayerTurn = CheckersRandom.coinToss(0, 1) == 0;
    }

    public static void switchTurns() {
        isPlayerTurn = !isPlayerTurn;
    }
}