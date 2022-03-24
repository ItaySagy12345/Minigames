package Checkers;

public class PlayerMove {

    public static void playerTurn() throws InterruptedException {
        // Step 1: Set opposition:
        MoveInput.setPieceColor();

        // Step 2: Print game info:
        Gameboard.printCpuGameboard();
        MoveInput.printPieceCount("Your piece count = ");

        // Step 3: Pick unit:
        MoveInput.setPlayerUnitAndType();
        if (MoveInput.requestedEndGame) {
            return;
        }

        // Step 4: Make move:
        MoveInput.executePlayerMove();

        // Step 5: Print gameboard:
        Gameboard.printPlayerGameboard();
    }
}
