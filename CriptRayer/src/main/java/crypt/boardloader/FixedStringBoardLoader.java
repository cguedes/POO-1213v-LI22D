package crypt.boardloader;

import crypt.Game;
import crypt.actor.Point;

public class FixedStringBoardLoader implements BoardLoader {

  private int boardRow = 0;

  @Override
  public void load(Game game) {
    addBoardRow(game, "#######################");
    addBoardRow(game, "#       ***   o       #");
    addBoardRow(game, "#  %    ###  ### ### :#");
    addBoardRow(game, "#  %    # #  # # # #  #");
    addBoardRow(game, "#  %%   ###  # # # #  #");
    addBoardRow(game, "#  %    #    ### ###  #");
    addBoardRow(game, "#     R o   o         #");
    addBoardRow(game, "#######################");
  }

  // usage: addBoardRow("#  %    # #  # # # #  #");
  private void addBoardRow(Game game, String row) {
    Point actorPosition = new Point(0, boardRow);
    for (int i = 0; i < row.length(); ++i) {
      char symbol = row.charAt(i);
      actorPosition.set(i, boardRow);
      game.getBoard().addActor(symbol, actorPosition);
    }
    ++boardRow;
  }

}
