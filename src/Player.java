import java.util.List;

public abstract class Player {
    protected List<Hand> hands;
    protected int money;

    public void stake(Hand hand, int bet) {
        money -= bet;
        hand.setBet(bet);
    }

    public void win(Hand hand) {
        money += 2 * hand.getBet();
    }

    public List<Hand> getHands() {
        return hands;
    }

    public int getMoney() {
        return money;
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public void showMoney() {
        System.out.println("Your money amount: " + money);
    }

    public abstract void showHand(Hand hand);

    public abstract void reveal(Hand hand);
}
