package crypt.actor;

public abstract class Actor {
  
  private final char symbol;
  private final Point position = new Point();

  public Actor(char symbol, Point position) {
    this.symbol = symbol;
    this.position.set(position.x, position.y);
  }

  public Point getPosition() {
    return position;
  }

  public void update() { }

  public void draw() {
    System.out.print(symbol);
  }

  public static Actor createActor(char symbol, Point position) {
    switch (symbol) {
      case 'R': return new Carter(position);
      case ':': return new Bug(position);
      case '#': return new Wall(position);
      case '%': return new Rock(position);
      case '*': return new Dirt(position);
      case 'o': return new Artifact(position);
      case ' ': return null;
      default:
        return null;
    }
  }


}

class Wall extends Actor { 
  public Wall(Point position) {
    super('#', position);
  }
}

class Rock extends Actor { 
  public Rock(Point position) {
    super('%', position);
  }
}

class Artifact extends Actor { 
  public Artifact(Point position) {
    super('o', position);
  }
}

class Dirt extends Actor { 
  public Dirt(Point position) {
    super('*', position);
  }
}

