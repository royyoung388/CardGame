# Structure
Rule.java. Interface, defined 3 common rule, Is the boardGame end? Who is the winner? Is the move valid?
BoardGame.java. Abstract Class, a base class, implements the Rule interface. Defined several general methods and abstract methods.

TTTGame.java. Tic-tac-toe boardGame, inherits the BoardGame class.
OACGame.java. Order and Chaos boardGame, inherits the BoardGame class.
CustomTTTGame.java. Custom tic-tac-toe boardGame, inherits the BoardGame class.

Utils.java. General methods to check the board with win condition.

Cell.java. One cell on the board, used to record the mark.
Board.java. Board, made by 2D cells list.

Player.java. Player, has a name.
Team.java. Team, has a symbol, every team consists of at least 1 player.

ScoreBoard.java. Scoreboard, record the score of every team.

Game.java. Control the 3 games. Decided to play which boardGame, and start a new boardGame or replay.
Main.java. Start the Game.


# Robust
Every input will be checked. If you input the wrong information, there will be an error, and asking for a new input.
For every move, the boardGame will check the validation.
For custom TTT boardGame, the relationship between team number and player number, board size and win condition will be checked. And you are not allowed to input the same team symbol.


# Scalability And Extensibility
Board scalability: Create different size of cell list will create different size of board.
Player Scalability: Shows in Team.java and Player.java. Multiply players can be added to same team. And every team will choose the next player to make a move.
Extensibility: Shows in the Rule.java interface, BoardGame.java base class. Creating a new class by inheriting the BoardGame makes extensibility possible.


# instruction
1. you should choose which boardGame you want to play.

2.1 If you chose the TTT BoardGame, the team and player will be defined as Team 1 with player O, Team 2 with player X, which is not changeable. And the board will be 3X3.
Then, you should follow the prompt to input row and column to make a move.
After every move, the boardGame will judge if the boardGame is end, or change the player.
At the end, the scoreboard will record the score of every team.

2.2 If you chose the OAC BoardGame, the team and player will be defined as Team 1 with player Order, Team 2 with player Chaos, which is not changeable. And the board will be 6X6.
Then, you should follow the prompt to input chess, row and column to make a move.
After every move, the boardGame will judge if the boardGame is end, or change the player.
At the end, the scoreboard will record the score of every team.

2.3 If you chose the Custom TTT BoardGame, you should input the board size, team number, team symbols, player number, win condition following the prompt.
The boardGame will automatically distribute the players and teams which will be shown.
Then, you should follow the prompt to input row, column to make a move.
After every move, the boardGame will judge if the boardGame is end, or change the team and player.
At the end, the scoreboard will record the score of every team.

3. After each boardGame, you can decide play again or not.
If you want to play again, the scoreboard will not be reset which means the score will be remembered.
If you want to play a new boardGame, the scoreboard will be reset, and you will be asked to choose a new game.