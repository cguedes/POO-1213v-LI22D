package crypt.actor;

public class InvalidActorException extends RuntimeException {

  private static final long serialVersionUID = 3923051349300650391L;
  private final char symbol;
  private final Point position;

  public InvalidActorException(char symbol, Point position) {
    super("Cannot create actor with symbol " + symbol + " at position " + position);
    this.symbol = symbol;
    this.position = position;
  }

  public char getSymbol() {
    return symbol;
  }

  public Point getPosition() {
    return position;
  }

}
