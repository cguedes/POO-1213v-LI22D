package crypt.boardloader;

import crypt.Board;
import crypt.actor.Point;

public abstract class StringBasedBoardLoader implements BoardLoader {

  private int boardRow = 0;

  // usage: addBoardRow("#  %    # #  # # # #  #");
  protected void addBoardRow(Board board, String row) {
    Point actorPosition = new Point(0, boardRow);
    for (int i = 0; i < row.length(); ++i) {
      char symbol = row.charAt(i);
      actorPosition.set(i, boardRow);
      board.addActor(symbol, actorPosition);
    }
    ++boardRow;
  }

}
