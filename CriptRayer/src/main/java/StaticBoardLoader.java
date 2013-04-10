import crypt.Game;
import crypt.actor.Point;

public class StaticBoardLoader {

  // TODO: O código em baixo deve ser movido para
  // um tipo que implemente um "BoardLoader"

  public static void loadBoard(Game game) {
    addBoardRow(game, "#######################");
    addBoardRow(game, "#       ***   o       #");
    addBoardRow(game, "#  %    ###  ### ### :#");
    addBoardRow(game, "#  %    # #  # # # #  #");
    addBoardRow(game, "#  %%   ###  # # # #  #");
    addBoardRow(game, "#  %    #    ### ###  #");
    addBoardRow(game, "#     R o   o         #");
    addBoardRow(game, "#######################");
  }

  private static int boardRow = 0;

  // usage: addBoardRow("#  %    # #  # # # #  #");
  private static void addBoardRow(Game game, String row) {
    Point actorPosition = new Point(0, boardRow);
    for (int i = 0; i < row.length(); ++i) {
      char symbol = row.charAt(i);
      actorPosition.set(i, boardRow);
      game.getBoard().addActor(symbol, actorPosition, game);
    }
    ++boardRow;
  }

}