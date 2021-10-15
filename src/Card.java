/***
 * One card, has suit and rank.
 */
public class Card {
    enum Suit {SPADE, HEART, DIAMOND, CLUB, JOKER}

    private final String rank;
    private final Suit suit;

    public Card(Suit suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        switch (suit) {
            case SPADE:
                return "\u2660" + rank;
            case CLUB:
                return "\u2663" + rank;
            case HEART:
                return "\u2665" + rank;
            case DIAMOND:
                return "\u2666" + rank;
            case JOKER:
                return "\uD83C\uDCCF";
        }
        return "no card";
    }
}
