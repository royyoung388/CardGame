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
//                return "\u2660" + rank;
                return "SPADE " + rank;
            case CLUB:
//                return "\u2663" + rank;
                return "CLUB " + rank;
            case HEART:
//                return "\u2665" + rank;
                return "HEART " + rank;
            case DIAMOND:
//                return "\u2666" + rank;
                return "DIAMOND " + rank;
            case JOKER:
//                return "\uD83C\uDCCF";
                return "JOKER " + rank;
        }
        return "no card";
    }
}
