package Checkers;

public class CpuMove {
    
    public static void cpuTurn() throws InterruptedException { 
        //Step 1: Set enemy:
        MoveInput.setPieceColor();

        //Step 2: Print piece count:
        MoveInput.printPieceCount("                      CPU piece count = ");

        //Step 2: Pick unit, type, and make move:
        MoveInput.executeCpuMove();

        //Step 3: Print move info:
        MoveInput.printCpuMoveInfo();
    }
}
