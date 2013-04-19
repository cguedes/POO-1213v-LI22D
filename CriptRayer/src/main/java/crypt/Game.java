package crypt;

import java.text.MessageFormat;

import crypt.actor.Actor;
import crypt.actor.Point;
import crypt.input.Input;
import crypt.input.NonBlockingInput;
import crypt.view.GameView;

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
    while (true)
    {
      input.update();
      board.update();
      drawGameViews();

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

  private final GameView[] gameViews = new GameView[2];
  private int totalGameViews = 0;

  public void addGameView(GameView gameView) {
    if (totalGameViews > gameViews.length) {
      throw new IllegalStateException(MessageFormat.format("Cannot add more that {0} game views.", gameViews.length));
    }
    if (gameView == null) {
      throw new IllegalArgumentException("gameView");
    }

    gameViews[totalGameViews++] = gameView;
  }

  private void drawGameViews() {
    for (GameView gameView : gameViews) {
      if (gameView != null) {
        gameView.draw();
      }
    }
  }

  int points = 0;

  public int getPoints() {
    return points;
  }

  public void addPoints(int pointsToAdd) {
    this.points += pointsToAdd;
  }

}