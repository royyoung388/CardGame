import java.util.*;

public class TEGame extends CardGame {
    private Deck deck;
    private List<TEPlayer> players;
    private TEBanker banker;
    private Scanner scanner;
    public static final int MAX = 31;

    public TEGame() {
        deck = new Deck();
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean isRoundEnd() {
        for (TEPlayer player : players) {
            if (player.hand().isNormal())
                return false;
        }
        return true;
    }

    @Override
    public boolean isGameEnd() {
        return players.size() < 1;
    }

    @Override
    public void start() {
        System.out.println("TE Game Start");
        System.out.println("Please input the player number: ");
        int num = Utils.nextInt(scanner);
        for (int i = 0; i < num; i++) {
            TEPlayer player = new TEPlayer(200);
            player.setName("Player " + (i + 1));
            players.add(player);
        }

        // set banker
        System.out.println("Player 1 is Banker");
        TEPlayer player = players.remove(0);
        player.winMoney(player.getMoney() * 2);
        banker = player.toBanker();

        do {
            newRound();
            endRound();
        }while (!isGameEnd());

        end();
    }

    @Override
    public void end() {
        System.out.println("TE game is end");
    }

    @Override
    public void newRound() {
        System.out.println();
        // deal card
        banker.hand().add(deck.draw());
        for (TEPlayer player : players) {
            player.hand().add(deck.draw());
        }

        // show card
        System.out.println("After Deal card:");
        System.out.println("Banker's hand:");
        banker.showHand(banker.hand());
        banker.showMoney();

        // to stake
        for (TEPlayer player : players) {
            System.out.println();
            System.out.println(player.getName() + "'s Turn:");
            player.reveal(player.hand());
            player.showMoney();
            int bet;
            do {
                System.out.println("Input your bet: (0 means fold)");
                bet = Utils.nextInt(scanner);
            } while (!Utils.checkIntRange(bet, 0, player.getMoney()));

            // fold
            if (bet == 0) {
                System.out.println("You fold");
                player.hand().fold();
            } else {
                player.stake(player.hand(), bet);
            }
        }

        // deal two more card
        banker.hand().add(deck.draw());
        banker.hand().add(deck.draw());
        for (TEPlayer player : players) {
            if (!player.hand().isNormal())
                continue;
            player.hand().add(deck.draw());
            player.hand().add(deck.draw());
        }

        // loop for action
        while (!isRoundEnd()) {
            // for every player
            for (TEPlayer player : players) {
                // do action util bust or stand
                while (player.hand().isNormal()) {
                    // show hand
                    System.out.println();
                    System.out.println(player.getName() + "'s Turn:");
                    System.out.println("Banker's Hand");
                    banker.showHand(banker.hand());
                    System.out.println("Your hand");
                    player.reveal(player.hand());

                    // choose the action
                    int choice;
                    do {
                        System.out.println("\nPlease input the action:");
                        System.out.println("1.Hit;  2.Stand");
                        choice = Utils.nextInt(scanner);
                    } while (!Utils.checkIntRange(choice, 2));

                    // do action
                    switch (choice) {
                        case 1:
                            player.hand().hit(deck.draw());
                            System.out.println("After hit:");
                            player.reveal(player.hand());
                            break;
                        case 2:
                            player.hand().stand();
                            break;
                    }

                    // if bust
                    if (player.hand().isBust())
                        System.out.println("Your hand is bust!");
                }
            }
        }

        // last deal for banker
        System.out.println("\nBanker's hand: ");
        banker.reveal(banker.hand());
        while (banker.hand().getMaxValue() < 27) {
            banker.hand().hit(deck.draw());
            System.out.println("After Deal: ");
            banker.reveal(banker.hand());
        }
    }

    @Override
    public void endRound() {
        System.out.println();
        int bankerMax = banker.hand().getMaxValue();
        System.out.println("Max value for Dear: " + bankerMax);

        // win or lose for each player
        for (TEPlayer player : players) {
            // skip fold
            if (player.hand().isFold())
                continue;

            int playerMax = player.hand().getMaxValue();
            System.out.printf("Max value for %s: %d\n", player.getName(), playerMax);
            // if player bust, player lose
            if (player.hand().isBust()) {
                player.lose(player.hand());
                banker.winMoney(player.hand().getBet());
            } else if (banker.hand().isBust() || bankerMax < playerMax) {
                // if dealer bust, player win
                // if player larger than dealer, player win
                player.win(player.hand());
                banker.loseMoney(player.hand().getBet());
            } else {
                // otherwise, player lose
                player.lose(player.hand());
                banker.winMoney(player.hand().getBet());
            }
            player.showMoney();
        }
        banker.showMoney();

        // discard card and clear hand
        for (TEPlayer player : players) {
            deck.discard(player.hand().getCards());
            player.hand().clear();
        }
        deck.discard(banker.hand().getCards());
        banker.hand().clear();

        // player with no money is out
        Iterator<TEPlayer> iterator = players.iterator();
        while (iterator.hasNext()) {
            TEPlayer player = iterator.next();
            if (player.getMoney() <= 0) {
                iterator.remove();
                System.out.println(player.getName() + " is out of the game for no money.");
            }
        }

        if (banker.getMoney() < 0) {
            System.out.println("Banker is out of the game for no money.");
            banker.clear();
            banker = null;
        }

        // Rotating the Banker
        // copy player list to sort
        List<TEPlayer> sortList = new ArrayList<>(players);
        sortList.sort(Collections.reverseOrder());
        do {
            iterator = sortList.iterator();
            while (iterator.hasNext()) {
                TEPlayer player = iterator.next();

                if (banker == null || player.getMoney() > banker.getMoney()) {
                    String choice;
                    do {
                        System.out.println(player.getName() + ", do you want to be banker? (Y/N)");
                        choice = scanner.next().strip().toUpperCase();
                    } while (!Utils.checkChar(choice, "YN"));
                    // Yes, to be a banker
                    if (choice.equals("Y")) {
                        // remove or replace the player in player list
                        if (banker != null)
                            players.set(players.indexOf(player), banker.getPlayer());
                        else
                            players.remove(player);
                        banker = player.toBanker();
                        iterator.remove();
                        break;
                    }
                }
            }
        } while (banker == null);

        // cash out?
        System.out.println();
        iterator = players.iterator();
        while (iterator.hasNext()) {
            TEPlayer player = iterator.next();

            String choice;
            do {
                System.out.println(player.getName() + ", do you want to continue the game? (Y/N)");
                choice = scanner.next().strip().toUpperCase();
                if (choice.equals("N")) {
                    iterator.remove();
                }
            } while (!Utils.checkChar(choice, "YN"));
        }
    }

    @Override
    public int getFaceValue(String rank) {
        return CardTools.getFaceValue(rank);
    }
}
