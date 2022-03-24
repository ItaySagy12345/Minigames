package Checkers;

public class GameStatus {
    private static int row, column;
    private static int longitude;

    public static boolean hasWinner() {
        setLongitude();
        return !hasMoves();
    }

    public static void setLongitude() {
        if (PlayCheckers.isPlayerTurn) {
            longitude = Gameboard.SOUTH;
        }
        else {
            longitude = Gameboard.NORTH;
        }
    }

    public static boolean hasMoves() {
        if (MoveInput.getPieceCount(MoveInput.opposingPawn, MoveInput.opposingKing) == 0) {
            return false;
        }
        for (row=0; row<8; row++) {
            for (column=0; column<8; column++) {
                if (Gameboard.gameboard[row][column] == MoveInput.opposingPawn) {
                    if (MoveType.canWalk(row, column, longitude, Gameboard.WEST, MoveType.ONE_PACE) || MoveType.canWalk(row, column, longitude, Gameboard.EAST, MoveType.ONE_PACE) ||
                        MoveType.canEat(row, column, longitude, Gameboard.WEST, MoveType.ONE_PACE, MoveType.TWO_PACES) || MoveType.canEat(row, column, longitude, Gameboard.EAST, MoveType.ONE_PACE, MoveType.TWO_PACES)) {
                        return true;
                    }
                }
                else if (Gameboard.gameboard[row][column] == MoveInput.opposingKing) {
                    if (MoveType.canWalk(row, column, Gameboard.NORTH, Gameboard.WEST, MoveType.ONE_PACE) || MoveType.canWalk(row, column, Gameboard.NORTH, Gameboard.EAST, MoveType.ONE_PACE) ||
                        MoveType.canWalk(row, column, Gameboard.SOUTH, Gameboard.WEST, MoveType.ONE_PACE) || MoveType.canWalk(row, column, Gameboard.SOUTH, Gameboard.EAST, MoveType.ONE_PACE) ||
                        MoveType.canEat(row, column, Gameboard.NORTH, Gameboard.WEST, MoveType.ONE_PACE, MoveType.TWO_PACES) || MoveType.canEat(row, column, Gameboard.NORTH, Gameboard.EAST, MoveType.ONE_PACE, MoveType.TWO_PACES) ||
                        MoveType.canEat(row, column, Gameboard.SOUTH, Gameboard.WEST, MoveType.ONE_PACE, MoveType.TWO_PACES) || MoveType.canEat(row, column, Gameboard.SOUTH, Gameboard.EAST, MoveType.ONE_PACE, MoveType.TWO_PACES)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void endGameScript() throws InterruptedException {
        if (longitude == Gameboard.SOUTH) {
            PlayCheckers.playerHasWon = true;
            printEndGameScript("Cpu has no valid moves available!", "You win!");
        }
        else {
            printEndGameScript("You have no valid moves available!", "You lose :(");
            PlayCheckers.playerHasWon = false;
        }
    }

    public static void printEndGameScript(String movesStatement, String verdict) throws InterruptedException {
        System.out.println(movesStatement);
        System.out.println(verdict);
        System.out.println();
        System.out.println();
        Thread.sleep(700);
    }
}
