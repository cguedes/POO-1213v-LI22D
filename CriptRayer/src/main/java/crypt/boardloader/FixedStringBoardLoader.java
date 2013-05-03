package crypt.boardloader;

import crypt.Board;
import crypt.Game;

public class FixedStringBoardLoader extends StringBasedBoardLoader {

  @Override
  public Board load(Game game) {
    Board board = new Board(game, 8, 23);
    addBoardRow(board, "#######################");
    addBoardRow(board, "#       ***   o       #");
    addBoardRow(board, "#  %    ###  ### ###  #");
    addBoardRow(board, "#  %    # #  # # # #: #");
    addBoardRow(board, "#  %%   ###  # # # #  #");
    addBoardRow(board, "#  %    #    ### ###  #");
    addBoardRow(board, "#     R o   o         #");
    addBoardRow(board, "#######################");
    return board;
  }

}
