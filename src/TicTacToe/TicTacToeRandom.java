package TicTacToe;

import java.util.concurrent.ThreadLocalRandom;

public class TicTacToeRandom {

    public static int coinToss(int min, int max) {
        max = max + 1;
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int diceRoll(int min, int max) {
        max = max + 1;
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
