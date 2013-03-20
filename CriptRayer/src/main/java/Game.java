
public class Game {

  private static java.util.Scanner kbd = new java.util.Scanner(System.in); 

  private static char[][] board = new char[8][23];
  private static int boardRow = 0;
  private static Point raiderPosition = new Point();


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

  private static void moveRaiderTo(Point raiderPosition) {
    setBoard(raiderPosition, ' ');
    
    raiderPosition.set(raiderPosition.x, raiderPosition.y);
   
    setBoard(raiderPosition, 'R');
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
    addBoardRow("#                     #");
    addBoardRow("#     R ###  ### ###  #");
    addBoardRow("#       # #  # # # #  #");
    addBoardRow("#       ###  # # # #  #");
    addBoardRow("#       #    ### ###  #");
    addBoardRow("#                     #");
    addBoardRow("#######################");

    showBoard();

    /*/ "hardcoded" move of Raider to right two times
    moveRaiderTo(raiderX + 1, raiderY);
    showBoard();
    moveRaiderTo(raiderX + 1, raiderY);
    showBoard();
    */

    while(true) 
    {
      Point dir = getDirection();
      raiderPosition.add(dir);
      moveRaiderTo(raiderPosition);
      showBoard();
    }

  }

}