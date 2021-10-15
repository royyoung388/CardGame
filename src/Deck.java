import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * It includes the draw deck and discard Deck.
 * When draw deck is empty, the discard deck will reshuffle into draw deck automatically.
 */
public class Deck {
    protected List<Card> drawDeck;
    protected List<Card> discard;

    public Deck() {
        drawDeck = new ArrayList<>(54);
        discard = new ArrayList<>(54);
    }

    public Deck(List<Card> cards) {
        this();
        init(cards);
    }

    public void init(List<Card> cards) {
        drawDeck.addAll(cards);
    }

    public void init52() {
        Card.Suit[] suits = {Card.Suit.CLUB, Card.Suit.SPADE, Card.Suit.DIAMOND, Card.Suit.HEART};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (Card.Suit c : suits)
            for (String r : ranks)
                drawDeck.add(new Card(c, r));
    }

    public Card draw() {
        if (drawDeck.size() == 0)
            reshuffle();
        return drawDeck.remove(0);
    }

    /**
     * shuffle the discard to deck
     */
    public void reshuffle() {
        drawDeck.addAll(discard);
        discard.clear();
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(drawDeck);
    }

    public void discard(List<Card> cards) {
        discard.addAll(cards);
    }
}
