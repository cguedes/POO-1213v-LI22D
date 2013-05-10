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

    int level = 3;
    Board board = new OriginalGameBoardLoader(level).load(game);
    game.setLevel(board, level);

    GUIGameView gameView = new GUIGameView(game);
    game.addGameView(gameView);

    Input gameInput = gameView;
    game.setInput(gameInput);

    game.run();

  }

}