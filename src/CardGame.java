public abstract class CardGame {
    public abstract boolean isRoundEnd();

    public abstract boolean isGameEnd();

    public abstract void start();

    public abstract void end();

    public abstract void newRound();

    public abstract void endRound();

    public abstract int getFaceValue(String rank);
}
