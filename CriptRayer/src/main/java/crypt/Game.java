package crypt;

import crypt.input.Input;
import crypt.input.NonBlockingInput;

public class Game {

  // public static final Input input = new ScannerInput();
  public static final Input input = new NonBlockingInput();
  public static final Board board = new Board();

  public Board getBoard() {
    return board;
  }

  public void run() throws InterruptedException
  {
    board.draw();
    while (true)
    {
      input.update();
      board.update();
      board.draw();

      // Aguardar 100ms
      Thread.sleep(100);
    }

  }

}