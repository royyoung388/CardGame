import java.util.ArrayList;

/***
 * TE Player, extends Player.
 * TEPlayer may stand or hit the hand. Beside it also can transfer to banker
 */
public class TEPlayer extends Player implements Comparable<TEPlayer> {
    public TEPlayer(int money) {
        this.money = money;
        hands = new ArrayList<>(1);
        hands.add(new TEHand());
    }

    public TEHand hand() {
        return (TEHand) getHands().get(0);
    }

    public TEBanker toBanker() {
        return new TEBanker(this);
    }

    @Override
    public void showHand(Hand hand) {
        System.out.print("XX  ");
        for (int i = 1; i < hand.getCards().size(); i++)
            System.out.print(hand.getCards().get(i) + "  ");
        System.out.println();
        System.out.println("bet: " + hand.getBet());
    }

    @Override
    public void reveal(Hand hand) {
        for (Card c : hand.getCards())
            System.out.print(c + "  ");
        System.out.println();
        System.out.println("bet: " + hand.getBet());
    }

    @Override
    public int compareTo(TEPlayer p) {
        return getMoney() - p.getMoney();
    }
}
