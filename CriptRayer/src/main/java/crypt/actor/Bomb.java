package crypt.actor;

import crypt.Game;

class Bomb extends Actor implements DestructibleActor {
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
        if (target instanceof DestructibleActor) {
          game.getBoard().removeActor(target, true);
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