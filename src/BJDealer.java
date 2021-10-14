import java.util.ArrayList;

public class BJDealer extends Player {

    public BJDealer() {
        hands = new ArrayList<>(1);
        hands.add(new BJHand());
    }

    public Hand getHand() {
        return hands.get(0);
    }

    @Override
    public void showHand(Hand hand) {
        System.out.println(hand.getCards().get(0) + "  XX");
    }

    @Override
    public void reveal(Hand hand) {
        for (Card c : hand.getCards())
            System.out.print(c + "  ");
        System.out.println();
    }
}
