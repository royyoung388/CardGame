import java.util.ArrayList;

public class BJHand extends Hand {

    public BJHand() {
        cards = new ArrayList<>(16);
    }

    public Hand split() {
        Hand newHand = new BJHand();
        newHand.add(cards.remove(0));
        return newHand;
    }

    public boolean isBlackJack() {
        return getMaxValue() == 21;
    }

    public boolean isNaturalBJ() {
        return isBlackJack() && cards.size() == 2 &&
                (cards.get(0).getRank().equals("A") || cards.get(1).getRank().equals("A"));
    }

    public boolean canSplit() {
        return cards.size() == 2 && cards.get(0).getRank().equals(cards.get(1).getRank());
    }

    public void doubleBet() {
        setBet(getBet() * 2);
    }

    public int getMaxValue() {
        Integer[] values = CardTools.getBJSum(cards);
        int max = 0;
        for (int v : values)
            if (v <= 21) {
                max = v;
            }
        if (max == 0)
            max = values[values.length - 1];
        return max;
    }

    @Override
    boolean isBust() {
        return getMaxValue() > 21;
    }
}
