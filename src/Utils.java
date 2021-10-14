public class Utils {
    /**
     *
     * @param input
     * @param maxRange
     * @return false for invalid
     */
    public static boolean checkRange(int input, int maxRange) {
        if (input < 1 || input > maxRange) {
            System.out.println("The input is invalid");
            return false;
        }
        return true;
    }
}
