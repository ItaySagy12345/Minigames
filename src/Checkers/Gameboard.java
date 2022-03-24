package Checkers;

public class Gameboard {
    private final static String ONE_STAR = "*";
    private final static String THREE_STARS = "***";
    private final static String SINGLE_SPACE = " ";
    private final static String DOUBLE_SPACE = "  ";
    public final static int NORTH = -1; 
    public final static int SOUTH = 1; 
    public final static int EAST = 1; 
    public final static int WEST = -1; 
    public final static String EMPTY = "   ";
    public final static String WTE = "\u2588\u2588\u2588";
    
    private static String white = " O ";
    private static String black = " X ";
    private static int spacerCount;
    private static int row, column, rightBorderCount;

    public static String[][] gameboard = new String[8][8];

    public static void printRules() throws InterruptedException {
        System.out.println("                        --< Game Rules >--");
        Thread.sleep(500);
        System.out.println("                      Regular checkers rules");
        Thread.sleep(500);
        System.out.println("                    Enter 100 to exit the game");
        Thread.sleep(500);
        System.out.println("         An automatic coin toss will determine who goes first");
        Thread.sleep(500);
        System.out.println("                                3");
        Thread.sleep(300);
        System.out.println("                                2");
        Thread.sleep(300);
        System.out.println("                                1");
        Thread.sleep(300);
        System.out.println("                              Start!\n");
        Thread.sleep(300);
    }

    public static void initializeGameboard() {
        for (row=0; row<8; row++) {
            for (column=0; column<8; column++) {
                if (row < 3) {
                    if (row % 2 == 0 && column % 2 == 1 ||
                        row % 2 == 1 && column % 2 == 0) {
                        gameboard[row][column] = white;
                    }
                }
                else if (row > 4) {
                    if (row % 2 == 1 && column % 2 == 0 ||
                        row % 2 == 0 && column % 2 == 1) {
                        gameboard[row][column] = black; 
                    }
                }
                else {
                    gameboard[row][column] = EMPTY; 
                }
            }
        }
    }

    public static void printCpuGameboard() {
        System.out.println();
        printTopBorder();
        printBoard();
        printBottomBorder1();
        printBottomBorder2();
    }

    public static void printPlayerGameboard() {
        System.out.println("                            Your move:");
        System.out.println();
        printTopBorder();
        printBoard();
        printBottomBorder1();
        printBottomBorder2();
        System.out.println("             --------------------------------------");
    }

    private static void printTopBorder() {
        for (spacerCount=0; spacerCount<6; spacerCount++) {
            System.out.print(EMPTY);
        }
        System.out.print(DOUBLE_SPACE + ONE_STAR);
        for (row=0; row<8; row++) {
            System.out.print(THREE_STARS);
        }
        System.out.print(ONE_STAR);
        System.out.println();
    }

    private static void printBoard() {
        rightBorderCount=8;
        for (row=0; row<8; row++) {
            for (column=0; column<8; column++) {
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        gameboard[row][column] = WTE;
                    }
                }
                else if (row % 2 == 1) {
                    if (column % 2 == 1) {
                        gameboard[row][column] = WTE;
                    }
                }
                if (column == 0) {
                    for (spacerCount=0; spacerCount<6; spacerCount++) {
                        System.out.print(EMPTY);  
                    }
                    System.out.print(rightBorderCount + SINGLE_SPACE + ONE_STAR);
                }
                System.out.print(gameboard[row][column]);
                if (column == 7) {
                    System.out.print(ONE_STAR);
                }
            }
            System.out.println();
            rightBorderCount--;
        }
    }

    private static void printBottomBorder1() {
        for (spacerCount=0; spacerCount<6; spacerCount++) {
            System.out.print(EMPTY);
        }
        System.out.print(DOUBLE_SPACE + ONE_STAR);
        for (row=0; row<8; row++) {
            System.out.print(THREE_STARS);
        }
        System.out.print(ONE_STAR);
        System.out.println();
    }

    private static void printBottomBorder2() {
        for (spacerCount=0; spacerCount<6; spacerCount++) {
            System.out.print(DOUBLE_SPACE);
        }
        for (row=0; row<2; row++) {
            System.out.print(EMPTY); 
        }
        System.out.print(EMPTY);
        for (row=0; row<8; row++) {
            System.out.print(SINGLE_SPACE + (row+1) + SINGLE_SPACE);
        }
        System.out.println();
    }
}
