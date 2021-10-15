import java.util.Scanner;

/***
 * BlackJack Game, extends from CardGame.
 * The procedure obeys the general card game.
 * The player should follow the instruction to play the game.
 */
public class BlackJackGame extends CardGame {
    private Deck deck;
    private BJPlayer player;
    private BJDealer dealer;
    private Scanner scanner;
    public static final int MAX = 21;

    public BlackJackGame() {
        deck = new Deck();
        deck.init52();
        deck.shuffle();
        dealer = new BJDealer();
        player = new BJPlayer(200);
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean isRoundEnd() {
        for (int i = 0; i < player.getHands().size(); i++) {
            Hand hand = player.getHands().get(i);
            if (hand.isNormal())
                return false;
        }
        return true;
    }

    @Override
    public boolean isGameEnd() {
        // end when player has no money
        if (player.getMoney() <= 0)
            return true;

        // chose to continue or not
        String choice;
        do {
            System.out.println("Do you want to continue the game? (Y/N)");
            choice = scanner.next().strip().toUpperCase();
            if (choice.equals("N"))
                return true;
        } while (!Utils.checkChar(choice, "YN"));

        return false;
    }

    @Override
    public void start() {
        System.out.println("BlackJack Game Start");
        do {
            newRound();
            endRound();
        } while (!isGameEnd());
        end();
    }

    @Override
    public void end() {
        System.out.println("BlackJack Game is end");
    }

    @Override
    public void newRound() {
        System.out.println();
        // to stake
        player.showMoney();
        int bet;
        do {
            System.out.println("Input your bet:");
            bet = Utils.nextInt(scanner);
        } while (!Utils.checkIntRange(bet, player.getMoney()));
        player.stake(player.firstHand(), bet);

        // deal card
        dealer.firstHand().add(deck.draw());
        player.firstHand().add(deck.draw());
        dealer.firstHand().add(deck.draw());
        player.firstHand().add(deck.draw());

        // show card
        System.out.println("After Deal card:");
        System.out.println("Dealer's hand:");
        dealer.showHand(dealer.firstHand());
        System.out.println("Your hand 1:");
        player.showHand(player.firstHand());

        // if dealer is BlackJack
        if (dealer.firstHand().isBlackJack()) {
            System.out.println("Dealer is BlackJack");
            player.firstHand().stand();
        }

        // if player is BlackJack
        if (player.firstHand().isBlackJack()) {
            System.out.println("You are BlackJack");
            player.firstHand().stand();
        }

        // split the hand
        if (player.canSplit()) {
            String choice;
            do {
                System.out.println("Do you want to split your hand? (Y/N)");
                choice = scanner.next().strip().toUpperCase();
                if (choice.equals("Y")) {
                    // split hand and draw
                    player.split();
                    player.firstHand().hit(deck.draw());
                    player.secondHand().hit(deck.draw());

                    System.out.println("Your hand 1:");
                    player.showHand(player.firstHand());
                    System.out.println("Your hand 2:");
                    player.showHand(player.secondHand());
                }
            } while (!Utils.checkChar(choice, "YN"));
        }

        // loop for action
        while (!isRoundEnd()) {
            // for two hand
            for (int i = 0; i < player.getHands().size(); i++) {
                BJHand hand = player.getBJHands(i);
                if (!hand.isNormal())
                    continue;

                // choose the action
                int choice;
                do {
                    System.out.printf("\nPlease input the action for hand %d:\n", i + 1);
                    System.out.println("1.Hit;  2.Stand;  3.Double Up");
                    choice = Utils.nextInt(scanner);
                } while (!Utils.checkIntRange(choice, 3));

                // do action
                switch (choice) {
                    case 1:
                        hand.hit(deck.draw());
                        System.out.println("After hit:");
                        player.showHand(hand);
                        break;
                    case 2:
                        hand.stand();
                        break;
                    case 3:
                        if (hand.getBet() > player.getMoney()) {
                            System.out.println("You don't have enough money to double up");
                            continue;
                        }
                        player.doubleUp(hand);
                        hand.hit(deck.draw());
                        hand.stand();
                        System.out.println("After double up:");
                        player.showHand(hand);
                        break;
                }

                // if bust
                if (hand.isBust())
                    System.out.println("Your hand is bust!");

            }
        }

        // deal for dealer
        System.out.println("\nDealer's hand: ");
        dealer.reveal(dealer.firstHand());
        while (dealer.firstHand().getMaxValue() < 17) {
            dealer.firstHand().hit(deck.draw());
            System.out.println("After Deal: ");
            dealer.reveal(dealer.firstHand());
        }
    }

    @Override
    public void endRound() {
        System.out.println();
        int dealerMax = dealer.firstHand().getMaxValue();
        System.out.println("Max value for Dear: " + dealerMax);

        // win or lose for each hand
        for (int i = 0; i < player.getHands().size(); i++) {
            BJHand hand = player.getBJHands(i);
            int playerMax = hand.getMaxValue();
            System.out.printf("Max value for your hand %d: %d\n", i + 1, playerMax);
            // if player bust, player lose
            if (hand.isBust()) {
                player.lose(hand);
            } else if (dealer.firstHand().isBust() || dealerMax < playerMax) {
                // if dealer bust, player win
                // if player larger than dealer, player win
                player.win(hand);
            } else {
                // otherwise, player lose
                player.lose(hand);
            }
        }

        // discard card and clear hand
        for (Hand hand : player.getHands()) {
            deck.discard(hand.getCards());
            hand.clear();
        }
        deck.discard(dealer.firstHand().getCards());
        dealer.firstHand().clear();
    }

    @Override
    public int getFaceValue(String rank) {
        return CardTools.getFaceValue(rank);
    }
}
