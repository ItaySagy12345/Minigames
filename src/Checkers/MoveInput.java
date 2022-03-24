package Checkers;

import java.util.Scanner;

public class MoveInput {
    private static Scanner scanner = new Scanner(System.in);

    private static final int COORDINATE_MIN = 1;
    private static final int COORDINATE_MAX = 8;
    private static final int END_GAME_CODE = 100;

    private static boolean hasPlayed;
    private static boolean hasAttemptedWest;
    private static boolean hasAttemptedEast;
    private static int boardX, boardY;
    private static int moveOption;
    private static int moveDirection;
    private static int pawnMoveDirection;
    private static int eatCount;
    private static int randomWalkDirection, randomEatDirection, randomWalkType;
    private static int walkDirectionAttemptArray[] = { 0, 0, 0, 0 };
    private static int walkDirectionAttemptArrayMaxIndex = 3;
    private static int walkTypeAttemptArray[] = { 0, 0, 0 };
    private static int walkTypeAttemptArrayMaxIndex = 2;
    private static int originalUnitLocationX, originalUnitLocationY;
    public static String pawn, king;
    public static String opposingPawn, opposingKing;
    public static String unitType;
    public static int matrixX, matrixY;
    public static int longitude, latitude;
    public static int unitLocationX, unitLocationY;
    public static int row, column;
    public static boolean requestedEndGame = false;

    // Common
    // Functions:-----------------------------------------------------------------------------*/

    public static void setPieceColor() {
        if (PlayCheckers.isPlayerTurn) {
            king = "XXX";
            opposingKing = "OOO";
            pawn = " X ";
            opposingPawn = " O ";
            pawnMoveDirection = Gameboard.NORTH;
        } else {
            king = "OOO";
            opposingKing = "XXX";
            pawn = " O ";
            opposingPawn = " X ";
            pawnMoveDirection = Gameboard.SOUTH;
        }
    }

    public static void printPieceCount(String prompt) {
        System.out.println(prompt + getPieceCount(pawn, king));
    }

    public static int getPieceCount(String pawnType, String kingType) {
        int pieceCount = 0;
        for (row = 0; row < 8; row++) {
            for (column = 0; column < 8; column++) {
                if (Gameboard.gameboard[row][column] == pawn || Gameboard.gameboard[row][column] == king) {
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    // Player
    // Functions:-----------------------------------------------------------------------------------------------*/

    public static void setPlayerUnitAndType() {
        while (true) {
            setUnitBoardCoordinates();
            if (requestedEndGame) {
                return;
            }
            setUnitMatrixCoordinates();
            setUnitLocationXYAndType();
            if (isValidUnitChoice()) {
                break;
            }
        }
    }

    public static void setUnitBoardCoordinates() {
        int coordinates = 0;
        int xCoordinate;
        int yCoordinate;
        while (true) {
            System.out.print("Pick a piece (X,Y): ");
            if (scanner.hasNextInt()) {
                coordinates = scanner.nextInt();
                if (coordinates == END_GAME_CODE) {
                    requestedEndGame = true;
                    return;
                }
                xCoordinate = coordinates / 10;
                yCoordinate = coordinates % 10;
                if (xCoordinate >= COORDINATE_MIN && xCoordinate <= COORDINATE_MAX &&
                        yCoordinate >= COORDINATE_MIN && yCoordinate <= COORDINATE_MAX) {
                    boardX = coordinates / 10;
                    boardY = coordinates % 10;
                    return;
                }
                System.out.println("Piece's individual coordinate must be between " + COORDINATE_MIN + " and "
                        + COORDINATE_MAX + ".");
            } else {
                System.out.println("Enter an integer value.");
                scanner.next();
            }
        }
    }

    public static void setUnitMatrixCoordinates() {
        matrixX = 8 - boardY;
        matrixY = boardX - 1;
    }

    public static void setUnitLocationXYAndType() {
        unitLocationX = matrixX;
        unitLocationY = matrixY;
        unitType = getUnitType();
    }

    public static String getUnitType() {
        return Gameboard.gameboard[unitLocationX][unitLocationY];
    }

    public static boolean isValidUnitChoice() {
        if (unitType == pawn) {
            // No moves (L&R):
            if (!MoveType.canWalk(Gameboard.NORTH, Gameboard.WEST) && !MoveType.canWalk(Gameboard.NORTH, Gameboard.EAST)
                    &&
                    !MoveType.canEat(Gameboard.NORTH, Gameboard.WEST)
                    && !MoveType.canEat(Gameboard.NORTH, Gameboard.EAST)) {
                System.out.println("This pawn can't move!");
                return false;
            }
        } else if (unitType == king) {
            // No moves (L&R):
            if (!MoveType.canWalk(Gameboard.NORTH, Gameboard.WEST) && !MoveType.canWalk(Gameboard.NORTH, Gameboard.EAST)
                    &&
                    !MoveType.canWalk(Gameboard.SOUTH, Gameboard.WEST)
                    && !MoveType.canWalk(Gameboard.SOUTH, Gameboard.EAST) &&
                    !MoveType.canEat(Gameboard.NORTH, Gameboard.WEST)
                    && !MoveType.canEat(Gameboard.NORTH, Gameboard.EAST) &&
                    !MoveType.canEat(Gameboard.SOUTH, Gameboard.WEST)
                    && !MoveType.canEat(Gameboard.SOUTH, Gameboard.EAST)) {
                System.out.println("This king can't move!");
                return false;
            }
        } else if (unitType == Gameboard.EMPTY) {
            System.out.println("Can't move an empty square!");
            return false;
        } else if (unitType == Gameboard.WTE) {
            System.out.println("Can't move a white square!");
            return false;
        } else if (unitType == opposingPawn || unitType == opposingKing) {
            System.out.println("Can't move an enemy unit!");
            return false;
        }
        return true;
    }

    public static void executePlayerMove() {
        while (true) {
            setMove();
            if (moveOption == 1) {
                if (MoveType.canWalk(longitude, latitude)) {
                    MoveType.walk();
                    break;
                }
                System.out.println("That move isn't available to you.");
            } else if (moveOption == 2) {
                int playerEatCount = 0;
                while (MoveType.canEat(longitude, latitude)) {
                    MoveType.eat();
                    playerEatCount++;
                    if (unitType == king) {
                        scanAndSet();
                    }
                }
                if (!MoveType.canEat(longitude, latitude) && playerEatCount > 0) {
                    if (playerEatCount == 1) {
                        System.out.println("You ate " + playerEatCount + " time!");
                        break;
                    }
                    System.out.println("You ate " + playerEatCount + " times!");
                    break;
                }
                if (!MoveType.canEat(longitude, latitude) && playerEatCount == 0) {
                    System.out.println("That move isn't available to you.");
                }
            }
        }
    }

    public static void scanAndSet() {
        if (MoveType.canEat(Gameboard.NORTH, Gameboard.WEST)) {
            longitude = Gameboard.NORTH;
            latitude = Gameboard.WEST;
        } else if (MoveType.canEat(Gameboard.NORTH, Gameboard.EAST)) {
            longitude = Gameboard.NORTH;
            latitude = Gameboard.EAST;
        } else if (MoveType.canEat(Gameboard.SOUTH, Gameboard.WEST)) {
            longitude = Gameboard.SOUTH;
            latitude = Gameboard.WEST;
        } else if (MoveType.canEat(Gameboard.SOUTH, Gameboard.EAST)) {
            longitude = Gameboard.SOUTH;
            latitude = Gameboard.EAST;
        }
    }

    public static void setMove() {
        moveOption = getValidMoveOption();
        moveDirection = getValidMoveDirection();
        setPlayerLongitudeLatitude();
    }

    public static int getValidMoveOption() {
        while (true) {
            printMoveOptionMenu();
            if (scanner.hasNextInt()) {
                moveOption = scanner.nextInt();
                if (moveOption == 1 || moveOption == 2) {
                    return moveOption;
                }
                System.out.println("You must choose between options 1 or 2.");
            } else {
                System.out.println("Enter an integer value!");
                scanner.next();
            }
        }
    }

    public static int getValidMoveDirection() {
        while (true) {
            if (unitType == pawn) {
                printPawnMoveDirectionMenu();
                if (scanner.hasNextInt()) {
                    moveDirection = scanner.nextInt();
                    if (moveDirection == 1 || moveDirection == 2) {
                        return moveDirection;
                    }
                    System.out.println("You must choose between options 1 or 2.");
                } else {
                    System.out.println("Enter an integer value!");
                    scanner.next();
                }
            } else if (unitType == king) {
                printKingMoveDirectionMenu();
                if (scanner.hasNextInt()) {
                    moveDirection = scanner.nextInt();
                    if (moveDirection >= 1 && moveDirection <= 4) {
                        return moveDirection;
                    }
                    System.out.println("You must choose between options 1, 2, 3 or 4.");
                } else {
                    System.out.println("Enter an integer value!");
                    scanner.next();
                }
            }
        }
    }

    public static void setPlayerLongitudeLatitude() {
        if (unitType == pawn) {
            longitude = Gameboard.NORTH;
            if (moveDirection == 1) {
                latitude = Gameboard.WEST;
            } else {
                latitude = Gameboard.EAST;
            }
        } else if (unitType == king) {
            if (moveDirection == 1 || moveDirection == 2) {
                longitude = Gameboard.NORTH;
                if (moveDirection == 1) {
                    latitude = Gameboard.WEST;
                } else {
                    latitude = Gameboard.EAST;
                }
            } else if (moveDirection == 3 || moveDirection == 4) {
                longitude = Gameboard.SOUTH;
                if (moveDirection == 3) {
                    latitude = Gameboard.WEST;
                } else {
                    latitude = Gameboard.EAST;
                }
            }
        }
    }

    public static void printMoveOptionMenu() {
        System.out.println("Move type: ");
        System.out.println("(1) Walk");
        System.out.println("(2) Eat");
    }

    public static void printPawnMoveDirectionMenu() {
        System.out.println("Direction: ");
        System.out.println("(1) Left");
        System.out.println("(2) Right");
    }

    public static void printKingMoveDirectionMenu() {
        System.out.println("Direction: ");
        System.out.println("(1) Up-Left");
        System.out.println("(2) Up-Right");
        System.out.println("(3) Down-Left");
        System.out.println("(4) Down-Right");
    }

    // Other Player Functions:
    // -----------------------------------------------------------------

    public static void executeCpuMove() {
        initializeKeyVariables();
        for (row = 0; row < 8 && !hasPlayed; row++) {
            for (column = 0; column < 8 && !hasPlayed; column++) {
                // (1) Eat with king:
                if (Gameboard.gameboard[row][column] == king) {
                    setCpuUnitTypeLocation(king);
                    if (!hasPlayed && cpuKingCanEat()) {
                        cpuKingEat();
                    }
                }
            }
        }
        for (row = 0; row < 8 && !hasPlayed; row++) {
            for (column = 0; column < 8 && !hasPlayed; column++) {
                // (2) Eat with pawn:
                if (Gameboard.gameboard[row][column] == pawn) {
                    setCpuUnitTypeLocation(pawn);
                    if (!hasPlayed && cpuPawnCanEat()) {
                        cpuPawnEat();
                    }
                }
            }
        }
        for (row = 0; row < 8 && !hasPlayed; row++) {
            for (column = 0; column < 8 && !hasPlayed; column++) {
                // (3) Turn pawn into king:
                if (Gameboard.gameboard[row][column] == pawn) {
                    setCpuUnitTypeLocation(pawn);
                    if (!hasPlayed && canBecomeKing(Gameboard.WEST)) {
                        MoveType.walk();
                        hasPlayed = true;
                    } else if (canBecomeKing(Gameboard.EAST)) {
                        MoveType.walk();
                        hasPlayed = true;
                    }
                }
            }
        }
        while (!hasPlayed && CheckersMath.sumArrayAttempts(walkTypeAttemptArray, walkTypeAttemptArrayMaxIndex) != 3) {
            randomWalkType = CheckersRandom.getUniqueDiceRoll(walkTypeAttemptArray, walkTypeAttemptArrayMaxIndex);
            if (randomWalkType == 0) {
                for (row = 0; row < 8 && !hasPlayed; row++) {
                    for (column = 0; column < 8 && !hasPlayed; column++) {
                        // (4) Walk with king:
                        if (Gameboard.gameboard[row][column] == king) {
                            setCpuUnitTypeLocation(king);
                            if (!hasPlayed && cpuKingCanWalk()) {
                                cpuKingWalk();
                            }
                        }
                    }
                }
                walkTypeAttemptArray[randomWalkType] = 1;
            }
            if (randomWalkType == 1) {
                for (row = 0; row < 8 && !hasPlayed; row++) {
                    for (column = 0; column < 8 && !hasPlayed; column++) {
                        // (5) Walk with pawn (scan top to bottom):
                        if (Gameboard.gameboard[row][column] == pawn) {
                            setCpuUnitTypeLocation(pawn);
                            if (!hasPlayed && cpuPawnCanWalk()) {
                                cpuPawnWalk();
                            }
                        }
                    }
                }
                walkTypeAttemptArray[randomWalkType] = 1;
            }
            if (randomWalkType == 2) {
                for (row = 7; row >= 0 && !hasPlayed; row--) {
                    for (column = 7; column >= 0 && !hasPlayed; column--) {
                        // (6) Walk with pawn (scan bottom to top):
                        if (Gameboard.gameboard[row][column] == pawn) {
                            setCpuUnitTypeLocation(pawn);
                            if (!hasPlayed && cpuPawnCanWalk()) {
                                cpuPawnWalk();
                            }
                        }
                    }
                }
                walkTypeAttemptArray[randomWalkType] = 1;
            }
        }
    }

    public static void initializeKeyVariables() {
        MoveType.hasEaten = false;
        hasPlayed = false;
        CheckersMath.initializeArrayToZero(walkDirectionAttemptArray, walkDirectionAttemptArrayMaxIndex);
        CheckersMath.initializeArrayToZero(walkTypeAttemptArray, walkTypeAttemptArrayMaxIndex);
    }

    public static void setCpuUnitTypeLocation(String unitTypeInput) {
        unitType = unitTypeInput;
        unitLocationX = row;
        originalUnitLocationX = row;
        unitLocationY = column;
        originalUnitLocationY = column;
    }

    /* (1) Eat with king:------------------------------------------ */

    public static boolean cpuKingCanEat() {
        return (MoveType.canEat(Gameboard.SOUTH, Gameboard.WEST) || MoveType.canEat(Gameboard.SOUTH, Gameboard.EAST) ||
                MoveType.canEat(Gameboard.NORTH, Gameboard.WEST) || MoveType.canEat(Gameboard.NORTH, Gameboard.EAST));
    }

    public static void cpuKingEat() {
        do {
            eatCount = 0;
            kingEating(Gameboard.SOUTH, Gameboard.WEST);
            kingEating(Gameboard.SOUTH, Gameboard.EAST);
            kingEating(Gameboard.NORTH, Gameboard.WEST);
            kingEating(Gameboard.NORTH, Gameboard.EAST);
        } while (eatCount != 0);
    }

    public static void kingEating(int longitude, int latitude) {
        while (MoveType.canEat(longitude, latitude)) {
            MoveType.eat();
            hasPlayed = true;
            eatCount++;
        }
    }

    /* (2) Eat with pawn:--------------------------------------------- */

    public static boolean cpuPawnCanEat() {
        return (MoveType.canEat(pawnMoveDirection, Gameboard.WEST)
                || MoveType.canEat(pawnMoveDirection, Gameboard.EAST));
    }

    public static void cpuPawnEat() {
        hasAttemptedWest = false;
        hasAttemptedEast = false;
        while (!hasPlayed && (!hasAttemptedWest || !hasAttemptedEast)) {
            randomEatDirection = CheckersRandom.coinToss(0, 1);
            if (randomEatDirection == 0) {
                pawnEating(pawnMoveDirection, Gameboard.WEST, hasAttemptedWest);
            }
            if (randomEatDirection == 1) {
                pawnEating(pawnMoveDirection, Gameboard.EAST, hasAttemptedEast);
            }
        }
    }

    public static void pawnEating(int longitude, int latitude, boolean hasAttemptedLatitude) {
        while (MoveType.canEat(longitude, latitude)) {
            MoveType.eat();
            hasPlayed = true;
        }
        hasAttemptedLatitude = true;
    }

    /* (3) Turn pawn into king:----------------------------------------- */

    public static boolean canBecomeKing(int latitude) {
        return unitLocationX + 1 == 7 && MoveType.canWalk(pawnMoveDirection, latitude);
    }

    /* (4) RANDOM Walk with king:----------------------------------------- */

    public static boolean cpuKingCanWalk() {
        return (MoveType.canWalk(Gameboard.SOUTH, Gameboard.WEST) || MoveType.canWalk(Gameboard.SOUTH, Gameboard.EAST)
                ||
                MoveType.canWalk(Gameboard.NORTH, Gameboard.WEST) || MoveType.canWalk(Gameboard.NORTH, Gameboard.EAST));
    }

    public static void cpuKingWalk() {
        while (!hasPlayed
                && CheckersMath.sumArrayAttempts(walkDirectionAttemptArray, walkDirectionAttemptArrayMaxIndex) != 4) {
            randomWalkDirection = CheckersRandom.getUniqueDiceRoll(walkDirectionAttemptArray,
                    walkDirectionAttemptArrayMaxIndex);
            if (randomWalkDirection == 0) {
                kingWalk(Gameboard.SOUTH, Gameboard.WEST);
            }
            if (randomWalkDirection == 1) {
                kingWalk(Gameboard.SOUTH, Gameboard.EAST);
            }
            if (randomWalkDirection == 2) {
                kingWalk(Gameboard.NORTH, Gameboard.WEST);
            }
            if (randomWalkDirection == 3) {
                kingWalk(Gameboard.NORTH, Gameboard.EAST);
            }
        }
    }

    public static void kingWalk(int longitude, int latitude) {
        if (MoveType.canWalk(longitude, latitude)) {
            MoveType.walk();
            hasPlayed = true;
        }
        walkDirectionAttemptArray[randomWalkDirection] = 1;
    }

    /* (5&6) RANDOM Walk with pawn:----------------------------------------- */

    public static boolean cpuPawnCanWalk() {
        return (MoveType.canWalk(pawnMoveDirection, Gameboard.WEST)
                || MoveType.canWalk(pawnMoveDirection, Gameboard.EAST));
    }

    public static void cpuPawnWalk() {
        hasAttemptedWest = false;
        hasAttemptedEast = false;
        while (!hasPlayed && (!hasAttemptedWest || !hasAttemptedEast)) {
            randomWalkDirection = CheckersRandom.coinToss(0, 1);
            if (randomWalkDirection == 0) {
                pawnWalk(pawnMoveDirection, Gameboard.WEST, hasAttemptedWest);
            }
            if (randomWalkDirection == 1) {
                pawnWalk(pawnMoveDirection, Gameboard.EAST, hasAttemptedEast);
            }
        }
    }

    public static void pawnWalk(int longitude, int latitude, boolean hasAttemptedLatitude) {
        if (MoveType.canWalk(longitude, latitude)) {
            MoveType.walk();
            hasPlayed = true;
        }
        hasAttemptedLatitude = true;
    }

    public static void printCpuMoveInfo() {
        if (MoveType.hasEaten) {
            System.out.println(
                    "                  CPU moved from (" + MoveType.convertToBoardCoordinateX(originalUnitLocationY)
                            + "," + MoveType.convertToBoardCoordinateY(originalUnitLocationX) +
                            ") to (" + MoveType.convertToBoardCoordinateX(unitLocationY) + ","
                            + MoveType.convertToBoardCoordinateY(unitLocationX) + ")");
        } else {
            System.out.println("                  CPU moved from (" + MoveType.convertToBoardCoordinateX(unitLocationY)
                    + "," + MoveType.convertToBoardCoordinateY(unitLocationX) +
                    ") to (" + MoveType.convertToBoardCoordinateX(MoveType.destinationY) + ","
                    + MoveType.convertToBoardCoordinateY(MoveType.destinationX) + ")");
        }
    }
}
