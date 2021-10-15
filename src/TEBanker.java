public class TEBanker extends Player {
    private TEPlayer player;

    public TEBanker(TEPlayer player) {
        this.player = player;
    }

    public TEHand hand() {
        return player.hand();
    }

    public TEPlayer getPlayer() {
        return player;
    }

    public void clear() {
        player = null;
    }

    @Override
    public void winMoney(int amount) {
        player.winMoney(amount);
    }

    @Override
    public void loseMoney(int amoumt) {
        player.loseMoney(amoumt);
    }

    @Override
    public int getMoney() {
        return player.getMoney();
    }

    @Override
    public void showMoney() {
        System.out.println("Banker's money amount: " + player.getMoney());
    }

    @Override
    public void showHand(Hand hand) {
        boolean first = true;
        for (Card card : player.hand().getCards()) {
            if (first) {
                System.out.print(card + "  ");
                first = false;
            } else {
                System.out.print("XX" + "  ");
            }
        }
        System.out.println();
    }

    @Override
    public void reveal(Hand hand) {
        for (Card c : hand.getCards())
            System.out.print(c + "  ");
        System.out.println();
    }
}
