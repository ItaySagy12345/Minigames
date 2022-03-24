package TicTacToe;

public class Gameboard {
    private static final String EMPTY = "   ";
    private static final String SINGLE_SPACE = " ";
    private static final String VERTICAL = "|";
    private static final String HORIZONTAL = "---";
    private static final String INTERSECTION = "+";

    private static int row, column;
    public static String[][] gameboard = new String[3][3];

    public static void printGameRules() throws InterruptedException {
        System.out.println("                   --< Game Rules >--");
        Thread.sleep(500);
        System.out.println("               Enter 100 to exit the game");
        Thread.sleep(500);
        System.out.println("        Make a move by entering a number from 1-9");
        Thread.sleep(500);
        System.out.println("    An automatic coin toss will determine who goes first");
        Thread.sleep(500);
        System.out.println("  There are 9 fields, numbered from 1-9, from left to right");
        Thread.sleep(500);
        System.out.println("                           3");
        Thread.sleep(300);
        System.out.println("                           2");
        Thread.sleep(300);
        System.out.println("                           1");
        Thread.sleep(300);
        System.out.println("                         Start!\n");
        Thread.sleep(300);
    } 

    public static void initializeGameboard() {
        for (row=0; row<3; row++) {
            for (column=0; column<3; column++) {
                gameboard[row][column] = EMPTY;
            }
        }
    }

    public static void printPlayerGameboard() {
        System.out.println();
        for (row=0; row<3; row++) {
            printRowSpacer(7);
            if (row % 2 == 1) {
                printMiddleRow();
                printRowSpacer(7);
            }
            for (column=0; column<3; column++) {
                if (column % 2 == 1) {
                    printVerticalLine();
                }
                System.out.print(gameboard[row][column]);
                if (column % 2 == 1) {
                    printVerticalLine();
                }
            }
            System.out.println();
            if (row % 2 == 1) {
                printRowSpacer(7);
                printMiddleRow();
            }
        }
        System.out.println();
    }

    public static void printCpuGameboard() {
        System.out.println();
        for (row=0; row<3; row++) {
            printRowSpacer(7);
            if (row % 2 == 1) {
                printMiddleRow();
                printRowSpacer(7);
            }
            for (column=0; column<3; column++) {
                if (column % 2 == 1) {
                    printVerticalLine();
                }
                System.out.print(gameboard[row][column]);
                if (column % 2 == 1) {
                    printVerticalLine();
                }
            }
            System.out.println();
            if (row % 2 == 1) {
                printRowSpacer(7);
                printMiddleRow();
            }
        }
        System.out.println();
        printRowSpacer(6);
        System.out.println(">>-------------<<");
    }

    public static void printRowSpacer(int spacersNeeded) {
        for (int spacerCount = 0; spacerCount < spacersNeeded; spacerCount++) {
            System.out.print(EMPTY);
        }
        System.out.print(SINGLE_SPACE);
    }

    public static void printVerticalLine() {
        System.out.print(VERTICAL);
    }

    public static void printMiddleRow() {
        System.out.print(HORIZONTAL);
        System.out.print(INTERSECTION);
        System.out.print(HORIZONTAL);
        System.out.print(INTERSECTION);
        System.out.print(HORIZONTAL);
        System.out.println();
    }
}
