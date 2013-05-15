package crypt.actor;

import crypt.Game;

public class Bomb extends Actor implements DestroyableActor {

  private boolean isFalling = false;

  public Bomb(Point position, Game game) {
    super('b', position, game);
  }

  @Override
  public void update() {
    Actor target = game.getActorAtDelta(this, Point.DOWN);
    boolean hasMoved = game.collide(this, target);

    if (isFalling && !hasMoved)
    {
      game.destroyActorsInRectangularArea(getPosition(), 1);
    }

    if (hasMoved) {
      isFalling = true;
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

  @Override
  public void destroy() {
    game.destroyActorsInRectangularArea(getPosition(), 1);
  }
}