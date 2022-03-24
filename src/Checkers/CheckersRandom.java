package Checkers;
import java.util.concurrent.ThreadLocalRandom;

public class CheckersRandom {
    
    public static int coinToss(int min, int max) {
        max = max + 1;
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int diceRoll(int min, int max) {
        max = max + 1;
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int getUniqueDiceRoll(int array[], int maxIndex) {
        while (true) {
            int index = diceRoll(0, maxIndex);
            if (array[index] == 0) {
                return index;
            }
        }
    }
}
