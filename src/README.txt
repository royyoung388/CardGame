# Structure
Main.java. Start the game.
Game.java. Entrance for the game. It contains BlackJack Game, and TE Game.

CardGame.java. Abstract Class, a base class, contains the general variables, and defined several general methods and abstract methods. It Represents general card game, a typical procedure: start -> new round -> is round end -> end round -> is game end -> end.
BlackJackGame.java. BlackJack game, inherits the CardGame class. Implements all the abstract method.
TEGame.java. TE game, inherits the BoardGame class. Implements all the abstract method.

Card.java. Represents one card, has rank and suit.
Deck.java. It includes the draw deck and discard deck. When draw deck is empty, the discard deck will reshuffle into draw deck automatically.

Hand.java. Abstract class, represents one hand. It has a card list, status and bet for this hand.
BJHand.java. Extends from Hand.java. Has method to judge BlackJack, and split this hand.
TEHand.java. Extends from Hand.java. According to the rule, hand may hit, stand. This class has no extra feature.

Player.java. An abstract class. It has attribute of hand list, name, money and other general method.
BJPlayer.java. BlackJack Player, extends Player. BJPlayer may split or double the hand, then the bet and money will also change.
BJDealer.java. BlackJack game dealer. Extends from BJPlayer, because the dealer is similar to BJPlayer. When show hand, only show the first card.
TEPlayer.java. Extends from Player. TEPlayer may stand or hit the hand. Beside it also can transfer to banker.
TEBanker.java. Banker in TE game. It extends from Player rather than TEPlayer. Because it's only a decorator, not an actual player.

CardTools.java. Some tools for card game. E.g. get the face of a card, get the all possible value for a card list.
Utils.java. Utils about checking input to simply the error handling.

# Robust
Every input will be checked. If you input the wrong information, there will be an error, and asking for a new input.

# Scalability And Extensibility
Scalability:
For TE game, it can decide the number of players.
For other card game with different cards, initializing the deck with different card list.
If one player has more hands, just creating new hand and add to player's hand list.

Extensibility:
Extends the CardGame.java, and implements all the general producer will create a new card game.
Extends the Player.java and implements necessary methods, then you can get a new player with different behavior.

# instruction
1. You should choose which card game you want to play.

2.1. If you choose the BlackJack Game, which only have one player with one dealer.
Then, you should follow the prompt to do actions.
After every round, the game will judge if the game is end.
You can also choose to end the game.

2.2. If you choose the TE Game, you need to input the number of players.
Then every player should follow the prompt to do actions.
After every round, the game will judge if the game is end.
Every player can also choose to end the game.