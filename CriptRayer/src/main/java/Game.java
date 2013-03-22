
public class Game {

  private static java.util.Scanner kbd = new java.util.Scanner(System.in); 

  private static char[][] board = new char[8][23];
  private static int boardRow = 0;
  private static Point raiderPosition = new Point();

  private static char TILE_EMPTY    = ' ';
  private static char TILE_WALL     = '#';
  private static char TILE_ROCK     = '%';
  private static char TILE_CARTER   = 'R';
  private static char TILE_SAND     = '*';
  private static char TILE_ARTIFACT = 'o';

  private static void addBoardRow(String row) {
    for (int i = 0; i < row.length(); ++i) {
      char actor = row.charAt(i);
      board[boardRow][i] = actor;
      if(actor == 'R') {
        raiderPosition.set(i, boardRow);
      }
    }
    ++boardRow;
  }

  private static void showBoard() {
    System.out.println();
    for (int r = 0; r < board.length; ++r) {
      for (int c = 0; c < board[r].length; ++c) {
          System.out.print(board[r][c]);
      }      
      System.out.println();
    }
  }

  private static void setBoard(int x, int y, char symbol) 
  {
    board[y][x] = symbol;
  }

  private static void setBoard(Point p, char symbol) 
  {
    setBoard(p.x, p.y, symbol);
  }

  private static char getBoardTile(Point p) {
    return board[p.y][p.x];
  }

  private static void moveRaiderTo(Point newPosition) {
    setBoard(raiderPosition, TILE_EMPTY);
    raiderPosition.set(newPosition.x, newPosition.y);
    setBoard(raiderPosition, TILE_CARTER);
  }

  private static boolean canMove(Point newPosition) {

    char tile = getBoardTile(newPosition);
    if(tile == TILE_EMPTY || 
       tile == TILE_SAND  ||
       tile == TILE_ARTIFACT
     ) 
    {
      return true;
    }
    return false;

  }

  private static Point getDirection() {
      System.out.print("Choose direction (ASDW): ");
      char key = kbd.next().charAt(0);
      key = Character.toUpperCase(key);
      Point direction = new Point();
      switch(key) {
        case 'A': direction.set(-1,  0); break;
        case 'D': direction.set(+1,  0); break;
        case 'S': direction.set( 0, +1); break;
        case 'W': direction.set( 0, -1); break;
      }
      return direction;
  }

  public static void main(String[] args) 
  {
    System.out.println("======== CryptRaider ========");
    addBoardRow("#######################");
    addBoardRow("#       ***           #");
    addBoardRow("#  %    ###  ### ###  #");
    addBoardRow("#  %    # #  # # # #  #");
    addBoardRow("#  %%   ###  # # # #  #");
    addBoardRow("#  %    #    ### ###  #");
    addBoardRow("#     R o             #");
    addBoardRow("#######################");

    showBoard();

    while(true) 
    {
      Point newPosition = new Point(raiderPosition);
      newPosition.add(getDirection());

      if(canMove(newPosition)) 
      {
        moveRaiderTo(newPosition);
      }

      showBoard();
    }

  }

}