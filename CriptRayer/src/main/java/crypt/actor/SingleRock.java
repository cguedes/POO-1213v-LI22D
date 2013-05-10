package crypt.actor;

import crypt.Game;

public class SingleRock extends Actor {
  public SingleRock(Point position, Game game) {
    super('@', position, game);
  }

  @Override
  public boolean collide(Actor other) {

    if (other instanceof Carter) {
      Point dir = this.getPosition().copy().sub(other.getPosition());
      Point targetPosition = getPosition().copy().add(dir);
      Actor target = game.getBoard().getActorAt(targetPosition);
      return game.collide(this, target);
    }

    return super.collide(other);
  }

}