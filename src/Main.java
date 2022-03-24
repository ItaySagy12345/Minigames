import java.util.Scanner;
import Checkers.PlayCheckers;
import TicTacToe.PlayTicTacToe;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int gameChoice;
    private static int points;
    private static int winCount = 0;
    private static int lossCount = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            printMainMenu();
            while (true) {
                if (scanner.hasNextInt()) {
                    gameChoice = scanner.nextInt();
                    if (gameChoice == 1) {
                        points = PlayTicTacToe.playGame();
                        break;
                    } else if (gameChoice == 2) {
                        points = PlayCheckers.playGame();
                        break;
                    } else if (gameChoice == 3) {
                        System.exit(0);
                    } else {
                        System.out.println("Enter a valid game number:");
                    }
                } else {
                    System.out.println("Enter a valid integer value.");
                    scanner.next();
                }
            }
            updateTotalWinLossCount();
        }
    }

    public static void printMainMenu() throws InterruptedException {
        System.out.println("        >>Main Menu<<         Wins: " + winCount);
        Thread.sleep(150);
        System.out.println("----------------------------- Losses: " + lossCount);
        Thread.sleep(150);
        System.out.println("         Pick a Game:");
        Thread.sleep(150);
        System.out.println("       (1) Tic-Tac-Toe");
        Thread.sleep(150);
        System.out.println("       (2) Checkers");
        Thread.sleep(150);
        System.out.println("       (3) Quit");
        Thread.sleep(150);
        System.out.print("Choice: ");
    }

    public static void updateTotalWinLossCount() {
        if (points > 0)
            winCount += points;
        else
            lossCount += points * (-1);
    }
}