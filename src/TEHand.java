import java.util.ArrayList;

/***
 * TE game hand
 * According to the rule, hand may hit, stand.
 * This class has no extra feature.
 */
public class TEHand extends Hand {
    public TEHand() {
        cards = new ArrayList<>(16);
    }

    @Override
    public int getMaxValue() {
        int[] values = CardTools.getTESum(cards);
        int max = 0;
        for (int v : values)
            if (v <= TEGame.MAX)
                max = v;
            else
                break;
        if (max == 0)
            max = values[0];
        return max;
    }

    @Override
    public boolean isBust() {
        return getMaxValue() > TEGame.MAX;
    }

    public static void main(String[] args) {
        TEHand hand = new TEHand();
        hand.add(new Card(Card.Suit.CLUB, "A"));
        hand.add(new Card(Card.Suit.CLUB, "3"));
        hand.add(new Card(Card.Suit.CLUB, "9"));
        hand.add(new Card(Card.Suit.CLUB, "10"));
        System.out.println(hand.getMaxValue());
    }
}
