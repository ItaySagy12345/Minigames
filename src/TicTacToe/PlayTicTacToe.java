package TicTacToe;

public class PlayTicTacToe {
    private static final String PLAYER_MARKER = " X ";
    private static final String CPU_MARKER = " O ";

    private static String marker;
    public static int[] occupiedPositionsArray = {0,0,0,0,0,0,0,0,0};
    public static int moveCount;
    public static int position;
    public static boolean isPlayerTurn;
    public static boolean isFirstTurn;
    public static boolean playerHasWon;
    public static boolean tie;

    public static int playGame() throws InterruptedException {
        Gameboard.printGameRules();
        Gameboard.initializeGameboard();
        initializeNewGameVariables();
        while (true) {
            if (!isFirstTurn) {
                Thread.sleep(500);
            }
            if (isPlayerTurn) {
                if (isFirstTurn) {
                    initiateFirstMoveChecklist("                       Go first!");
                    Gameboard.printPlayerGameboard();
                }
                PlayerMove.playerTurn();
                if (PlayerMove.requestedEndGame) {
                    return 0;
                }
                initializeInGameVariables(PLAYER_MARKER);
            }
            else {
                if (isFirstTurn) {
                    initiateFirstMoveChecklist("                  CPU made first move!");
                }
                CpuMove.cpuTurn();
                initializeInGameVariables(CPU_MARKER);
            }
            switch (position) {
                case 1:
                    Gameboard.gameboard[0][0] = marker;
                    break;
                case 2:
                    Gameboard.gameboard[0][1] = marker;
                    break;
                case 3:
                    Gameboard.gameboard[0][2] = marker;
                    break;
                case 4:
                    Gameboard.gameboard[1][0] = marker;
                    break;
                case 5:
                    Gameboard.gameboard[1][1] = marker;
                    break;
                case 6:
                    Gameboard.gameboard[1][2] = marker;
                    break;
                case 7:
                    Gameboard.gameboard[2][0] = marker;
                    break;
                case 8:
                    Gameboard.gameboard[2][1] = marker;
                    break;
                case 9:
                    Gameboard.gameboard[2][2] = marker;
                    break;
            }
            updateStats();
            switchTurns();
            if (GameStatus.hasWinner()) {
                GameStatus.endGameScript();
                return GameStatus.getPoints();
            }
        }
    }

    public static void initializeNewGameVariables() {
        PlayerMove.requestedEndGame = false;
        playerHasWon = false;
        tie = false;
        isFirstTurn = true;
        isPlayerTurn = TicTacToeRandom.coinToss(0, 1) == 0;
        moveCount = 0;
        for (int index = 0; index < 9; index++) {
            occupiedPositionsArray[index] = 0;
        }
    }

    public static void initiateFirstMoveChecklist(String prompt) {
        System.out.println(prompt);
        isFirstTurn = false;
    }

    public static void initializeInGameVariables(String marketType) {
        occupiedPositionsArray[position-1] = 1;
        marker = marketType;
    }

    public static void updateStats() {
        if (isPlayerTurn) {
            Gameboard.printPlayerGameboard();
        }
        else {
            Gameboard.printCpuGameboard();
        }

        moveCount++;
    }

    public static void switchTurns() {
        isPlayerTurn = !isPlayerTurn;
    }
}
