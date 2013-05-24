package crypt;

import java.util.ArrayList;
import java.util.Collection;

import crypt.actor.Actor;
import crypt.actor.Empty;
import crypt.actor.Point;

public class Board {

  private final Game game;
  private final int numRows;
  private final int numCols;
  private final Actor[] actors;
  private int numActors = 0;

  public Board(Game game, int numRows, int numCols) {
    this.game = game;
    this.numRows = numRows;
    this.numCols = numCols;
    this.actors = new Actor[numRows * numCols];
  }

  public Actor[] getActors() {
    return actors;
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

  public void removeActor(Actor actorToRemove, boolean insertEmpty) {
    removeActor(actorToRemove);
    if (insertEmpty)
    {
      insertEmpty(actorToRemove.getPosition());
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
    return numRows;
  }

  public int getNumCols() {
    return numCols;
  }

  private Collection<BoardListener> boardListeners = new ArrayList<BoardListener>();

  public void setBoardListener(BoardListener boardListener) {
    if (boardListener == null)
      throw new IllegalArgumentException("boardListener");

    this.boardListeners.add(boardListener);
  }

  private void fireActorUpdated(Actor actor) {
    for (BoardListener boardListener : boardListeners) {
      boardListener.actorUpdated(actor);
    }
  }

}
