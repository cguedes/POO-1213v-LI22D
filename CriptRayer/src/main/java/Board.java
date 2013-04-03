
public class Board {
  
  private static final int NUM_ROWS = 8;
  private static final int NUM_COLS = 23;
  private final Actor[] actors = new Actor[NUM_ROWS*NUM_COLS];  
  private int numActors = 0;

  public void addActor(char symbol, Point position)
  {
    Actor actor = new Actor(symbol, position);
    actors[numActors++] = actor;
  }

  private Actor getActorAt(int x, int y) {
    for (int i = 0; i < actors.length; ++i) {
      Actor actor = actors[i];
      if(actor != null && actor.position.x == x && actor.position.y == y)
        return actor;
    }
    return null;
  }

  public void draw() {
    System.out.println();
    for (int r = 0; r < NUM_ROWS; ++r) {
      for (int c = 0; c < NUM_COLS; ++c) {
          Actor actor = getActorAt(c, r);
          if(actor != null) {
            actor.draw();
          }
          else {
            System.out.print(' ');
          }
      }
      System.out.println();
    }
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