import java.util.Scanner;

public class BlackJackGame extends CardGame {
    private Deck deck;
    private BJPlayer player;
    private BJDealer dealer;
    private Scanner scanner;

    public BlackJackGame() {
        deck = new Deck();
        dealer = new BJDealer();
        player = new BJPlayer();
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Player[] winner() {
        return new Player[0];
    }

    @Override
    public int getFaceValue(String rank) {
        return CardTools.getFaceValue(rank);
    }

    public void round() {

    }

    public void start() {
        System.out.println("BlackJack Game start");

        dealer.getHand().add(deck.draw());
        player.firstHand().add(deck.draw());
        dealer.getHand().add(deck.draw());
        player.firstHand().add(deck.draw());

        player.showMoney();
        int bet = 0;
        do {
            System.out.println("Input your bet:");
            // todo input string
            bet = scanner.nextInt();
        } while (!Utils.checkRange(player.getMoney(), bet));
        player.stake(player.firstHand(), bet);

        System.out.println("After Deal card:");
        System.out.println("Dealer's hand:");
        dealer.showHand(dealer.getHand());
        System.out.println("Your hand:");
        player.showHand(player.firstHand());

        // todo check input
        if (player.firstHand().canSplit() && player.firstHand().getBet() < player.getMoney()) {
            System.out.println("Do you want to split your hand? (Y/N)");
            String input = scanner.nextLine();
            if (input.equals("Y")) {
                player.addHand(player.firstHand().split());
            }
        }

        // todo two hand, hand stand?
        int choice = 0;
        do {
            System.out.println("Please input the action: ");
            System.out.println("1.Hit;  2.Stand;  3.Double Up");
            choice = scanner.nextInt();
        } while (!Utils.checkRange(choice, 3));

        switch (choice) {
            case 1:
                player.firstHand().hit(deck.draw());
                break;
            case 2:
                player.firstHand().stand();
                break;
            case 3:
                if (player.firstHand().getBet() > player.getMoney()) {
                    System.out.println("You don't have enough money to double up");
                    continue;
                }
                player.doubleUp(player.firstHand());
                break;
        }
    }
}
