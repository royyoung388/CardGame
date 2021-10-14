public class Card {
    enum Color {SPADE, HEART, DIAMOND, CLUB}

    private final String rank;
    private final Color color;

    public Card(Color color, String rank) {
        this.color = color;
        this.rank = rank;
    }

    public Color getColor() {
        return color;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        switch (color) {
            case SPADE:
                return "\u2660" + rank;
            case CLUB:
                return "\u2663" + rank;
            case HEART:
                return "\u2665" + rank;
            case DIAMOND:
                return "\u2666" + rank;
        }
        return "no card";
    }
}
