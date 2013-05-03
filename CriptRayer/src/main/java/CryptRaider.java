import crypt.Board;
import crypt.Game;
import crypt.boardloader.BoardLoader;
import crypt.boardloader.OriginalGameBoardLoader;
import crypt.view.ConsoleGameView;
import crypt.view.GUIGameView;

public class CryptRaider {

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("======== CryptRaider ========");
    Game game = new Game();

    BoardLoader boardLoader = getBoardLoader();
    Board board = boardLoader.load(game);
    game.setLevel(board, 1);

    game.addGameView(new GUIGameView(game));
    game.addGameView(new ConsoleGameView(game));

    game.run();

  }

  private static BoardLoader getBoardLoader() {
    // return new FixedStringBoardLoader();
    return new OriginalGameBoardLoader(1);
  }

}