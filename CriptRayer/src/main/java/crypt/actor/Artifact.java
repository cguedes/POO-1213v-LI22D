package crypt.actor;

import crypt.Game;

public class Artifact extends Actor {
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
      Point dir = this.getPosition().copy().sub(other.getPosition());
      Point targetPosition = getPosition().copy().add(dir);
      Actor target = game.getBoard().getActorAt(targetPosition);
      return game.collide(this, target);
    }

    return super.collide(other);
  }
}