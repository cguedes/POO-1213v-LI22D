package crypt.boardloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import crypt.Board;
import crypt.Game;

public class OriginalGameBoardLoader extends StringBasedBoardLoader {

  private int level;

  public OriginalGameBoardLoader(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public Board load(Game game) {

    FileReader reader = null;
    try {
      reader = new FileReader("levels/level" + level + ".txt");
      BufferedReader buffReader = new BufferedReader(reader);
      return readBoardFrom(game, buffReader);
    } catch (FileNotFoundException ex)
    {
      throw new BoardLoadException("Cannot load level " + level, ex);
    } catch (IOException ex)
    {
      throw new BoardLoadException("Cannot parse level " + level, ex);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          throw new BoardLoadException("Cannot close level stream", e);
        }
      }
    }
  }

  private Board readBoardFrom(Game game, BufferedReader reader) throws IOException {

    String rowsAndCols = reader.readLine();

    Scanner scanner = new Scanner(rowsAndCols);
    int rows = scanner.nextInt();
    int cols = scanner.nextInt();

    Board board = new Board(game, rows, cols);
    for (int r = 0; r < rows; ++r) {
      String rowLine = reader.readLine();
      addBoardRow(board, rowLine);
    }
    return board;
  }

}