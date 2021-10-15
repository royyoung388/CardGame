import java.util.Scanner;

public class Game {
    private CardGame game;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Game Start");

        while (true) {
            System.out.println("Please choose the game:");
            System.out.println("1. BlackJack Game");
            System.out.println("2. TE Game");
            System.out.println("3. Quit");

            int choice;
            do {
                System.out.println("Please input the number:");
                choice = Utils.nextInt(scanner);
            } while (!Utils.checkIntRange(choice, 3));

            switch (choice) {
                case 1:
                    game = new BlackJackGame();
                    game.start();
                    break;
                case 2:
                    game = new TEGame();
                    game.start();
                    break;
                case 3:
                    return;
            }
        }
    }
}
