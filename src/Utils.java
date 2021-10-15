import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    /**
     * @param value
     * @param maxRange
     * @return false for invalid
     */
    public static boolean checkIntRange(int value, int maxRange) {
        return checkIntRange(value, 1, maxRange);
    }

    public static boolean checkIntRange(int value, int minRange, int maxRange) {
        if (value < minRange || value > maxRange) {
            System.out.println("Error: The input is invalid");
            return false;
        }
        return true;
    }

    public static boolean checkChar(String input, String range) {
        if (input.strip().length() > 1)
            return false;

        char ch = input.toUpperCase().charAt(0);
        if (range.indexOf(ch) == -1) {
            System.out.println("Error: The input is invalid");
            return false;
        }
        return true;
    }

    public static int nextInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Error: the input is invalid, please input again");
            }
        }
    }
}
