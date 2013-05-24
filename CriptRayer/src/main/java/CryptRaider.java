import javax.swing.JOptionPane;

import crypt.Board;
import crypt.Game;
import crypt.boardloader.OriginalGameBoardLoader;
import crypt.input.Input;
import crypt.view.GUIGameView;

public class CryptRaider {

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("======== CryptRaider ========");
    Game game = new Game();

    GUIGameView gameView = addGameView(game);
    addGameView(game);
    // addGameView(game);
    // addGameView(game);

    Input gameInput = gameView;
    game.setInput(gameInput);

    int level = 4;
    Board board = new OriginalGameBoardLoader(level).load(game);
    game.setLevel(board, level);

    // Ask user name
    String username = JOptionPane.showInputDialog("What is your name?");
    game.setUsername(username);

    game.run();

  }

  private static GUIGameView addGameView(Game game) {
    GUIGameView gameView = new GUIGameView(game);
    game.addGameView(gameView);
    return gameView;
  }

}