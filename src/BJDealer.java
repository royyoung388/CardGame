import java.util.ArrayList;

public class BJDealer extends BJPlayer {

    public BJDealer() {
        super(0);
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
