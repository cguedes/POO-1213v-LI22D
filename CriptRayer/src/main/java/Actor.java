
public class Actor {
  
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
      case 'R':
        return new Carter(position);
      case '#':
      case '%':
      case '*':
      case 'o':
        return new Actor(symbol, position);
      case ' ':
      default:
        return null;
    }
  }


}

