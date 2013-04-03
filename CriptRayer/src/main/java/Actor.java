
public class Actor {
  
  private final char symbol;
  public final Point position = new Point();    // TODO: Change-me to private please!

  public Actor(char symbol, Point position) {
    this.symbol = symbol;
    this.position.set(position.x, position.y);
  }

  public void draw() {
    System.out.print(symbol);
  }


}

  /*
  private static char TILE_EMPTY    = ' ';
  private static char TILE_WALL     = '#';
  private static char TILE_ROCK     = '%';
  private static char TILE_CARTER   = 'R';
  private static char TILE_SAND     = '*';
  private static char TILE_ARTIFACT = 'o';
  */