import java.util.List;

public abstract class Hand {
    protected List<Card> cards;
    protected STATUS status;
    protected int bet;

    private enum STATUS {STAND, BUST, NORMAL, WIN}

    public void hit(Card card) {
        add(card);
        if (isBust()) {
            bust();
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void stand() {
        status = STATUS.STAND;
    }

    public void bust() {
        status = STATUS.BUST;
    }

    public void normal() {
        status = STATUS.NORMAL;
    }

    public void win() {
        status = STATUS.WIN;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
        bet = 0;
        normal();
    }

    abstract boolean isBust();
}
