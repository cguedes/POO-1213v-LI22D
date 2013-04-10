package crypt;

import crypt.actor.Actor;
import crypt.actor.Empty;
import crypt.actor.Point;

public class Board {

  private final Game game;
  private static final int NUM_ROWS = 8;
  private static final int NUM_COLS = 23;
  private final Actor[] actors = new Actor[NUM_ROWS * NUM_COLS];
  private int numActors = 0;

  public Board(Game game) {
    this.game = game;
  }

  public void addActor(char symbol, Point position)
  {
    Actor actor = Actor.createActor(symbol, position, game);
    actors[numActors++] = actor;
  }

  public Actor getActorAt(Point position) {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      if (actor.getPosition().equals(position))
        return actor;
    }
    return null;
  }

  public void removeActor(Actor actorToRemove) {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      if (actor == actorToRemove) {
        actors[i] = new Empty(actorToRemove.getPosition(), game);
        return;
      }
    }
  }

  public void draw() {
    Point position = new Point();
    System.out.println();
    for (int r = 0; r < NUM_ROWS; ++r) {
      for (int c = 0; c < NUM_COLS; ++c) {
        position.set(c, r);
        Actor actor = getActorAt(position);
        actor.draw();
      }
      System.out.println();
    }
  }

  public void update() {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      actor.update();
    }
  }

  public void moveActor(Actor actor, Point nextPosition) {

    Actor actorAtNextPosition = getActorAt(nextPosition);
    Point previousPosition = new Point(actor.getPosition());

    // Change positions
    actor.getPosition().set(nextPosition.x, nextPosition.y);
    actorAtNextPosition.getPosition().set(previousPosition.x, previousPosition.y);

  }

}
