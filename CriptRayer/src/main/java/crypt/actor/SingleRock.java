package crypt.actor;

import crypt.Game;

public class SingleRock extends Actor {
  public SingleRock(Point position, Game game) {
    super('@', position, game);
  }

  @Override
  public void update() {

    Actor target = game.getActorAtDelta(this, Point.DOWN);
    game.collide(this, target);

    super.update();
  }

  @Override
  public boolean collide(Actor other) {

    if (other instanceof Carter) {
      Actor next = game.getNextActorInSameDirection(other, this);
      return game.collide(this, next);
    }

    return super.collide(other);
  }

}