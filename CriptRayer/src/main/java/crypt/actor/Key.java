package crypt.actor;

import crypt.Game;

public class Key extends Actor {
  public Key(Point position, Game game) {
    super('k', position, game);
  }

  @Override
  public boolean collide(Actor other) {

    if (other instanceof Carter) {
      // Remove door
      game.openDoor(this);
      return true;
    }

    return super.collide(other);
  }
}