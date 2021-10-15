import java.util.ArrayList;

/***
 * BlackJack Player, extends Player.
 * BJPlayer may split or double the hand, then the bet and money will also change.
 */
public class BJPlayer extends Player {

    public BJPlayer(int money) {
        this.money = money;
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
        return null;
    }

    // can split the hand?
    public boolean canSplit() {
        return hands.size() == 1 && firstHand().canSplit() && firstHand().getBet() < getMoney();
    }

    // split the hand
    public void split() {
        addHand(firstHand().split());
        stake(secondHand(), firstHand().getBet());
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
        System.out.println("bet: " + hand.getBet());
    }

}
