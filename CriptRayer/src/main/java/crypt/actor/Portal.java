package crypt.actor;

import crypt.Game;

class Portal extends Actor {
  public Portal(Point position, Game game) {
    super('U', position, game);
  }

  @Override
  public boolean collide(Actor other) {
    if (other instanceof Artifact) {
      game.removeArtifact((Artifact) other);
      return false;
    }
    if (other instanceof Carter) {
      game.removeCarter((Carter) other);
      return false;
    }
    return super.collide(other);
  }
}