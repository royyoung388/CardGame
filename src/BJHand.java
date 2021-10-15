import java.util.ArrayList;

/***
 * BlackJack game hand
 * According to the rule, hand may hit, split, stand, double up.
 * Natural BlackJack is always the biggest.
 */
public class BJHand extends Hand {

    public BJHand() {
        cards = new ArrayList<>(16);
    }

    public Hand split() {
        Hand newHand = new BJHand();
        newHand.add(cards.remove(0));
        newHand.setBet(getBet());
        return newHand;
    }

    public boolean isBlackJack() {
        return getMaxValue() == BlackJackGame.MAX;
    }

    public boolean isNaturalBJ() {
        return isBlackJack() && cards.size() == 2 &&
                (cards.get(0).getRank().equals("A") || cards.get(1).getRank().equals("A"));
    }

    public boolean canSplit() {
        return isNormal() && cards.size() == 2 && cards.get(0).getRank().equals(cards.get(1).getRank());
    }

    public void doubleBet() {
        setBet(getBet() * 2);
    }

    @Override
    public int getMaxValue() {
        int[] values = CardTools.getBJSum(cards);
        int max = 0;
        for (int v : values)
            if (v <= BlackJackGame.MAX)
                max = v;
            else
                break;
        if (max == 0)
            max = values[0];
        return max;
    }

    @Override
    public boolean isBust() {
        return getMaxValue() > BlackJackGame.MAX;
    }

    public static void main(String[] args) {
        BJHand hand = new BJHand();
        hand.add(new Card(Card.Suit.CLUB, "A"));
        hand.add(new Card(Card.Suit.CLUB, "3"));
        hand.add(new Card(Card.Suit.CLUB, "9"));
        hand.add(new Card(Card.Suit.CLUB, "10"));
        System.out.println(hand.getMaxValue());
    }
}
