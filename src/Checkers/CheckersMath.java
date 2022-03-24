package Checkers;
public class CheckersMath {

    public static int sumArrayAttempts(int array[], int maxIndex) {
        int sum = 0;
        for (int index=0; index<maxIndex+1; index++) {
            sum += array[index];
        }
        return sum;
    }

    public static void initializeArrayToZero(int array[], int maxIndex) {
        for (int index=0; index<maxIndex+1; index++) {
            array[index] = 0;
        }
    }
}
