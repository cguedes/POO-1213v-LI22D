package crypt.actor;

import crypt.Game;

public class Bomb extends Actor implements DestroyableActor {
  private static final int POINT_FOR_ACTOR_DESTRUCTION = 3;

  public Bomb(Point position, Game game) {
    super('b', position, game);
  }

  @Override
  public void update() {
    Point targetPosition = getPosition().copy().add(Point.DOWN);
    Actor target = game.getBoard().getActorAt(targetPosition);
    game.collide(this, target);

    if (target instanceof Rock) {
      // remove all surrounding actors (only if they aren't indestructible)
      explodeBomb();
    }

    super.update();
  }

  private void explodeBomb() {

    Point position = new Point();
    int blastRange = 1;
    for (int r = -blastRange; r <= blastRange; ++r)
      for (int c = -blastRange; c <= blastRange; ++c)
      {
        position.set(getPosition().x + c, getPosition().y + r);
        Actor target = game.getBoard().getActorAt(position);
        if (target instanceof DestroyableActor) {
          game.getBoard().removeActor(target, true);
          game.addPoints(POINT_FOR_ACTOR_DESTRUCTION);
        }
      }

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