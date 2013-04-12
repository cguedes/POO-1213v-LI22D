package crypt.actor;

import crypt.Game;

public class Empty extends Actor {

  public Empty(Point position, Game game) {
    super(' ', position, game);
  }

  @Override
  public boolean collide(Actor other) {
    return true;
  }

}
