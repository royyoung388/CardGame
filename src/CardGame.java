public abstract class CardGame {
    public abstract boolean isEnd();

    public abstract Player[] winner();

    public abstract int getFaceValue(String rank);
}
