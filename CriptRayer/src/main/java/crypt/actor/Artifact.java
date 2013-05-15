package crypt.actor;

import crypt.Game;

public class Artifact extends Actor implements DestroyableActor {
  public Artifact(Point position, Game game) {
    super('o', position, game);
  }

  @Override
  public void update() {

    Point targetPosition = getPosition().copy().add(Point.DOWN);
    Actor target = game.getBoard().getActorAt(targetPosition);
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