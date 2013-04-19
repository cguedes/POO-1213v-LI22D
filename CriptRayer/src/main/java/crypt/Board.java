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

  private void insertEmpty(Point position) {
    Empty empty = new Empty(position, game);
    actors[numActors - 1] = empty;
    fireActorUpdated(empty);
  }

  public Actor getActorAt(Point position) {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      if (actor.getPosition().equals(position))
        return actor;
    }
    throw new ActorNotFoundException(position);
  }

  public void removeActor(Actor actorToRemove) {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      if (actor == actorToRemove) {
        actors[i] = actors[numActors - 1];
        actors[numActors - 1] = null;
        return;
      }
    }
  }

  public void update() {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      actor.update();
    }
  }

  public void moveActor(Actor actor, Point nextPosition) {

    Actor target = getActorAt(nextPosition);
    Point previousPosition = new Point(actor.getPosition());

    // 1. remove target actor
    removeActor(target);

    // 2. move actor to target position
    actor.getPosition().set(nextPosition.x, nextPosition.y);
    fireActorUpdated(actor);

    // 3. insert space in previous actor position
    insertEmpty(previousPosition);

  }

  public int getNumRows() {
    return NUM_ROWS;
  }

  public int getNumCols() {
    return NUM_COLS;
  }

  private BoardListener boardListener = null;

  public void setBoardListener(BoardListener boardListener) {
    if (this.boardListener != null)
      throw new IllegalStateException("Cannot set more that one boardListener");

    if (boardListener == null)
      throw new IllegalArgumentException("boardListener");

    this.boardListener = boardListener;
  }

  private void fireActorUpdated(Actor actor) {
    if (boardListener != null) {
      boardListener.actorUpdated(actor);
    }

  }

}
