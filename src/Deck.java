import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;
    private List<Card> discard;

    public Deck() {
        discard = new ArrayList<>(52);

        Card.Color[] colors = {Card.Color.CLUB, Card.Color.SPADE, Card.Color.DIAMOND, Card.Color.HEART};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        deck = new ArrayList<>(52);
        for (Card.Color c : colors)
            for (String r : ranks)
                deck.add(new Card(c, r));
    }

    public Card draw() {
        if (deck.size() == 0)
            reshuffle();
        return deck.remove(0);
    }

    /**
     * shuffle the discard to deck
     */
    public void reshuffle() {
        deck.addAll(discard);
        discard.clear();
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void discard(List<Card> cards) {
        discard.addAll(cards);
    }
}
