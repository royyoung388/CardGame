import java.util.ArrayList;

public class BJPlayer extends Player {

    public BJPlayer() {
        hands = new ArrayList<>(1);
        hands.add(new BJHand());
    }

    public BJHand getBJHands(int index) {
        return (BJHand) hands.get(index);
    }

    public BJHand firstHand() {
        return getBJHands(0);
    }

    public BJHand secondHand() {
        if (hands.size() > 1)
            return getBJHands(1);
        throw null;
    }

    public void doubleUp(BJHand hand) {
        money -= hand.getBet();
        hand.doubleBet();
    }

    @Override
    public void showHand(Hand hand) {
        reveal(hand);
    }

    @Override
    public void reveal(Hand hand) {
        for (Card c : hand.getCards())
            System.out.print(c + "  ");
        System.out.println();
    }

}
