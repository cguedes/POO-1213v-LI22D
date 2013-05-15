package crypt.actor;

import crypt.Game;

public abstract class Actor {

  private final char symbol;
  private final Point position = new Point();
  protected final Game game;

  public Actor(char symbol, Point position, Game game) {
    this.symbol = symbol;
    this.position.set(position.x, position.y);
    this.game = game;
  }

  public Point getPosition() {
    return position;
  }

  public void update() {
  }

  public void draw() {
    System.out.print(symbol);
  }

  public static Actor createActor(char symbol, Point position, Game game) {

    switch (symbol) {
    case 'R':
      return new Carter(position, game);
    case ':':
      return new Bug(position, game);
    case '#':
      return new Wall(position, game);
    case '%':
      return new Rock(position, game);
    case '*':
      return new Dirt(position, game);
    case 'o':
      return new Artifact(position, game);
    case 'b':
      return new Bomb(position, game);
    case '@':
      return new SingleRock(position, game);
    case 'k':
      return new Key(position, game);
    case 'd':
      return new Door(position, game);
    case 'U':
      return new Portal(position, game);
    case ' ':
      return new Empty(position, game);
    default:
      throw new InvalidActorException(symbol, position);
    }
  }

  public boolean collide(Actor other) {
    return false;
  }

  public char getSymbol() {
    return symbol;
  }

}

class Wall extends Actor {
  public Wall(Point position, Game game) {
    super('#', position, game);
  }
}

class Rock extends Actor implements DestroyableActor {
  public Rock(Point position, Game game) {
    super('%', position, game);
  }

  @Override
  public void destroy() {
  }
}
