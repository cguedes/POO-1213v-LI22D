package crypt.actor;

import crypt.Game;

public class Dirt extends Actor {
  public Dirt(Point position, Game game) {
    super('*', position, game);
  }

  @Override
  public boolean collide(Actor other) {
    if (other instanceof Carter)
      return true;

    return super.collide(other);
  }
}