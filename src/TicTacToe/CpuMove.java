package TicTacToe;

public class CpuMove {

    public static void cpuTurn() {
        PlayTicTacToe.position = TicTacToeRandom.diceRoll(1, 9);
        while (PlayTicTacToe.occupiedPositionsArray[PlayTicTacToe.position-1] == 1) {
            PlayTicTacToe.position = TicTacToeRandom.diceRoll(1, 9);
        }
        System.out.println("                    CPU took square " + PlayTicTacToe.position);
    }
}
