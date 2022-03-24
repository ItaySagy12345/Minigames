package TicTacToe;

import java.util.Scanner;

public class PlayerMove {
    private static Scanner scanner = new Scanner(System.in);

    private static final int MIN_POSITION_VALUE = 1;
    private static final int MAX_POSITION_VALUE = 9;
    private static final int END_GAME_CODE = 100;

    public static boolean requestedEndGame = false;
    
    public static void playerTurn() {
        while (true) {
            System.out.print("Make a move (1-9): ");
            if (scanner.hasNextInt()) {
                PlayTicTacToe.position = scanner.nextInt();
                if (PlayTicTacToe.position == END_GAME_CODE) {
                    requestedEndGame = true;
                    return;
                }
                if (PlayTicTacToe.position >= MIN_POSITION_VALUE && 
                    PlayTicTacToe.position <= MAX_POSITION_VALUE && 
                    PlayTicTacToe.occupiedPositionsArray[PlayTicTacToe.position-1] != 1) {
                    break;
                }
                System.out.println("Enter a valid section!");
            }
            else {
                System.out.print("Enter a valid integer value!");
                System.out.println();
                //Prevents infinite loop:
                scanner.next();
            }
        }
    }
}
