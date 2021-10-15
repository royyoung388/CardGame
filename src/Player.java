import java.util.List;

public abstract class Player {
    protected List<Hand> hands;
    protected int money;
    protected String name;

    public void stake(Hand hand, int bet) {
        loseMoney(bet);
        hand.setBet(bet);
    }

    public void win(Hand hand) {
        System.out.println("Win for hand " + (hands.indexOf(hand) + 1));
        winMoney(2 * hand.getBet());
    }

    public void tie(Hand hand) {
        System.out.println("Tie for hand " + (hands.indexOf(hand) + 1));
        winMoney(hand.getBet());
    }

    public void lose(Hand hand) {
        System.out.println("Lose for hand " + (hands.indexOf(hand) + 1));
    }

    public void winMoney(int amount) {
        money += amount;
    }

    public void loseMoney(int amoumt) {
        money -= amoumt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
