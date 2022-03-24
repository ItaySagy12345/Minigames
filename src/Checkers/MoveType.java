package Checkers;

public class MoveType {
    public final static int ONE_PACE = 1;
    public final static int TWO_PACES = 2;

    private static int onePaceBackX, onePaceBackY;
    public static int destinationX, destinationY;
    public static boolean hasEaten;

    // Walk functions:
    public static boolean canWalk(int longitude, int latitude) {
        destinationX = getXCoordinate(longitude, latitude, ONE_PACE);
        destinationY = getYCoordinate(longitude, latitude, ONE_PACE);
        if (areValidCoordinates()) {
            if (Gameboard.gameboard[destinationX][destinationY] == Gameboard.EMPTY) {
                return true;
            }
            return false;
        }
        return false;
    }

    // Check game status override:
    public static boolean canWalk(int row, int column, int longitude, int latitude, int pace) {
        destinationX = getXCoordinate(row, longitude, latitude, pace);
        destinationY = getYCoordinate(column, longitude, latitude, pace);
        if (areValidCoordinates()) {
            if (Gameboard.gameboard[destinationX][destinationY] == Gameboard.EMPTY) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void walk() {
        if (PlayCheckers.isPlayerTurn && destinationX == 0) {
            postWalkChanges(MoveInput.king);
        } else if (!PlayCheckers.isPlayerTurn && destinationX == 7) {
            postWalkChanges(MoveInput.king);
        } else {
            postWalkChanges(MoveInput.unitType);
        }
    }

    public static void postWalkChanges(String unitType) {
        Gameboard.gameboard[destinationX][destinationY] = unitType;
        Gameboard.gameboard[MoveInput.unitLocationX][MoveInput.unitLocationY] = Gameboard.EMPTY;
    }

    // Eat functions:
    public static boolean canEat(int longitude, int latitude) {
        destinationX = getXCoordinate(longitude, latitude, TWO_PACES);
        destinationY = getYCoordinate(longitude, latitude, TWO_PACES);
        if (areValidCoordinates()) {
            onePaceBackX = getXCoordinate(longitude, latitude, ONE_PACE);
            onePaceBackY = getYCoordinate(longitude, latitude, ONE_PACE);
            if (Gameboard.gameboard[destinationX][destinationY] == Gameboard.EMPTY &&
                    (Gameboard.gameboard[onePaceBackX][onePaceBackY] == MoveInput.opposingPawn ||
                            Gameboard.gameboard[onePaceBackX][onePaceBackY] == MoveInput.opposingKing)) {
                return true;
            }
            return false;
        }
        return false;
    }

    // Check game status override:
    public static boolean canEat(int row, int column, int longitude, int latitude, int pace, int paces) {
        destinationX = getXCoordinate(row, longitude, latitude, paces);
        destinationY = getYCoordinate(column, longitude, latitude, paces);
        if (areValidCoordinates()) {
            onePaceBackX = getXCoordinate(row, longitude, latitude, pace);
            onePaceBackY = getYCoordinate(column, longitude, latitude, pace);
            if (Gameboard.gameboard[destinationX][destinationY] == Gameboard.EMPTY &&
                    (Gameboard.gameboard[onePaceBackX][onePaceBackY] == MoveInput.opposingPawn ||
                            Gameboard.gameboard[onePaceBackX][onePaceBackY] == MoveInput.opposingKing)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void eat() {
        hasEaten = true;
        if (PlayCheckers.isPlayerTurn && destinationX == 0) {
            postEatChanges(MoveInput.king);
        } else if (!PlayCheckers.isPlayerTurn && destinationX == 7) {
            postEatChanges(MoveInput.king);
        } else {
            postEatChanges(MoveInput.unitType);
        }
    }

    public static void postEatChanges(String unitType) {
        Gameboard.gameboard[destinationX][destinationY] = unitType;
        Gameboard.gameboard[onePaceBackX][onePaceBackY] = Gameboard.EMPTY;
        Gameboard.gameboard[MoveInput.unitLocationX][MoveInput.unitLocationY] = Gameboard.EMPTY;
        MoveInput.unitLocationX = destinationX;
        MoveInput.unitLocationY = destinationY;
    }

    // Coordinates functions:
    public static int getXCoordinate(int longitude, int latitude, int paces) {
        return MoveInput.unitLocationX + (paces * longitude);
    }

    public static int getYCoordinate(int longitude, int latitude, int paces) {
        return MoveInput.unitLocationY + (paces * latitude);
    }

    // Check game status modified functions:
    public static int getXCoordinate(int row, int longitude, int latitude, int paces) {
        return row + (paces * longitude);
    }

    public static int getYCoordinate(int column, int longitude, int latitude, int paces) {
        return column + (paces * latitude);
    }
    // -------------------------------------

    public static boolean areValidCoordinates() {
        if (destinationX <= 7 && destinationX >= 0 && destinationY <= 7 && destinationY >= 0) {
            return true;
        }
        return false;
    }

    public static int convertToBoardCoordinateX(int matrixY) {
        return matrixY + 1;
    }

    public static int convertToBoardCoordinateY(int matrixX) {
        return 8 - matrixX;
    }
}
