package crypt.actor;

import crypt.Game;

public class Dirt extends Actor {
  private static final int POINTS_FOR_REMOVE = 5;

  public Dirt(Point position, Game game) {
    super('*', position, game);
  }

  @Override
  public boolean collide(Actor other) {
    if (other instanceof Carter) {
      game.addPoints(POINTS_FOR_REMOVE);
      return true;
    }

    return super.collide(other);
  }
}