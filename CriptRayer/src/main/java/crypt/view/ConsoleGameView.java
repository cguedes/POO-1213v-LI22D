package crypt.view;

import crypt.Game;
import crypt.actor.Actor;
import crypt.actor.Point;

public class ConsoleGameView implements GameView {

  private final Game game;

  public ConsoleGameView(Game game) {
    this.game = game;
  }

  @Override
  public void draw() {
    Point position = new Point();
    int numRows = game.getBoard().getNumRows();
    int numCols = game.getBoard().getNumCols();
    System.out.println();
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < numCols; ++c) {
        position.set(c, r);
        Actor actor = game.getBoard().getActorAt(position);
        actor.draw();
      }
      System.out.println();
    }
  }
}
