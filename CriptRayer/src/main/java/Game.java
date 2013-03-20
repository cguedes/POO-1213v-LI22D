
public class Game {

  private static char[][] board = new char[8][23];
  private static int boardRow = 0;
  private static int raiderX, raiderY;


  private static void addBoardRow(String row) {
    for (int i = 0; i < row.length(); ++i) {
      char actor = row.charAt(i);
      board[boardRow][i] = actor;
      if(actor == 'R') {
        raiderX = i;
        raiderY = boardRow;
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

  private static void moveRaiderTo(int newX, int newY) {
    setBoard(raiderX, raiderY, ' ');
    
    raiderX = newX;
    raiderY = newY;
   
    setBoard(raiderX, raiderY, 'R');
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
    java.util.Scanner kbd = new java.util.Scanner(System.in); 

    while(true) 
    {
      // Get direction
      System.out.print("Choose direction (ASDW): ");
      char key = kbd.next().charAt(0);
      key = Character.toUpperCase(key);
      int dx = 0, dy = 0;
      switch(key) {
        case 'A': dx = -1; break;
        case 'S': dy = +1; break;
        case 'D': dx = +1; break;
        case 'W': dy = -1; break;
      }

      moveRaiderTo(raiderX + dx, raiderY + dy);
      showBoard();
    }

  }

}