package crypt.actor;

import crypt.Game;

public class Bomb extends Actor implements DestroyableActor {

  public Bomb(Point position, Game game) {
    super('b', position, game);
  }

  @Override
  public void update() {
    Actor target = game.getActorAtDelta(this, Point.DOWN);
    game.collide(this, target);

    if (target instanceof Rock) {
      game.destroyActorsInRectangularArea(getPosition(), 1);
    }

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