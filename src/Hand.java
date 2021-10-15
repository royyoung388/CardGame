import java.util.List;

public abstract class Hand {
    protected List<Card> cards;
    protected STATUS status = STATUS.NORMAL;
    protected int bet;

    enum STATUS {STAND, BUST, NORMAL, FOLD}

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

    public boolean isNormal() {
        return status == STATUS.NORMAL;
    }

    public void fold() {
        status = STATUS.FOLD;
    }

    public boolean isFold() {
        return status == STATUS.FOLD;
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

    public abstract int getMaxValue();

    public abstract boolean isBust();
}
