package crypt;

import crypt.actor.Point;

public class ActorNotFoundException extends RuntimeException {

  public ActorNotFoundException(Point position) {
    super("Cannot find actor at position " + position);
  }
}
