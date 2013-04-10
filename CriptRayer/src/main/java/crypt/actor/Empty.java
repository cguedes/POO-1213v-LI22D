package crypt.actor;

import crypt.Game;

public class Empty extends Actor {

  private static int GLOBAL_ID = 0;
  private int id;

  public Empty(Point position, Game game) {
    super(' ', position, game);
    id = GLOBAL_ID++ % 10;
  }

  @Override
  public void draw() {
    System.out.print(id);
  }

}
