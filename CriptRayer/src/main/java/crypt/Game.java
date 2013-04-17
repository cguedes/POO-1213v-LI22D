package crypt;

import crypt.actor.Actor;
import crypt.actor.Point;
import crypt.input.Input;
import crypt.input.NonBlockingInput;

public class Game {

  public final Input input = new NonBlockingInput();
  public final Board board = new Board(this);

  public Input getInput() {
    return input;
  }

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

  public boolean collide(Actor source, Actor target) {

    Point targetPosition = target.getPosition().copy();
    boolean hasCollided = target.collide(source);
    if (hasCollided) {
      board.moveActor(source, targetPosition);
    }
    return hasCollided;

  }

}