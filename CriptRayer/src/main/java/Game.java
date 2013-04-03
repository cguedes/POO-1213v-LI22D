
public class Game {

  private static final Input input = new Input();
  private static final Board board = new Board();

  private static int boardRow = 0;
  // usage: addBoardRow("#  %    # #  # # # #  #");
  private static void addBoardRow(String row) {
    Point actorPosition = new Point(0, boardRow);
    for (int i = 0; i < row.length(); ++i) {
      char symbol = row.charAt(i);
      actorPosition.set(i, boardRow);
      board.addActor(symbol, actorPosition);
    }
    ++boardRow;
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

    board.draw();

    while(true) 
    {
      input.update();
      //board.update();
      board.draw();
    }

  }

}