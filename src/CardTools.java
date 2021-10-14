import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardTools {
    private static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker"};
    private static final int[] values = {-1, 2, 3, 4, 5, 6, 7, 9, 10, 10, 10, 10};

    /**
     * @param rank
     * @return face value of each card. -1 for Ace.
     */
    public static int getFaceValue(String rank) {
        for (int i = 0; i < ranks.length; i++)
            if (rank.equals(ranks[i]))
                return values[i];
        throw new IllegalArgumentException("Unknown rank value");
    }

    public static Integer[] getBJSum(List<Card> cards) {
        int sum = 0;
        int count = 0;

        for (Card c : cards) {
            int value = getFaceValue(c.getRank());
            if (value != -1)
                sum += value;
            else
                count++;
        }

        // all possible sum for Ace
        int[] ace = new int[count];
        Integer[] values = new Integer[(int) Math.pow(2, count)];
        Arrays.fill(values, sum);

        int flag = 0;
        while (flag != Math.pow(2, count) - 1) {
            int bit = 1;
            for (int i = 0; i < count; i++) {
                if ((flag & bit) != 0)
                    ace[i] = 1;
                else
                    ace[i] = 11;
                bit <<= 1;
            }
            for (int i : ace)
                values[flag] += i;
            flag += 1;
        }
        Arrays.sort(values, Collections.reverseOrder());
        return values;
    }
}
