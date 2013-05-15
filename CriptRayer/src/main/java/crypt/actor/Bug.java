package crypt.actor;

import crypt.Game;

public class Bug extends Actor implements DestroyableActor {

  private int tick = 0;
  private static final int SPEED = 4;
  private static final Point[] directions = { Point.UP, Point.DOWN, Point.LEFT, Point.RIGHT };

  private Point currentDirection = Point.DOWN;

  public Bug(Point position, Game game) {
    super(':', position, game);
  }

  @Override
  public void update() {

    if (tick++ % SPEED != 0)
      return;

    Point nextPosition = getPosition().copy().add(currentDirection);
    Actor target = game.getBoard().getActorAt(nextPosition);
    boolean hasMoved = game.collide(this, target);
    if (!hasMoved) {
      changeCurrentDirection();
    }

  }

  private void changeCurrentDirection() {
    Point nextDirection;
    do
    {
      int dirIdx = (int) (Math.random() * 4);
      nextDirection = directions[dirIdx];
    } while (nextDirection == currentDirection);
    currentDirection = nextDirection;
  }

  @Override
  public boolean collide(Actor other) {

    if (other instanceof SingleRock) {
      game.destroyActorsInRectangularArea(getPosition(), 1);
    }

    if (other instanceof Carter) {
      game.carterDestroyed();
    }

    return super.collide(other);
  }

  @Override
  public void destroy() {
  }
}
