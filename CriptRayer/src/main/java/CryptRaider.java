import crypt.Game;

public class CryptRaider {

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("======== CryptRaider ========");
    Game game = new Game();
    StaticBoardLoader.loadBoard(game);
    game.run();

  }

}